package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileWriter(target))) {
            var lines = reader.lines().toList();
            boolean isWorking = true;
            String start = null;
            String end = null;
            for (var line : lines) {
                if (line.startsWith("4") || line.startsWith("5")) {
                    if (isWorking) {
                        start = line.split("\\s")[1];
                        isWorking = false;
                    }
                } else {
                    if (!isWorking) {
                        end = line.split("\\s")[1];
                        writer.println(start + ";" + end);
                        isWorking = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}