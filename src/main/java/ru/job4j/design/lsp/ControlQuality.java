package ru.job4j.design.lsp;

import java.util.List;

public class ControlQuality {
    List<Store> stores;

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
}
