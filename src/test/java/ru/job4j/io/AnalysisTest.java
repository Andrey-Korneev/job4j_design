package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {
    @Test
    void whenTwoPeriods(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.append("200 10:56:01").append(System.lineSeparator())
                    .append("500 10:57:01").append(System.lineSeparator())
                    .append("400 10:58:01").append(System.lineSeparator())
                    .append("300 10:59:01").append(System.lineSeparator())
                    .append("500 11:01:02").append(System.lineSeparator())
                    .append("200 11:02:02");
        }
        File target  = tempDir.resolve("target.csv").toFile();
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(line -> result.append(line).append(System.lineSeparator()));
        }
        StringBuilder expected = new StringBuilder();
        expected.append("10:57:01;10:59:01").append(System.lineSeparator())
                .append("11:01:02;11:02:02").append(System.lineSeparator());
        assertThat(expected.toString()).hasToString(result.toString());
    }

    @Test
    void whenOnePeriod(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.append("200 10:56:01").append(System.lineSeparator())
                    .append("500 10:57:01").append(System.lineSeparator())
                    .append("400 10:58:01").append(System.lineSeparator())
                    .append("500 10:59:01").append(System.lineSeparator())
                    .append("400 11:01:02").append(System.lineSeparator())
                    .append("300 11:02:02");
        }
        File target  = tempDir.resolve("target.csv").toFile();
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(line -> result.append(line).append(System.lineSeparator()));
        }
        assertThat("10:57:01;11:02:02" + System.lineSeparator()).hasToString(result.toString());
    }
}