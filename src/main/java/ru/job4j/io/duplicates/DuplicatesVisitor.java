package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, String> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        var fileProperty = new FileProperty(files.size(), file.getFileName().toAbsolutePath().toString());
        if (files.containsKey(fileProperty)) {
            System.out.println(fileProperty.getName());
        }
        files.putIfAbsent(fileProperty, fileProperty.getName());
        return super.visitFile(file, attrs);
    }
}
