package ru.job4j.io.searcher;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Searcher {
    private static final String START_DIRECTORY = "d";
    private static final String NAME = "n";
    private static final String NAME_TYPE = "t";
    private static final String RESULT_PATH = "o";
    private static final String[] NAME_TYPES = new String[] {"name", "mask", "regex"};
    private static final String REGEX_PATH_TO_DIRECTORY = "([a-zA-Z]:\\\\)?(([a-zA-Z0-9_. -]*)\\\\?)*";
    private static final String REGEX_PATH_TO_FILE = "([a-zA-Z]:\\\\)?([a-zA-Z0-9_. -]\\\\?)*[a-zA-Z0-9._ -]+\\.\\w+";
    public static final String HELP = "Use keys:\n-"
            + START_DIRECTORY + "\tsearch directory\n-"
            + NAME + "\tfilename, mask, regex\n-"
            + NAME_TYPE + "\tsearch type (\"name\", \"mask\", \"regex\")\n-"
            + RESULT_PATH + "\tresult filename";

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        Path start = Path.of(argsName.get(START_DIRECTORY));
        String regex = getRegexFromName(argsName.get(NAME), argsName.get(NAME_TYPE));
        Pattern pattern = Pattern.compile(regex);
        Predicate<Path> condition = path -> pattern.matcher(path.toFile().getName()).matches();
        List<Path> result = search(start, condition);
        outputResult(result, argsName.get(RESULT_PATH));
    }

    private static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static String getRegexFromName(String name, String type) {
        if ("mask".equals(type)) {
            name = name.replaceAll("\\.", "\\\\.")
                    .replaceAll("\\*", ".*")
                    .replaceAll("\\?", ".");
        }
        return name;
    }

    private static void checkArgs(ArgsName args) {
        if (args.getValues().size() != 4) {
            throw new IllegalArgumentException("Wrong number of program arguments. There should be four.\n"
                    + HELP);
        }
        if (!Pattern.matches(REGEX_PATH_TO_DIRECTORY, args.get(START_DIRECTORY))) {
            throw new IllegalArgumentException("Wrong path to start directory.\n" + HELP);
        }
        String type = args.get(NAME_TYPE).toLowerCase();
        if (!Arrays.asList(NAME_TYPES).contains(type)) {
            throw new IllegalArgumentException("Wrong search type.\n" + HELP);
        }
        if (!Pattern.matches(REGEX_PATH_TO_FILE, args.get(RESULT_PATH))) {
            throw new IllegalArgumentException("Wrong path to result file.\n" + HELP);
        }
    }

    private static void outputResult(List<Path> result, String outputPath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath))) {
            for (Path path : result) {
                writer.append(path.toString())
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}