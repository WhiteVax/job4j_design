package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    public static final String MENU = """
            Введите 1 для вода названия файла.
            Введите 2 для загрузки данных в кеш.
            Введите 3 для получения данных с кеша по ключу.
            Введите 4 для выхода с программы.
            """;

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            var dir = new DirFileCache("C:/projects/job4j_design/src/main/java/ru/job4j/cache/");
            run(scanner, dir);
        }
    }

    private static void run(Scanner scanner, DirFileCache dirFileCache) {
        String fileName = null;
        var working = true;
        while (working) {
            System.out.print(MENU);
            int userChoice = scanner.nextInt();
            if (userChoice == 1) {
                System.out.println("Введите имя файла с расширением.");
                fileName = scanner.next();
            } else if (userChoice == 2) {
                System.out.println("Загружаются данные в кеш.");
                dirFileCache.load(fileName);
            } else if (userChoice == 3) {
                System.out.println("Получение данных с кеша по ключу.");
                System.out.println("Ведите ключ кеша.");
                var ask = scanner.next();
                System.out.println(dirFileCache.get(ask));
            } else if (userChoice == 4) {
                System.out.print("Выход.");
                working = false;
            }
        }
    }
}
