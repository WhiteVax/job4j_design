package ru.job4j.search;

import java.util.HashMap;

public class ArgsName {
    private final HashMap<String, String> map = new HashMap<>();

    private void parse(String[] txt) {
        for (var string: txt) {
            String[] array = string.split("=", 2);
            validateString(string);
            map.putIfAbsent(array[0], array[1]);
        }
    }

    private void validateString(String arg) {
        if (!arg.matches("(-{1}.+={1}.+)")) {
            throw new IllegalArgumentException(String.format("Wrong arg %s.", arg));
        }
    }

    public ArgsName of(String[] arg) {
        if (arg.length != 4) {
            throw new IllegalArgumentException("Wrong count args.");
        }
        var argsName = new ArgsName();
        argsName.parse(arg);
        return argsName;
    }

    public String get(String key) {
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException("Not found key.");
        }
        return map.get(key);
    }
}
