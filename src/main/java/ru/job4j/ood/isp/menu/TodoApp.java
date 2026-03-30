package ru.job4j.ood.isp.menu;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    public static void main(String[] args) {
        var menu = new SimpleMenu();
        var print = new Printer();

        menu.add(Menu.ROOT, "Задача 1", DEFAULT_ACTION);
        menu.add("Задача 1", "Подзадача 1.1", DEFAULT_ACTION);
        menu.add("Подзадача 1.1", "Подзадача 1.1.1", DEFAULT_ACTION);

        print.print(menu);
        menu.select("Задача 1").ifPresent(i -> i.getActionDelegate().delegate());
    }
}
