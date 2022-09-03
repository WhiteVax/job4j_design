package ru.job4j.gc.prof;

import java.util.Random;
import java.util.Scanner;

public class Start {

    private RandomArray randomArray;

    public static void main(String[] args) {
        var start = new Start();
        start.show();
        try (var scanner = new Scanner(System.in)) {
            var work = true;
            while (work) {
                int i = scanner.nextInt();
                System.out.println(i);
                start.run(i);
                start.show();
                if (i == 5) {
                    System.out.println("5.Выход.");
                    System.out.println("------------------------------");
                    work = false;
                }
            }
        }
    }

    public void show() {
        System.out.println("1.Создание массива.");
        System.out.println("2.Сортировка пузырьком.");
        System.out.println("3.Сортировка вставками.");
        System.out.println("4.Сортировка слиянием.");
        System.out.println("5.Выход.");
        System.out.println("------------------------------");
    }

    public void run(int i) {
        if (i == 1) {
            System.out.println("1.Создание массива.");
            var random = new RandomArray(new Random());
            random.insert(300_000);
            randomArray = random;
            System.out.println("------------------------------");
        } else if (i == 2) {
            System.out.println("2.Сортировка пузырьком.");
            var bubleSort = new BubbleSort();
            bubleSort.sort(randomArray);
            System.out.println("------------------------------");
        } else if (i == 3) {
            System.out.println("3.Сортировка вставками.");
            var insertSort = new InsertSort();
            insertSort.sort(randomArray);
            System.out.println("------------------------------");
        } else if (i == 4) {
            System.out.println("4.Сортировка слиянием.");
            var mergeSort = new MergeSort();
            mergeSort.sort(randomArray);
            System.out.println("------------------------------");
        }
    }
}
