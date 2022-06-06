package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (var reader = new BufferedReader(new FileReader(file))) {
            list = reader.lines().filter(e -> e.matches("^.+\\s404\\s.+"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                )
        )) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var logFilter = new LogFilter();
        List<String> log = logFilter.filter("C:\\projects"
                + "\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\log.txt");
        log.forEach(System.out::println);
        logFilter.save(log, "C:\\projects"
                + "\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\404.txt");
    }
}
