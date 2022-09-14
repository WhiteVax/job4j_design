package ru.job4j.ood.ocp;

import java.util.HashMap;
import java.util.Map;

/**
 * Ошибки OCP
 */

public class EmulatorShop {
    Map<Integer, Item> map = new HashMap<>();

    public Item getItem(int id) {
        return  map.get(id);
    }

    public void addItem(int id, Item item) {
        map.put(id, item);
    }
}
