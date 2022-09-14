package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.List;

public class EmulatorHardWareStore extends EmulatorShop {
    List<Item> guarantee = new ArrayList<>();

    @Override
    public void addItem(int id, Item item) {
        map.put(id, item);
        guarantee.add(item);
    }

    public List<Item> getAllDevices() {
        return new ArrayList<>(guarantee);
    }
}
