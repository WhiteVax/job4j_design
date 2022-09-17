package ru.job4j.design.lsp;

import java.util.List;
import java.util.function.Predicate;

public class ControlQuality {
    private Store shop;
    private Store warehouse;
    private Store trash;

    public ControlQuality(Store shop, Store warehouse, Store trash) {
        this.shop = shop;
        this.warehouse = warehouse;
        this.trash = trash;
    }

    public Store getShop() {
        return shop;
    }

    public Store getWarehouse() {
        return warehouse;
    }

    public Store getTrash() {
        return trash;
    }

    public void addFoods(Predicate<Food> filterShop,
                         Predicate<Food> filterWarehouse,
                         Predicate<Food> filterTrash, List<Food> foods) {
        for (var food : foods) {
            if (shop.check(filterShop, food)) {
                if (shop.calculatePercent(food) > 75) {
                    food.setDiscount(10);
                    food.setPrice((int) (food.getPrice() * 0.9));
                }
                shop.add(food);
            } else if (warehouse.check(filterWarehouse, food)) {
                warehouse.add(food);
            } else if (trash.check(filterTrash, food)) {
                trash.add(food);
            }
        }
    }
}
