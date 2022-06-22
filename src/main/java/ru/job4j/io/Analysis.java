package ru.job4j.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;

public class Analysis {
    public void unavailable(String source, String target) throws IOException {
        try (var writer = new PrintWriter(new FileOutputStream(target))) {
            var reader = new BufferedReader(new FileReader(source));
            var server = true;
            while (reader.ready()) {
                var line = reader.readLine();
                if (server && (line.contains("400") || line.contains("500"))) {
                    server = false;
                    writer.print(line.replaceAll("\\d{3}\\s", "") + "; ");
                } else if (!server && (line.contains("200") || line.contains("300"))) {
                    server = true;
                    writer.print(line.replaceAll("\\d{3}\\s", "") + "; " + System.lineSeparator());
                }
            }
        }
    }

    public static void main(String[] args) {
        var analysis = new Analysis();
        try {
            analysis.unavailable("./data/server.log", "./data/unavailable.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

