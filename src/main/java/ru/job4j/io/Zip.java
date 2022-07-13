package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (var zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (var file : sources) {
                zip.putNextEntry(new ZipEntry(file.toString()));
                try (var out = new BufferedInputStream(new FileInputStream(file.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Path> convertToList(String directory, String exclude) {
        List<Path> list = new ArrayList<>();
        try {
            list = Search.search(Path.of(directory), p -> !p.toFile().getName().matches(String.format(".%s", exclude)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void checkValidate(String[] args, ArgsName argName) {
        if (args.length != 3) {
            throw new IllegalArgumentException(String.format("Wrong count args %s.", Arrays.toString(args)));
        } else if (!Files.isDirectory(Path.of(argName.get("d")))) {
            throw new IllegalArgumentException(String.format("Wrong arg -d %s.", args[0]));
        } else if (!Files.exists(Path.of(argName.get("e").substring(1, 1)))) {
            throw new IllegalArgumentException(String.format("Wrong arg -e %s", args[1]));
        } else if (!Files.exists(Path.of(argName.get("o")))) {
            throw new IllegalArgumentException(String.format("Wrong arg -o %s", args[2]));
        }
    }

    public static void main(String[] args) {
        var zip = new Zip();
        var arg = ArgsName.of(args);
        zip.checkValidate(args, arg);
        zip.packFiles(zip.convertToList(arg.get("d"), arg.get("e")), new File(arg.get("o")));
    }
}
