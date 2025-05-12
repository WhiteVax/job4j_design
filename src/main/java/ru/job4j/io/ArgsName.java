package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (var line : args) {
            validate(line);
            String[] word = line.split("=", 2);
            values.putIfAbsent(word[0].substring(1), word[1]);
        }
    }

    private void validate(String line) {
        if (!line.contains("=")) {
            throw new IllegalArgumentException("Error: This argument '" + line + "' does not contain an equal sign");
        }
        if (!line.startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument '" + line + "' does not start with a '-' character");
        }
        if (line.startsWith("-") && line.indexOf("=") == 1) {
            throw new IllegalArgumentException("Error: This argument '" + line + "' does not contain a key");
        }
        if (line.indexOf("=") == line.length() - 1) {
            throw new IllegalArgumentException("Error: This argument '" + line + "' does not contain a value");
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        var names = new ArgsName();
        names.parse(args);
        return names;
    }
}
