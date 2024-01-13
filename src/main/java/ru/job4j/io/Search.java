package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {
    public static void main(String[] args) throws IOException {
        checkArgs(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void checkArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong number of program arguments. There should be two.");
        }
        if (!Pattern.matches("([a-zA-Z]:\\\\)?(([a-zA-Z0-9_.-]*)\\\\?)*", args[0])) {
            throw new IllegalArgumentException("Wrong path.");
        }
        if (!Pattern.matches("\\.\\w+", args[1])) {
            throw new IllegalArgumentException("Wrong file extension.");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}