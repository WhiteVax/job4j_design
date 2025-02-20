package ru.job4j.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class SimpleMapTest {
    private final SimpleMap<Integer, String> map = new SimpleMap<>();

    @BeforeEach
    void setUp() {
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
    }

    @Test
    void checkSimpleIterator() {
        assertThat(map).hasSize(4).contains(1, 2, 3, 4);
    }

    @Test
    void whenCheckGet() {
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(4);
        assertThat(map.get(5)).isNull();
        assertThat(map).hasSize(4);
    }

    @Test
    void whenCheckPut() {
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map).hasSize(5);
        assertThat(map.put(8, "8")).isFalse();
        assertThat(map).hasSize(5);
        assertThat(map.put(1, "10")).isFalse();
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemove() {
        assertThat(map.remove(2)).isTrue();
        assertThat(map).hasSize(3);
        assertThat(map.remove(2)).isFalse();
        assertThat(map).hasSize(3);
        assertThat(map.remove(5)).isFalse();
        assertThat(map).hasSize(3);
    }

    @Test
    void whenCheckIterator() {
        map.remove(2);
        map.remove(3);
        map.put(null, "0000");
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isNull();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(4);
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenConcurrentIteratorAdd() {
        Iterator<Integer> iterator = map.iterator();
        map.put(0, "0");
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenConcurrentIteratorRemove() {
        Iterator<Integer> iterator = map.iterator();
        map.remove(1);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenNotConcurrentIteratorGet() {
        Iterator<Integer> iterator = map.iterator();
        map.get(1);
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenMapExpand() {
        map.put(null, "0000");
        assertThat(map.put(15, "15")).isTrue();
        assertThat(map).hasSize(6);
        assertThat(map.put(8, "8")).isTrue();
        assertThat(map.put(16, "16")).isFalse();
        assertThat(map.get(4)).isEqualTo("4");
        assertThat(map.get(8)).isEqualTo("8");
        assertThat(map.get(15)).isEqualTo("15");
        assertThat(map).hasSize(7).contains(null, 1, 2, 3, 4, 8, 15);
    }

    @Test
    void whenCheckPutKeyNull() {
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckGetKeyNull() {
        map.put(null, "0000");
        assertThat(map.get(null)).isEqualTo("0000");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemoveKeyNull() {
        map.put(null, "0000");
        assertThat(map.remove(null)).isTrue();
        assertThat(map).hasSize(4);
    }

    @Test
    void whenCheckPutZeroAndNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.put(0, "0")).isFalse();
    }

    @Test
    void whenCheckPutNullAndZero() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.put(null, "0000")).isFalse();
    }

    @Test
    void whenCheckGetZeroAndNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.get(0)).isNull();
    }

    @Test
    void whenCheckGetNullAndZero() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.get(null)).isNull();
    }


    @Test
    public void whenAddDuplicate() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        assertThat(map.put(6923, "Elena")).isFalse();
    }

    @Test
    public void whenAddTrue() {
        var map = new SimpleMap<>();
        assertThat(map.put(6923, "Elena")).isTrue();
    }

    @Test
    public void whenGetSuccessful() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        map.put(793, "Petr");
        map.put(237, "Ivan");
        map.put(3689, "Boris");
        assertThat(map.get(237)).isEqualTo("Ivan");
    }

    @Test
    public void whenGetNull() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        map.put(793, "Petr");
        map.put(237, "Ivan");
        map.put(3689, "Boris");
        assertThat(map.get(92)).isNull();
    }

    @Test
    public void whenDeleteTru() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        map.put(793, "Petr");
        map.put(237, "Ivan");
        assertThat(map.remove(793)).isTrue();
    }

    @Test
    public void whenDeleteFalse() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        map.put(793, "Petr");
        map.put(237, "Ivan");
        assertThat(map.remove(0)).isFalse();
    }

    @Test
    public void whenMultiIterator() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        map.put(793, "Petr");
        map.put(237, "Ivan");
        map.put(3689, "Boris");
        var it = map.iterator();
        assertThat(it.hasNext()).isTrue();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    public void whenException() {
        var map = new SimpleMap<>();
        assertThatThrownBy(() -> map.iterator().next());
    }
}
