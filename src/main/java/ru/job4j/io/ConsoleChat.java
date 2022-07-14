package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        validate(path, botAnswers);
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private void validate(String path, String botAnswers) {
        if (!Files.exists(Path.of(path))) {
            throw new IllegalArgumentException(String.format("Check the correctness of the file %s.", path));
        } else if (!Files.exists(Path.of(botAnswers))) {
            throw new IllegalArgumentException(String.format("Check the correctness of the file %s.", botAnswers));
        }
    }

    public void run() {
        List<String> lines = readPhrases();
        List<String> log = new ArrayList<>();
        var random = new Random();
        var scanner = new Scanner(System.in);
        var botWork = false;
        while (scanner.hasNext()) {
            var answer = scanner.nextLine();
            log.add(answer);
            if (answer.equals(CONTINUE)) {
                botWork = true;
            } else if (answer.equals(STOP)) {
                botWork = false;
            } else if (answer.equals(OUT)) {
                saveLog(log);
                return;
            }
            if (botWork) {
                var bot = lines.get(random.nextInt(lines.size() - 1));
                System.out.println(bot);
                log.add(bot);
            }
        }
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Path.of(botAnswers), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (var print = new PrintWriter(new FileWriter(this.path, StandardCharsets.UTF_8, true))) {
            log.forEach(e -> {
                print.write(e);
                print.write(System.lineSeparator());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chatLog.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
