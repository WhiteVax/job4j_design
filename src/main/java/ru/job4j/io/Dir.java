package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        var file = new File("C:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exists %s" + file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s" + file.getAbsoluteFile()));
        }
        System.out.println(file.getName() + " : " + file.getTotalSpace());
        for (var subfile : Objects.requireNonNull(file.listFiles())) {
            System.out.println(subfile.getName() + " : " + subfile.length());
        }
    }
}
