package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size = 0;
    private int modCount;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
        modCount++;
    }

    public boolean revert() {
        boolean rsl = false;
        if (head != null && head.next != null) {
            rsl = true;
            Node<T> current = head.next;
            head.next = null;
            while (current != null) {
                Node<T> next = current.next;
                current.next = head;
                head = current;
                current = next;
            }
        }
        return rsl;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> fist = head.next;
        T deleteItem = head.value;
        head.value = null;
        head.next = null;
        head = fist;
        size--;
        modCount++;
        return deleteItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final private int expectedMod = modCount;

            Node<T> node = head;

            @Override
            public boolean hasNext() {
                if (expectedMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
