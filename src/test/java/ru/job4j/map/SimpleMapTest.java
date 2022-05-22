package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SimpleMapTest {

    @Test
    public void whenAddDuplicate() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        assertFalse(map.put(6923, "Elena"));
    }

    @Test
    public void whenAddTrue() {
        var map = new SimpleMap<>();
        assertTrue(map.put(6923, "Elena"));
    }

    @Test
    public void whenGetSuccessful() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        map.put(793, "Petr");
        map.put(237, "Ivan");
        map.put(3689, "Boris");
        assertThat(map.get(237), is("Ivan"));
    }

    @Test
    public void whenGetNull() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        map.put(793, "Petr");
        map.put(237, "Ivan");
        map.put(3689, "Boris");
        assertNull(map.get(92346115));
    }


    @Test
    public void whenDeleteTru() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        map.put(793, "Petr");
        map.put(237, "Ivan");
        assertTrue(map.remove(793));
    }

    @Test
    public void whenDeleteFalse() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        map.put(793, "Petr");
        map.put(237, "Ivan");
        assertFalse(map.remove(0));
    }

    @Test
    public void whenMultiIterator() {
        var map = new SimpleMap<>();
        map.put(6923, "Elena");
        map.put(793, "Petr");
        map.put(237, "Ivan");
        map.put(3689, "Boris");
        var it = map.iterator();
        assertTrue(it.hasNext());
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenException() {
        var map = new SimpleMap<>();
        map.iterator().next();
    }
}
