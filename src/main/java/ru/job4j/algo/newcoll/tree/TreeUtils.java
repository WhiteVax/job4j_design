package ru.job4j.algo.newcoll.tree;

import ru.job4j.collection.SimpleQueue;

import java.util.*;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root is null.");
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        int count = 0;
        Node<T> current;
        while (true) {
            try {
                current = queue.poll();
            } catch (NoSuchElementException e) {
                break;
            }
            count++;
            for (var el : current.getChildren()) {
                queue.push(el);
            }
        }
        return count;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     *
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public List<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root is null.");
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        List<T> list = new ArrayList<>();
        Node<T> current;

        while (true) {
            try {
                current = queue.poll();
                list.add(current.getValue());
            } catch (NoSuchElementException e) {
                break;
            }
            for (var el : current.getChildren()) {
                queue.push(el);
            }
        }
        return list;
    }
}