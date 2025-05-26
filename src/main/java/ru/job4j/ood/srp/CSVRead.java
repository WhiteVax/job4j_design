package ru.job4j.ood.srp;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Нарушения SRP
 */

public class CSVRead {
    private Map<String, Integer> tableHeader = new HashMap<>();
    private final List<String[]> list = new ArrayList<>();
    private final List<String> filter = new ArrayList<>();

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
        try (var scanner = new Scanner(new FileReader(arg.get("path"), StandardCharsets.UTF_8))) {
            int i = 0;
            for (var el : scanner.nextLine().split(arg.get("delimiter"))) {
                tableHeader.put(el, i++);
            }
            while (scanner.hasNext()) {
                list.add(scanner.nextLine().split(arg.get("delimiter")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        filter(arg.get("filter"));
        out(arg.get("out"));
    }

    public void out(String arg) {
        if ("stdout".equals(arg)) {
            filter.forEach(System.out::println);
        } else if (Files.exists(Path.of(arg))) {
            saveFile(arg);
        }
    }

    public void saveFile(String target) {
        try (var file = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            filter.forEach(e -> {
                file.write(e);
                file.write(System.lineSeparator());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filter(String arg) {
        List<Integer> index = new ArrayList<>();
        for (var el : arg.split(",")) {
            if (tableHeader.containsKey(el)) {
                index.add(tableHeader.get(el));
            }
        }
        filter.add(arg.replace(",", ";"));
        for (var el: list) {
            var builder = new StringBuilder();
            for (var  pointer : index) {
                builder.append(el[pointer]);
                if (pointer != index.size() - 1) {
                    builder.append(";");
                }
            }
            filter.add(builder.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        var arg = ArgsName.of(args);
        var reader = new CSVRead();
        reader.handle(arg);
    }
}
