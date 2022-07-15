package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    static List<String> list = new ArrayList<>();

    private static void validate(ArgsName argsName) {
        if (!Files.exists(Path.of(argsName.get("path")))) {
            throw new IllegalArgumentException(String.format("Сheck the file %s.", argsName.get("path")));
        } else if (!argsName.get("delimiter").matches(".{1}")) {
            throw new IllegalArgumentException(String.format("Wrong delimiter %s.", argsName.get("delimiter")));
        } else if (!argsName.get("out").matches("\\w+") && !Files.exists(Path.of(argsName.get("out")))) {
            throw new IllegalArgumentException(String.format("Wrong arg out %s.", argsName.get("out")));
        } else if (!argsName.get("filter").matches("\\w+,\\w+")) {
            throw new IllegalArgumentException(String.format("Wrong arg filter %s.", argsName.get("filter")));
        }
    }

    public void handle(ArgsName arg) {
        validate(arg);
        try (var scanner = new Scanner(new FileInputStream(arg.get("path")))
                .useDelimiter(arg.get("delimiter"))) {
            while (scanner.hasNext()) {
                if ("name,age".equals(arg.get("filter"))) {
                    list.add(String.format(("%s;%s"), scanner.next(), scanner.next()));
                    scanner.nextLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out(arg.get("out"));
    }

    public void out(String arg) {
        if ("stdout".equals(arg)) {
            list.forEach(System.out::println);
        } else if (Files.exists(Path.of(arg))) {
            saveFile(arg);
        }
    }

    public void saveFile(String target) {
        try (var file = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            list.forEach(e -> {
                file.write(e);
                file.write(System.lineSeparator());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        var arg = ArgsName.of(args);
        var reader = new CSVReader();
        reader.handle(arg);
    }
}
