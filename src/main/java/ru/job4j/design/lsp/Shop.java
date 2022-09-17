package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private List<Food> list = new ArrayList<>();

    @Override
    public void add(Food food) {
        list.add(food);
    }

    @Override
    public List<Food> getList() {
        return list;
    }
}
