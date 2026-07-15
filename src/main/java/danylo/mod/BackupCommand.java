package danylo.mod;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


public class BackupCommand {
    private static final String userHome = System.getProperty("user.home"); //  /home/USER
    private static final Path backupRoot = Path.of(userHome, "MinecraftBackups");  //  /home/USER/MinecraftBackups
    private static final Path serverRoot = Path.of(userHome, "MinecraftServer");  //  /home/USER/MinecraftBackups

    private static final Logger LOGGER = LoggerFactory.getLogger(BackupCommand.class);
    private static final String NO_BACKUPS_MSG = "§cNo backups found. Run /backup to create your first one.";


    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            if (environment != Commands.CommandSelection.DEDICATED) return; // The command is registered on dedicated servers only

            // backup load
            LiteralArgumentBuilder<CommandSourceStack> loadCommand = Commands.literal("load")
                    .executes(BackupCommand::runLoad) // /backup load  (missing number -> error message)
                    .then(Commands.argument("backupNumber", IntegerArgumentType.integer(1))  // backups are numerated starting from 1
                            .executes(ctx -> runLoad(ctx, IntegerArgumentType.getInteger(ctx, "backupNumber"))) // /backup load <n>
                    );

            // /backup list
            LiteralArgumentBuilder<CommandSourceStack> listCommand = Commands.literal("list")
                    .executes(ctx -> runList(ctx, 10)) // if no atg provided, default to showing 10
                    .then(Commands.argument("listCount", IntegerArgumentType.integer(1))  // must show at least 1
                            .executes(ctx -> runList(ctx, IntegerArgumentType.getInteger(ctx, "listCount"))) // /backup list <n>
                    );

            // /backup
            LiteralArgumentBuilder<CommandSourceStack> backupCommand = Commands.literal("backup")
                    .requires(source -> source.hasPermission(3))
                    .executes(BackupCommand::runBackup) // /backup
                    .then(loadCommand)
                    .then(listCommand);

