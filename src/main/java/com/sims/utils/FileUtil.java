package com.sims.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUtil {

    public static void moveFile(String sourceFileName, String destinationFolderPath) throws IOException {
        Path source = new File(sourceFileName).toPath();
        Path destination = new File(destinationFolderPath, source.getFileName().toString()).toPath();
        Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
    }
}
