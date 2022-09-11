package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    private static final String MENU = """
            Введите 1 для вода пути директории.
            Введите 2 для загрузки данных в кеш.
            Введите 3 для получения данных с кеша по ключу.
            Введите 4 для выхода с программы.
            """;
    private static final String SELECT_DIR = "Введите путь директории.";
    private static final String SELECT_PUT = "Введите имя файла с расширением.";
    private static final String SELECT_GET = "Получение данных с кеша по ключу. Ведите ключ кеша.";
    private static final String SELECT_EXIT = "Выход.";
    private static final int DIR = 1;
    private static final int PUT = 2;
    private static final int GET = 3;
    private static final int EXIT = 4;
    private static DirFileCache dirFileCache;

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            run(scanner);
        }
    }

    private static void run(Scanner scanner) {
        var working = true;
        while (working) {
            System.out.print(MENU);
            int userChoice = scanner.nextInt();
            if (userChoice == DIR) {
                System.out.println(SELECT_DIR);
                var dir = scanner.next();
                dirFileCache = new DirFileCache(dir);
            } else if (userChoice == PUT) {
                System.out.println(SELECT_PUT);
                var fileName = scanner.next();
                dirFileCache.get(fileName);
            } else if (userChoice == GET) {
                System.out.println(SELECT_GET);
                var ask = scanner.next();
                System.out.println(dirFileCache.get(ask));
            } else if (userChoice == EXIT) {
                System.out.print(SELECT_EXIT);
                working = false;
            }
        }
    }
}
