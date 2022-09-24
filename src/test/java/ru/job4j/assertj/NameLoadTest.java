package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        var nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenArrayIsEmpty() {
        var nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void whenStringDoesNotHaveEqualsSymbol() {
        var nameLoad = new NameLoad();
        var string = "namesurname";
        assertThatThrownBy(() -> nameLoad.parse(string))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenStringStartWithEqualsSymbol() {
        var nameLoad = new NameLoad();
        var string = "=namesurname";
        assertThatThrownBy(() -> nameLoad.parse(string))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenStringEndOnEqualsSymbol() {
        var nameLoad = new NameLoad();
        var string = "namesurname=";
        assertThatThrownBy(() -> nameLoad.parse(string))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMapIsEmpty() {
        var nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class);
    }
}