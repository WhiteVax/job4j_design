package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalysisTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenAnalysisSuccessful() throws IOException {
        var source = folder.newFile("testServerLog.txt");
        var target = folder.newFile("testUnavailable.cvs");
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
        assertThat(builder.toString(), is("10:58:01; 10:59:01; "
                + "11:01:02; 11:02:02; "));
    }
}
