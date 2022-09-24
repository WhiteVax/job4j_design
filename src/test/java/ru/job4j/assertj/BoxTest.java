package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    public void whenIsSphere() {
        var box = new Box(0, 10);
        var name = box.whatsThis();
        assertThat(name).isNotNull()
                .isEqualTo("Sphere");
    }

    @Test
    public void whenIsUnknown() {
        var box = new Box(0, 0);
        assertThat(box.whatsThis()).isNotNull()
                .isEqualTo("Unknown object");
    }

    @Test
    public void whenGetNumberOfVertices() {
        var box = new Box(0, 10);
        assertThat(box.getNumberOfVertices()).isEven()
                .isEqualTo(0);
    }

    @Test
    public void whenGetNumberOfVerticesIsNegative() {
        var box = new Box(-10, 0);
        assertThat(box.getNumberOfVertices()).isNegative()
                .isEqualTo(-1);
    }

    @Test
    public void whenExist() {
        var box = new Box(10, 0);
        assertThat(box.isExist()).isNotNull()
                .isFalse();
    }

    @Test
    public void whenExistIsTrue() {
        var box = new Box(0, 10);
        assertThat(box.isExist()).isNotNull()
                .isTrue();
    }

    @Test
    public void whenGetArea() {
        var box = new Box(0, 10);
        assertThat(box.getArea()).isNotIn()
                .isEqualTo(1256.637, withPrecision(0.01));
    }
}