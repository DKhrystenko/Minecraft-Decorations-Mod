package danylo.mod;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;


public class BackupCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("backup")
                    .requires(source -> source.hasPermission(3))
                    .executes(BackupCommand::runBackup));
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

        // Linux only, saves /home/USER/MinecraftServer/world folder to /home/USER/MinecraftBackups/backup_TIME folder
        // In the future config file can be used for custom directories(not hardcoded)
        String userHome = System.getProperty("user.home");
        Path folderToCopy = Path.of(userHome, "MinecraftServer/world");
        Path backupRoot = Path.of(userHome, "MinecraftBackups");
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
                                    Component.literal("§aBackup done! Saved to: " + folderDestination.resolve("world")),
                            true
                    );
                });

            } catch (IOException e) {
                // Send error message on the main thread
                server.execute(() -> {
                    source.sendFailure(Component.literal("§cBackup failed: " + e.getMessage()));
                });
                e.printStackTrace(); // Log to RPi5 console
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
