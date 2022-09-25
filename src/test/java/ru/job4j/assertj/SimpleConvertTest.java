package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        var simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }


    @Test
    void whenToList() {
        var simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "third");
        assertThat(list).hasSize(3)
                .asList()
                .doesNotContain("first", Index.atIndex(2))
                .contains("first", "second", "third");
    }

    @Test
    void whenToSet() {
        var simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "third",
                "last", "last");
        assertThat(set).isNotEmpty()
                .hasSize(4)
                .doesNotHaveDuplicates()
                .containsAll(Set.of("first", "second", "third",
                        "last"))
                .isInstanceOf(Set.class);

    }

    @Test
    void whenToMap() {
        var simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("zero", "first",
                "second", "third");
        Map<String, Integer> expected = Map.of("zero", 0, "first", 1,
                "second", 2, "third", 3);
        assertThat(map).hasSize(4)
                .contains(entry("zero", 0))
                .containsAllEntriesOf(expected);
    }
}