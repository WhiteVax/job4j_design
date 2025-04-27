package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final HashMap<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (var reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(e -> !e.isEmpty() && !e.matches("^#+.*"))
                    .forEach(e -> {
                        String[] s = e.split("=", 2);
                        if (s.length != 2 || s[0].isEmpty() || s[1].isEmpty()) {
                            throw new IllegalArgumentException();
                        }
                        values.put(s[0], s[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(System.lineSeparator());
        try (var reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(joiner::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return joiner.toString();
    }
}
