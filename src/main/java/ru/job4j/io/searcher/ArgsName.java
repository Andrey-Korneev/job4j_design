package ru.job4j.io.searcher;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing.\n" + Searcher.HELP);
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String pair : args) {
            int split = pair.indexOf('=');
            values.put(pair.substring(1, split), pair.substring(split + 1));
        }
    }

    public static ArgsName of(String[] args) {
        checkArgs(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public static void checkArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program.\n" + Searcher.HELP);
        }
        for (String arg : args) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(
                        "Error: This argument '" + arg + "' does not contain an equal sign"
                );
            }
            int split = arg.indexOf('=');
            String key = arg.substring(0, split);
            if (key.matches("-*")) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a key");
            }
            if (!key.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not start with a '-' character");
            }
            String value = arg.substring(split + 1);
            if (value.isEmpty()) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a value");
            }
        }
    }
}