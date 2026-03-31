package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void addFoods(List<Food> foods) {
        for (var food : foods) {
            for (var store : stores) {
                if (store.check(food)) {
                    store.add(food);
                    break;
                }
            }
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (var store : stores) {
            foods.addAll(store.getList());
            store.clear();
        }
        addFoods(foods);
    }
}