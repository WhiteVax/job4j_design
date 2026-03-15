package ru.job4j.algo;

import org.junit.jupiter.api.Test;
import ru.job4j.algo.sort.Merge;

import static org.assertj.core.api.Assertions.*;

class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenSortedThenTwoSize() {
        int[] array = {10, 4};
        assertThat(Merge.mergesort(array)).containsExactly(4, 10);
    }

    @Test
    void whenSortedEmptyArray() {
        int[] array = {};
        assertThat(Merge.mergesort(array)).isEmpty();
    }
}