package ru.job4j.cache;

public class Emulator {
    public static void main(String[] args) {
        var file = new DirFileCache("C:/projects/job4j_design/src/main/java/ru/job4j/cache/");
        file.load("file.txt");
        System.out.println(file.get("file.txt"));
    }
}
