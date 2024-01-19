package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CSVReader {
    private static final String IN = "path";
    private static final String DELIMITER = "delimiter";
    private static final String OUT = "out";
    private static final String FILTER = "filter";
    private static final String FILTER_DELIMITER = ",";
    private static final String STD_OUT = "stdout";
    private static final String REGEX_PATH_TO_FILE =
            "([a-zA-Z]:\\\\)?([a-zA-Z0-9_. -]\\\\?)*[a-zA-Z0-9._ -]+\\.\\w+";


    public static void handle(ArgsName argsName) {
        try (var scanner = new Scanner(Paths.get(argsName.get(IN))).useDelimiter(System.lineSeparator());
                var writer = getWriter(argsName.get(OUT))) {
            if (scanner.hasNext()) {
                var line = scanner.next();
                var indexes = getIndexes(line, argsName.get(DELIMITER), argsName.get(FILTER));
                writer.append(getData(line, indexes, argsName.get(DELIMITER)))
                        .append(System.lineSeparator());
                while (scanner.hasNext()) {
                    line = scanner.next();
                    writer.append(getData(line, indexes, argsName.get(DELIMITER)))
                            .append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static PrintWriter getWriter(String path) {
        PrintWriter writer = null;
        if (STD_OUT.equals(path)) {
            writer = new PrintWriter(System.out);
        } else {
            try {
                writer = new PrintWriter(new FileWriter(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return writer;
    }

    private static List<Integer> getIndexes(String line, String delimiter, String filter) {
        List<Integer> indexes = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        var scanner = new Scanner(new ByteArrayInputStream(line.getBytes())).useDelimiter(delimiter);
        while (scanner.hasNext()) {
            headers.add(scanner.next());
        }
        for (var f : filter.split(FILTER_DELIMITER)) {
            if (headers.contains(f)) {
                indexes.add(headers.indexOf(f));
            }
        }
        return indexes;
    }

    private static String getData(String line, List<Integer> indexes, String delimiter) {
        List<String> values = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        var scanner = new Scanner(new ByteArrayInputStream(line.getBytes())).useDelimiter(delimiter);
        while (scanner.hasNext()) {
            values.add(scanner.next());
        }
        for (var index : indexes) {
            result.append(values.get(index)).append(delimiter);
        }
        return result.isEmpty() ? "" : result.substring(0, result.length() - 1);
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        handle(argsName);
    }

    private static void checkArgs(ArgsName argsName) {
        if (!Files.exists(Paths.get(argsName.get(IN)))) {
            throw new IllegalArgumentException("Source file not found.");
        }
        String out = argsName.get(OUT);
        if (!STD_OUT.equals(out) && !Pattern.matches(REGEX_PATH_TO_FILE, out)) {
            throw new IllegalArgumentException("Wrong path to output file.");
        }
    }
}