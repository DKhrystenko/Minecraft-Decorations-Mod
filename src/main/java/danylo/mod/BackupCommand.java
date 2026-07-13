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

        // FORCE SAVE: write everything from RAM to disk and pause autosave
        server.saveEverything(true, true, true);
        server.setAutoSave(false);

        source.sendSuccess(() -> Component.literal("Starting backup..."), true);

        LocalDateTime dateTimeObj = LocalDateTime.now();
        DateTimeFormatter formatterObj  = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
        String formattedDate = dateTimeObj.format(formatterObj);

        Path folderToCopy = Path.of("C:\\Users\\yaditrc\\Desktop\\danylo-decor\\run");
        Path folderDestination = Path.of("D:\\backup_" + formattedDate); // backup_{YEAR}-{MONTH}-{DAY}_{HOUR}-{MINUTE}

        try {
            try {
                Files.createDirectory(folderDestination);
            } catch (IOException e) {
                source.sendFailure(Component.literal("§cFailed to create folder: " + e.getMessage()));
                return 0;
            }

            try {
                source.sendSuccess(() -> Component.literal("Copying files... Wait"), true);
                copyDirectory(folderToCopy, folderDestination);
                source.sendSuccess(() -> Component.literal("Backup done! Saved to: " + folderDestination), true);
            } catch  (IOException e) {
                source.sendFailure(Component.literal("§cFailed to copy world: " + e.getMessage()));
                return 0;
            }

            source.sendSuccess(() -> Component.literal("Backup successful."), true);
            return 1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // MUST BE EXECUTED
            server.setAutoSave(true);
            server.saveEverything(true, true, true);
        }
    }


    private static void copyDirectory(Path source, Path dest) throws IOException {
        // 1. Extract the source folder's name (e.g., "run")
        Path sourceName = source.getFileName();
//        if (sourceName == null) {
//            throw new IOException("Source path does not have a valid folder name: " + source);
//        }

        // 2. Build the final target: D:\backup_time\run
        Path target = dest.resolve(sourceName);

        // 3. Walk through every file and subfolder in the source
        try (Stream<Path> stream = Files.walk(source)) {
            for (Path path : stream.toList()) { // Use toList() to avoid stream issues

                if (path.getFileName().toString().endsWith(".lock")) {
                    continue; // Skip any .lock file (session.lock, etc.)
                }

                // Calculate the relative path from the source (e.g., "world/level.dat")
                Path relativePath = source.relativize(path);
                // Build the destination path (e.g., D:\backup_time\run\world\level.dat)
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
