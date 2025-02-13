package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    public void whenAddAfterLast() {
        ListUtils.addAfter(input, 0, 4);
        assertThat(input).hasSize(3).containsSequence(1, 4, 3);
    }

    @Test
    public void whenRemoveAllElements() {
        List<Integer> list = List.of(1, 2, 3);
        input.add(2);
        ListUtils.removeAll(input, list);
        assertThat(input).hasSize(0);
    }

    @Test
    public void whenRemoveIf() {
        ListUtils.removeIf(input, x -> x >= 0);
        assertThat(input).hasSize(0);
    }

    @Test
    public void whenReplaceIf() {
        ListUtils.replaceIf(input, i -> i > 0, 0);
        assertThat(input).hasSize(2).contains(0, 0);
    }
}
