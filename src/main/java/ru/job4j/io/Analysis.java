package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileWriter(target))) {
            boolean isWorking = true;
            String start = null;
            String end = null;
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith("4") || line.startsWith("5")) {
                    if (isWorking) {
                        start = line.split("\\s")[1];
                        isWorking = false;
                    }
                } else {
                    if (!isWorking) {
                        end = line.split("\\s")[1];
                        writer.append(start)
                                .append(';')
                                .append(end)
                                .append(System.lineSeparator());
                        isWorking = true;
                    }
                }
                line = reader.readLine();
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