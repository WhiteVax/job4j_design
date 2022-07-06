package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        var fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (files.containsKey(fileProperty)) {
            List<Path> list = files.get(fileProperty);
            list.add(file);
        } else {
            List<Path> list = new ArrayList<>();
            list.add(file);
            files.put(fileProperty, list);
        }
        return super.visitFile(file, attrs);
    }

    public void printDuplicates() {
        if (files.isEmpty() || files.values().stream().noneMatch(e -> e.size() > 1)) {
            System.out.println("Not found duplicates!");
        } else {
            files.values()
                    .stream()
                    .filter(paths -> paths.size() > 1)
                    .forEach(e -> e.forEach(l -> System.out.println(l.toFile().getAbsoluteFile())));
        }
    }
}
