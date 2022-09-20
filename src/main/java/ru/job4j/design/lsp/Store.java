package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Store {
    default float calculatePercent(Food food) {
        long totalShelfLife = ChronoUnit.DAYS.between(food.getCreateDate(),
                food.getExpireDate());
        long expireDays = ChronoUnit.DAYS.between(food.getCreateDate(),
                LocalDate.now());
        return (float) expireDays / totalShelfLife * 100;
    }

    boolean check(Food food);
    boolean add(Food food);
    List<Food> getList();
}
