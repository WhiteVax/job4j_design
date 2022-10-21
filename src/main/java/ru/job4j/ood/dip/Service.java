package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.List;

/**
 * Нарушение DIP
 * Хранилище зависит от реализации, а не абстракции
 */
public class Service {
    List<User> store = new ArrayList<>();

    /**
     * Привязка к конкретному типу, а не к абстрактному
     */
    public void add(User user) {
        store.add(user);
    }
}