            dispatcher.register(backupCommand);
        });
    }


    private static int runBackup(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        MinecraftServer server = source.getServer();

        // Force save: loads data from RAM to disk and disables autosave
        server.saveEverything(true, true, true);
        server.setAutoSave(false);

        String formattedDate = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));

        // Saves /home/USER/MinecraftServer/world folder to /home/USER/MinecraftBackups/backup_TIME folder
        // In the future config file can be used for custom directories(not hardcoded)
        Path folderToCopy = serverRoot.resolve("world");
        Path folderDestination = backupRoot.resolve("backup_" + formattedDate);

        // Check source exists
        if (!Files.exists(folderToCopy) || !Files.isDirectory(folderToCopy)) {
            source.sendFailure(Component.literal("§cERROR: Source folder does not exist: " + folderToCopy));
            // CRITICAL: Resume autosave before returning
            server.setAutoSave(true);
            server.saveEverything(true, true, true);
            return 0;
        }

        source.sendSuccess(() -> Component.literal("§eBackup started... Wait..."), false);

        // Run the COPY in the background (ASYNC)
        CompletableFuture.runAsync(() -> {
            try {
                Files.createDirectories(folderDestination);
                copyDirectory(folderToCopy, folderDestination);

                // Send success message on the main thread
                server.execute(() -> {
                    source.sendSuccess(() ->
                                    Component.literal("§aBackup done! Saved to: " + folderDestination),
                            true
                    );
                });

            } catch (IOException e) {
                // Send error message on the main thread
                server.execute(() -> {
                    source.sendFailure(Component.literal("§cBackup failed: " + e.getMessage()));
                });
                LOGGER.error("Backup failed while copying files", e);
            } finally {
                // Resume autosave in the background thread's finally block
                server.execute(() -> {
                    server.setAutoSave(true);
                    server.saveEverything(true, true, true);
                    source.sendSuccess(() -> Component.literal("§7Autosave resumed."), false);
                });
            }
        });

        // Command returns immediately (server stays responsive)
        return 1;
    }


    private static int runLoad(CommandContext<CommandSourceStack> context) {
        context.getSource().sendFailure(Component.literal("You must provide number of the backup you want to load." +
                "\nCorrect syntax is /backup load <int>, where <int> is number of a backup, can be found in /backup list"));
        return 0;
    }

    private static int runLoad(CommandContext<CommandSourceStack> context, int backupNumber) {
        CommandSourceStack source = context.getSource();

        List<String> backupList;
        try {
            backupList = getBackupList();
        } catch (IOException e) {
            source.sendFailure(Component.literal(NO_BACKUPS_MSG));
            return 0;
        }

        if (backupList.isEmpty() || backupNumber > backupList.size()) {
            source.sendFailure(Component.literal(NO_BACKUPS_MSG));
            return 0;
        }

        String backupName = backupList.get(backupNumber - 1);
        Path worldToLoad = backupRoot.resolve(backupName).resolve("world");


        // IMPORTANT: use setsid to create a process outside of current JVM, otherwise when loadBackup.sh stops the server,
        // it also kills itself and can't finish (because it's a child process)
        ProcessBuilder pb = new ProcessBuilder("setsid", "bash", "./loadBackup.sh", worldToLoad.toString());
        pb.directory(serverRoot.toFile());

        File logFile = serverRoot.resolve("logs/backup_load.log").toFile();
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(logFile));
        pb.redirectError(ProcessBuilder.Redirect.appendTo(logFile));


        source.sendSuccess(() -> Component.literal(
                "§aLoading backup §e" + backupName + " §ain the background... server will restart in 2 seconds..."), false);

        // Give players a moment to read the message before the server stops
        CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> {
            try {
                pb.start();
            } catch (IOException e) {
                LOGGER.error("Failed to start backup load process", e);
            }
        });

        return 1;
    }


    private static int runList(CommandContext<CommandSourceStack> context, int listCount) {
        CommandSourceStack source = context.getSource();

        // List all entries (files and folders) in the backup root
        try {
            // Filter to only directories (your timestamped backups)
            var backups = getBackupList();

            if (backups.isEmpty()) {
                source.sendFailure(Component.literal(NO_BACKUPS_MSG));
                return 0;
            }

            // Print last backups,
            int backupsSize = Math.min(backups.size(), listCount);
            source.sendSuccess(() ->
                            Component.literal("§6=== Showing " + backupsSize + " of " + backups.size() + " most recent backups ==="),
                    false
            );

            for (int i = 0; i < backupsSize; i++) {
                String backupName = backups.get(i);
                String displayText = "§a" + (i + 1) + ". " + backupName;

                source.sendSuccess(() -> Component.literal(displayText), false);
            }

            return 1;

        } catch (NoSuchFileException e) {
            source.sendFailure(Component.literal(NO_BACKUPS_MSG));
            return 0;
        } catch (IOException e) {
            source.sendFailure(Component.literal("§cFailed to list backups: " + e.getMessage()));
            LOGGER.error("Couldn't list backups", e);
            return 0;
        }
    }


    /**
     * Returns a list of backup names(strings e.g. "backup_2026-07-13_20-56") with most recent being at index 0
     */
    private static List<String> getBackupList() throws IOException {
        try (Stream<Path> stream = Files.list(backupRoot)) {
            // Filter to only directories (your timestamped backups)
            List<String> backupList = stream
                    .filter(Files::isDirectory)
                    .map(Path::getFileName) // Get just the folder name (e.g., "backup_2026-07-13_20-56")
                    .map(Path::toString)
                    .sorted(Comparator.reverseOrder()) // Sort alphabetically (recent first)
                    .toList();

            return backupList;
        }
    }


    /**
     * Creates folder(with the name of the source) in destination and copies files there
     */
    private static void copyDirectory(Path source, Path dest) throws IOException {
        Path sourceName = source.getFileName();
        if (sourceName == null) {
            throw new IOException("Source path does not have a valid folder name: " + source);
        }

        Path target = dest.resolve(sourceName);

        // Walk through every file and subfolder in the source
        try (Stream<Path> stream = Files.walk(source)) {
            for (Path path : stream.toList()) { // Use toList() to avoid stream issues

                if (path.getFileName().toString().endsWith(".lock")) {
                    continue; // Skip any .lock file (session.lock, etc.)
                }

                // Calculate the relative path from the source (e.g., "world/level.dat")
                Path relativePath = source.relativize(path);
                Path destinationPath = target.resolve(relativePath);

                if (Files.isDirectory(path)) {
                    // Create the directory at the destination
                    Files.createDirectories(destinationPath);
                } else {
                    // Copy the file (overwrite if it exists)
                    Files.copy(path, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

}
