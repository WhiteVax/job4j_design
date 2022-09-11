package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MaxMinTest {
    @Test
    public void whenMax() {
        List<Integer> numbers = List.of(0, 13, 16, 2, -2, 40);
        var max = new MaxMin();
        assertThat(max.max(numbers, Integer::compareTo)).isEqualTo(40);
    }

    @Test
    public void whenMin() {
        List<Integer> numbers = List.of(0, 13, 16, 2, -2, 40);
        var min = new MaxMin();
        assertThat(min.min(numbers, Integer::compareTo)).isEqualTo(-2);
    }
}
