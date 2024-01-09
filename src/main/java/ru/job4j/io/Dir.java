package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("Total size: %s bytes%n", file.getTotalSpace());
        getListFilesWithSize(file.listFiles());
    }

    public static void getListFilesWithSize(File[] listFiles) {
        for (File file : listFiles) {
            if (file.isFile()) {
                System.out.printf("%s -- %s bytes%n", file.getName(), file.length());
            } else {
                getListFilesWithSize(file.listFiles());
            }
        }
    }
}