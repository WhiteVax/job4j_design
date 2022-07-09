package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("The container doesn't contain such a key - %s.", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        validate(args);
        for (var line : args) {
            String[] word = line.split("=", 2);
            values.putIfAbsent(word[0].replace("-", ""), word[1].replace("-", ""));
        }
    }

    private void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty args.");
        }
        for (var line : args) {
            if (!line.matches("(-{1}.+={1}.+)")) {
                throw new IllegalArgumentException(String.format("Wrong format %s.", line));
            }
        }
    }

    public static ArgsName of(String[] args) {
        var names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        var jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        var zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
