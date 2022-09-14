package ru.job4j.ood.ocp;

import java.util.Calendar;

public class EmulatorShopMeat extends EmulatorShop {

    @Override
    public Item getItem(int id) {
        var item = map.get(id);
        /*
        Добавляем проверку срока годности товара
         */
        return item;
    }
}
