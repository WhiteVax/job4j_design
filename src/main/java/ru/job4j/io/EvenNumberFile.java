package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("C:\\projects\\"
                + "job4j_design\\src\\main\\java\\ru\\job4j\\io\\even.txt")) {
            Scanner out = new Scanner(in);
            while (out.hasNext()) {
                System.out.println(out.nextInt() % 2 == 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
