package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            int level = (int) item.getNumber()
                    .chars()
                    .filter(ch -> ch == '.')
                    .count();
            String indent = "----".repeat(Math.max(0, level - 1));
            System.out.println(indent + item.getNumber() + " " + item.getName() + ".");
        }
    }
}
