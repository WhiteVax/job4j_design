package ru.job4j.search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Программа для поиска файлов, в каталоге и подкаталогах.
 * Запускается с примерными параметрами -d=c:/ -n=readme.txt -t=name -o=log.txt
 * Ключи
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени,
 * regex по регулярному выражению.
 * -o - результат записать в файл.
 * @Version 1.1
 */

public class SearchFiles {
    private final static String SEARCH_DIRECTORY = "-d";
    private final static String SEARCH_NAME = "-n";
    private final static String SEARCH_TYPE = "-t";
    private final static String SAVE_DIRECTORY = "-o";
    private List<Path> list = new ArrayList<>();

    private final ArgsName argsName;

    public SearchFiles(ArgsName argsName) {
        this.argsName = argsName;
    }

    private void validate() {
        if (!Files.isDirectory(Path.of(argsName.get(SEARCH_DIRECTORY)))) {
            throw new IllegalArgumentException("Wrong in arg.");
        } else if (!argsName.get(SAVE_DIRECTORY).matches("(\\w+)")) {
            throw new IllegalArgumentException("Wrong out arg.");
        }
    }

    public void find() {
        validate();
        var visitor = new Visitor(argsName.get(SEARCH_NAME), argsName.get(SEARCH_TYPE));
        try {
            Files.walkFileTree(Path.of(argsName.get(SEARCH_DIRECTORY)), visitor);
            list = visitor.getPaths();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try (var writer =
                     new BufferedWriter(new PrintWriter(argsName.get(SAVE_DIRECTORY)))) {
            for (var line : list) {
                writer.write(line.toFile().getAbsolutePath());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var argsName = new ArgsName().of(args);
        var search = new SearchFiles(argsName.of(args));
        search.find();
        search.save();
    }
}
