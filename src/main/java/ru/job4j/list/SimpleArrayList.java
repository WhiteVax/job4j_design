package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void growth() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            growth();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T setValue = container[index];
        container[index] = newValue;
        modCount++;
        return setValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T deletedValue = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return deletedValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            int pointer = 0;

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                boolean rsl = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                } else if (container.length > pointer && container[pointer] != null) {
                    rsl = true;
                }
                return rsl;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[pointer++];
            }
        };
    }
}

