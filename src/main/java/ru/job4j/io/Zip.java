package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(input.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(input.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName programArgs = ArgsName.of(args);
        checkArgs(programArgs);
        List<Path> files = Search.search(
                Paths.get(programArgs.get("d")),
                path -> !path.toFile().getName().endsWith(programArgs.get("e"))
        );
        new Zip().packFiles(files, new File(programArgs.get("o")));
    }

    private static void checkArgs(ArgsName argsName) {
        if (!Files.exists(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("Specified directory not found.");
        }
        if (!Pattern.matches("\\.\\w+", argsName.get("e"))) {
            throw new IllegalArgumentException("Wrong extension of excluded files.");
        }
        if (!Pattern.matches(
                "([a-zA-Z]:\\\\)?(([a-zA-Z0-9_.-]*)\\\\?)*[a-zA-Z0-9._ -]+\\.zip$",
                argsName.get("o"))) {
            throw new IllegalArgumentException("Wrong archive name.");
        }
    }
}