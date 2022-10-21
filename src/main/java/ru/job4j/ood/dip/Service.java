package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.List;

/**
 * Нарушение DIP
 * Сервис зависит от реализации, а не абстракции.
 * (Если используется как хранилище, то принцип не нарушается.)
 */
public class Service {
    List<User> store = new ArrayList<>();

    public void add(User user) {
        if (user.check()) {
            store.add(user);
        }
    }


}
