package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] numbers = text.toString().split(System.lineSeparator());
            for (String s : numbers) {
                int number = Integer.parseInt(s);
                if (number % 2 == 0) {
                    System.out.println(number + " - число четное");
                } else {
                    System.out.println(number + " - число нечетное");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}