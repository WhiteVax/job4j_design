package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> head;

    private Node<E> tail;

    private int size = 0;

    private int modCount;

    @Override
    public void add(E value) {
        final Node<E> newNode = new Node<>(null, value, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        E rsl;
        if (index == 0) {
            rsl = head.item;
        } else if (index == size - 1) {
            rsl = tail.item;
        } else if (index > size / 2) {
            rsl = fromTheTailGet(index);
        } else {
            rsl = fromTheHeadGet(index);
        }
        return rsl;
    }

    public E fromTheTailGet(int index) {
        E rsl = null;
        Node<E> node = tail;
        for (int i = size - 1; i > 0; i--) {
            if (i == index) {
                rsl = node.getItem();
            }
            node = node.prev;
        }
        return rsl;
    }

    public E fromTheHeadGet(int index) {
        E rsl = null;
        Node<E> node = head;
        for (int i = 0; i <= index; i++) {

            if (i == index) {
                rsl = node.getItem();
            }
            node = node.next;
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            final private int expectedMod = modCount;

            Node<E> node = head;

            @Override
            public boolean hasNext() {
                if (expectedMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                E item = node.getItem();
                if (hasNext()) {
                    node = node.next;
                }
                return item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        public E getItem() {
            return item;
        }
    }
}
