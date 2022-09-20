package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Shop implements Store {
    private static final  int PERCENT_DISCOUNT = 75;
    private static final  int PERCENT_FOOD = 25;
    private static final  int FOOD_OVERDUE = 100;

    private static final Predicate<Float> FILTER = percent -> percent >= PERCENT_FOOD
            && percent < FOOD_OVERDUE;

    private final List<Food> list = new ArrayList<>();

    @Override
    public boolean check(Food food) {
        return FILTER.test(calculatePercent(food));
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (check(food)) {
            if (calculatePercent(food) > PERCENT_DISCOUNT) {
                setPrice(food);
            }
            list.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getList() {
        return new ArrayList<>(list);
    }

    public void setPrice(Food food) {
        food.setPrice(food.getPrice() * (1 - (food.getDiscount() / 100)));
    }
}
