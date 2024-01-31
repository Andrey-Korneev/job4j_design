package ru.job4j.io.searcher;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class SearcherTest {
    @Test
    void whenUseTypeName(@TempDir Path folder) throws Exception {
        Path file = folder.resolve("file.txt");
        Files.writeString(file, "data");
        Path result = folder.resolve("result.txt");
        var args = new String[] {"-d=" + folder, "-n=file.txt", "-t=name", "-o=" + result};
        var expected = folder + "\\file.txt" + System.lineSeparator();
        Searcher.main(args);
        assertThat(Files.readString(result)).isEqualTo(expected);
    }

    @Test
    void whenUseTypeMask(@TempDir Path folder) throws Exception {
        Path file = folder.resolve("file.txt");
        Path file2 = folder.resolve("file2.data");
        Path file3 = folder.resolve("file3.qxt");
        Files.writeString(file, "data");
        Files.writeString(file2, "data");
        Files.writeString(file3, "data");
        Path result = folder.resolve("result.txt");
        var args = new String[] {"-d=" + folder, "-n=*.?xt", "-t=mask", "-o=" + result};
        var expected = String.join(
                System.lineSeparator(),
                folder + "\\file.txt",
                folder + "\\file3.qxt"
        ).concat(System.lineSeparator());
        Searcher.main(args);
        assertThat(Files.readString(result)).isEqualTo(expected);
    }

    @Test
    void whenUseTypeMask2(@TempDir Path folder) throws Exception {
        Path file = folder.resolve("f.txt");
        Path file2 = folder.resolve("file.txt");
        Files.writeString(file, "data");
        Files.writeString(file2, "data");
        Path result = folder.resolve("result.txt");
        var args = new String[] {"-d=" + folder, "-n=?.*", "-t=mask", "-o=" + result};
        var expected = folder + "\\f.txt" + System.lineSeparator();
        Searcher.main(args);
        assertThat(Files.readString(result)).isEqualTo(expected);
    }

    @Test
    void whenUseTypeMask3(@TempDir Path folder) throws Exception {
        Path file = folder.resolve("f.t");
        Path file2 = folder.resolve("f.txt");
        Files.writeString(file, "data");
        Files.writeString(file2, "data");
        Path result = folder.resolve("result.txt");
        var args = new String[] {"-d=" + folder, "-n=?.?", "-t=mask", "-o=" + result};
        var expected = folder + "\\f.t" + System.lineSeparator();
        Searcher.main(args);
        assertThat(Files.readString(result)).isEqualTo(expected);
    }

    @Test
    void whenUseTypeMask4(@TempDir Path folder) throws Exception {
        Path file = folder.resolve("file.txt");
        Path file2 = folder.resolve("file2.data");
        Files.writeString(file, "data");
        Files.writeString(file2, "data");
        Path result = folder.resolve("result.txt");
        var args = new String[] {"-d=" + folder, "-n=*.*", "-t=mask", "-o=" + result};
        var expected = String.join(
                System.lineSeparator(),
                folder + "\\file.txt",
                folder + "\\file2.data"
        ).concat(System.lineSeparator());
        Searcher.main(args);
        assertThat(Files.readString(result)).isEqualTo(expected);
    }

    @Test
    void whenUseTypeRegex(@TempDir Path folder) throws Exception {
        Path file = folder.resolve("file.txt");
        Path file2 = folder.resolve("file2.data");
        Files.writeString(file, "data");
        Files.writeString(file2, "data");
        Path result = folder.resolve("result.txt");
        var args = new String[] {"-d=" + folder, "-n=[a-zA-Z0-9._ -]+\\.\\w+", "-t=regex", "-o=" + result};
        var expected = String.join(
                System.lineSeparator(),
                folder + "\\file.txt",
                folder + "\\file2.data"
        ).concat(System.lineSeparator());
        Searcher.main(args);
        assertThat(Files.readString(result)).isEqualTo(expected);
    }
}