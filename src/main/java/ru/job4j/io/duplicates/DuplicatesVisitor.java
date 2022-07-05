package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, String> files = new HashMap<>();
    private final Set<FileProperty> duplicates = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        var fileProperty = new FileProperty(files.size(), file.getFileName().toAbsolutePath().toString());
        if (files.containsKey(fileProperty)) {
            duplicates.add(fileProperty);
        }
        files.putIfAbsent(fileProperty, fileProperty.getName());
        return super.visitFile(file, attrs);
    }

    public void  printDuplicates() {
        if (duplicates.isEmpty()) {
            System.out.println("Not found duplicates.");
        } else {
            duplicates.forEach(e -> System.out.println(e.getName()));
        }
    }
}
