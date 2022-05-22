package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count > table.length * LOAD_FACTOR) {
            expend();
        }
        int index = indexFor(key.hashCode());
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hasCode) {
        return (hasCode == 0) ? 0 : hasCode ^ (hasCode >>> 16);
    }

    private int indexFor(int has) {
        return (capacity - 1) & hash(has);
    }

    private void expend() {
        capacity *= 2;
        MapEntry<K, V>[] rsl = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            int key = indexFor(table[i].key.hashCode());
            rsl[key] = rsl[i];
        }
        table = Arrays.copyOf(rsl, capacity);
    }

    @Override
    public V get(K key) {
        int index = indexFor(key.hashCode());
        return table[index] == null || !table[index].key.equals(key)
                ? null : table[index].value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(key.hashCode());
        if (table[index] != null) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            int courser = 0;
            final int expectedMod = modCount;

            @Override
            public boolean hasNext() {
                if (expectedMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (courser < table.length && table[courser] == null) {
                    courser++;
                }
                return courser < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[courser++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
