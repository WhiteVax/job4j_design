package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Warehouse implements Store {
    private static final  int PERCENT_WAREHOUSE = 25;
    private static final  int PERCENT_TRASH = 100;

    private static final Predicate<Float> FILTER = f -> f < PERCENT_WAREHOUSE
            && f < PERCENT_TRASH;

    private final List<Food> list = new ArrayList<>();

    @Override
    public boolean check(Food food) {
        return FILTER.test(calculatePercent(food));
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (check(food)) {
            list.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getList() {
        return new ArrayList<>(list);
    }
}
