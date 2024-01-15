package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String INTRO = "Начинаем... Вы первый(ая).";
    private static final String FAREWELL = "До свидания.";
    private static final Scanner SCANNER = new Scanner(System.in);
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        boolean isStop = false;
        boolean isRun = true;
        System.out.println(INTRO);
        while (isRun) {
            System.out.print("> ");
            String userPhrase = SCANNER.nextLine();
            log.add("User: " + userPhrase);
            if (OUT.equals(userPhrase)) {
                isRun = false;
            } else if (STOP.equals(userPhrase)) {
                isStop = true;
            } else if (CONTINUE.equals(userPhrase)) {
                isStop = false;
            }
            if (isRun && !isStop) {
                String botAnswer = getRandomPhrase(phrases);
                System.out.println(botAnswer);
                log.add("Bot: " + botAnswer);
            } else if (!isRun) {
                saveLog(log);
                System.out.println(FAREWELL);
            }
        }
    }

    private String getRandomPhrase(List<String> phrases) {
        Random random = new Random();
        return phrases.get(random.nextInt(phrases.size()));
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            reader.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/chatLog.txt", "data/chatPhrases.txt");
        consoleChat.run();
    }
}