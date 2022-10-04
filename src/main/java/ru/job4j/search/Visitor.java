package ru.job4j.search;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Visitor extends SimpleFileVisitor<Path> {
    private final String name;
    private final String type;
    private final Predicate<String> filter;
    private final ArrayList<Path> files = new ArrayList<>();

    public Visitor(String name, String type) {
        this.name = name;
        this.type = type;
        this.filter = filter();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (filter.test(file.getFileName().toString())) {
            files.add(file);
        }
        return CONTINUE;
    }

    public Predicate<String> filter() {
        Predicate<String> filter = Objects::nonNull;
        if ("mask".equals(type)) {
            var pattern = String.format("^%s$",
                    name.replace(".", "[.]").replace("*", ".*").replace("?", "."));
            filter = element -> element.matches(pattern);
        } else if ("name".equals(type)) {
            filter = element -> element.equals(name);
        } else if ("regex".equals(type)) {
            filter = element -> element.matches(name);
        }
        return filter;
    }

    public List<Path> getPaths() {
        return new ArrayList<>(files);
    }
}
