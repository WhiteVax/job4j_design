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
        if (count >= table.length * LOAD_FACTOR) {
            expend();
        }
        boolean rsl = false;
        int index = indexFor(Objects.hashCode(key));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hasCode) {
        return hasCode ^ (hasCode >>> 16);
    }

    private int indexFor(int has) {
        return  hash(has) & (capacity - 1);
    }

    private void expend() {
        capacity = capacity<< 1;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int newIndex = indexFor(Objects.hashCode(entry.key));
                newTable[newIndex] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexFor(Objects.hashCode(key));
        V result = null;
        if (keyEqual(table[index], key)) {
            result = table[index].value;
        }
        return result;
    }

    private boolean keyEqual(MapEntry<K, V> entry, K key) {
        boolean result = false;
        if (entry != null) {
            result = Objects.hashCode(entry.key) == Objects.hashCode(key) &&
                    Objects.equals(entry.key, key);
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(Objects.hashCode(key));
        if (keyEqual(table[index], key)) {
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
