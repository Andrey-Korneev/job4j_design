package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    if (!line.contains("=")) {
                        throw new IllegalArgumentException();
                    }
                    String key = line.substring(0, line.indexOf('=')).stripTrailing();
                    String value = line.substring(line.indexOf('=') + 1).stripLeading();
                    if (key.isEmpty() || value.isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    values.put(key, value);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public Map<String, String> getValues() {
        return values;
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}