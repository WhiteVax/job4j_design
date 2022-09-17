package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;

public interface Store {
    default float calculatePercent(Food food) {
        long totalShelfLife = ChronoUnit.DAYS.between(food.getCreateDate(),
                food.getExpireDate());
        long expireDays = ChronoUnit.DAYS.between(food.getCreateDate(),
                LocalDate.now());
        return (float) expireDays / totalShelfLife * 100;
    }

    default boolean check(Predicate<Food> filter, Food food) {
        return filter.test(food);
    }

    public void add(Food food);
    public List<Food> getList();
}
