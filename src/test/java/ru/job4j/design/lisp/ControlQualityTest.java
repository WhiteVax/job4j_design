package ru.job4j.design.lisp;

import org.junit.Test;
import ru.job4j.design.lsp.*;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

public class ControlQualityTest {

    @Test
    public void whenAddFood() {
        var sprite = new Food("sprite", LocalDate.now().plusDays(30),
                LocalDate.now().minusDays(5), 140, 0);
        var water = new Food("water", LocalDate.now().plusDays(5),
                LocalDate.of(2022, 5, 17), 100, 0);
        var bread = new Food("bread", LocalDate.now().minusDays(5),
                LocalDate.now(), 30, 0);
        var shop = new Shop();
        var warehouse = new Warehouse();
        var trash = new Trash();
        Predicate<Food> filterShop = e -> shop.calculatePercent(e) > 25
                && shop.calculatePercent(e) < 100;
        Predicate<Food> filterWarehouse =
                e -> warehouse.calculatePercent(e) < 25
                        && warehouse.calculatePercent(e) > 0;
        Predicate<Food> filterTrash = e -> warehouse.calculatePercent(e) > 100
                || warehouse.calculatePercent(e) <= 0;
        var control = new ControlQuality(shop, warehouse, trash);
        control.addFoods(filterShop, filterWarehouse, filterTrash,
                List.of(sprite, bread, water));
        water.setPrice(90);
        water.setDiscount(10);
        assertThat(control.getShop().getList()).isEqualTo(List.of(water));
        assertThat(control.getWarehouse().getList()).isEqualTo(List.of(sprite));
        assertThat(control.getTrash().getList()).isEqualTo(List.of(bread));
    }
}
