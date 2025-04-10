package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/result.txt")) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    int number = (i + 1) * (j + 1);
                    out.write(String.format("%d ", number).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
