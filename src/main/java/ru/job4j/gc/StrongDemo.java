package ru.job4j.gc;

import java.util.ArrayList;
import java.util.List;

public class StrongDemo {
    public static void main(String[] args) {
        example3();
    }

    private static void example3() {
        List<String> strings = new ArrayList<>();
        while (true) {
            strings.add(String.valueOf(System.currentTimeMillis()));
        }
    }
}
