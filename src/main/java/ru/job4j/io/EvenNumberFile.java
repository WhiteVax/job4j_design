package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("src\\main\\java\\ru\\job4j\\io\\even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                System.out.println(read % 2 == 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
