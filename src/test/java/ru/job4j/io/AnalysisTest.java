package ru.job4j.io;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AnalysisTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     *
     * @throws IOException вылетает когда используется junit 5 вместо 4
     * ниже более лаконичный аналог
     */
    @org.junit.Test
    public void whenAnalysisSuccessful() throws IOException {
        var source = folder.newFile("testServerLog.txt");
        var target = new File(folder.getRoot(), "testUnavailable.csv");
        try (var writer = new PrintWriter(source)) {
            writer.print("200 10:56:01"
                    + System.lineSeparator()
                    + "200 10:57:01"
                    + System.lineSeparator()
                    + "400 10:58:01"
                    + System.lineSeparator()
                    + "200 10:59:01"
                    + System.lineSeparator()
                    + "500 11:01:02"
                    + System.lineSeparator()
                    + "200 11:02:02");
        }
        var analysis = new Analysis();
        analysis.unavailable(source.getPath(), target.getPath());
        var builder = new StringBuilder();
        try (var in = new BufferedReader(new FileReader(target))) {
                in.lines().forEach(builder :: append);
        }
        assertThat(builder.toString()).isEqualTo("10:58:01; 10:59:01; "
                + "11:01:02; 11:02:02; ");
    }

    @Test
    public void whenUnavailableWithFirstData(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
            output.println("500 11:01:02");
            output.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getPath(), target.getPath());
        StringBuilder result = new StringBuilder();
        try (var in = new BufferedReader(new FileReader(target))) {
                in.lines().forEach(result :: append);
        }
        String expected = "10:57:01; 10:59:01; 11:01:02; 11:02:02; ";
        assertThat(result.toString()).isEqualTo(expected);
    }
}
