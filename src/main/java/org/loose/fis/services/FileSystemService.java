package org.loose.fis.services;

import javafx.scene.shape.Path;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystemService {

        public static String APPLICATION_FOLDER = ".registration-example";
        private static final String USER_FOLDER = System.getProperty("user.home");

        public static Path getApplicationHomeFolder() {
                return (Path) Paths.get(USER_FOLDER, APPLICATION_FOLDER);
        }

        static void initDirectory()
        {
                Path applicationHomePath = getApplicationHomeFolder();
                if (!Files.exists((java.nio.file.Path) applicationHomePath))
                        ((java.nio.file.Path) applicationHomePath).toFile().mkdirs();
        }
}
