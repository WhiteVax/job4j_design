package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        search(Path.of(args[0]), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
        var file = new File(args[0]);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s!", file.getAbsoluteFile()));
        }
        if (!args[1].matches("(^\\.[a-z]+)")) {
            throw new IllegalArgumentException(String.format("Wrong format file %s!", args[1]));
        }
    }
}