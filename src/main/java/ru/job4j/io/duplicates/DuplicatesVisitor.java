package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file), file.toFile().getName());
        files.computeIfAbsent(fileProperty, k -> new ArrayList<>()).add(file);
        return FileVisitResult.CONTINUE;
    }

    public Map<FileProperty, List<Path>> getDuplicates() {
        return files.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void printDuplicates() {
        for (var file : getDuplicates().entrySet()) {
            FileProperty fileProperty = file.getKey();
            System.out.println(fileProperty.getName() + " - " + fileProperty.getSize() + " bytes");
            for (var path : file.getValue()) {
                System.out.println("  " + path);
            }
        }
    }
}