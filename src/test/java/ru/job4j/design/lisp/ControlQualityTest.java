package ru.job4j.design.lisp;

import org.junit.Test;
import ru.job4j.design.lsp.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ControlQualityTest {

    @Test
    public void whenAddFood() {
        var sprite = new Food("sprite", LocalDate.now().plusDays(30),
                LocalDate.now().minusDays(5), 140, 0);
        var water = new Food("water", LocalDate.now().plusDays(5),
                LocalDate.of(2022, 5, 17), 120, 30);
        var bread = new Food("bread", LocalDate.now(),
                LocalDate.now().minusDays(15), 30, 0);
        var shop = new Shop();
        var warehouse = new Warehouse();
        var trash = new Trash();
        var control = new ControlQuality(List.of(shop, warehouse,
                trash));
        control.addFoods(List.of(sprite, bread, water));
        water.setPrice(84);
        assertThat(shop.getList()).isEqualTo(List.of(water));
        assertThat(warehouse.getList()).isEqualTo(List.of(sprite));
        assertThat(trash.getList()).isEqualTo(List.of(bread));
    }
}
