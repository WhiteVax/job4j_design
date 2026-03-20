package ru.job4j.algo.newcoll.tree;

import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

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

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     *
     * @param root   корень дерева
     * @param parent ключ узла-родителя
     * @param child  ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException("Root is null.");
        }
        Optional<Node<T>> parentNode = findByKey(root, parent);
        Optional<Node<T>> childNode = findByKey(root, child);
        if (parentNode.isPresent() && childNode.isEmpty()) {
            parentNode.get().getChildren().add(new Node<>(child));
            return true;
        }
        return false;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Root is null.");
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        Optional<Node<T>> rsl = Optional.empty();
        stack.push(root);
        Node<T> current;
        while (true) {
            try {
                current = stack.pop();
            } catch (NoSuchElementException e) {
                break;
            }
            if (key.equals(current.getValue())) {
                rsl = Optional.of(current);
                break;
            }
            for (var el : current.getChildren()) {
                stack.push(el);
            }
        }
        return rsl;
    }


    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Root is null.");
        }

        if (key.equals(root.getValue())) {
            return Optional.of(root);
        }

        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        Node<T> current;

        while (true) {
            try {
                current = queue.poll();
            } catch (NoSuchElementException e) {
                break;
            }

            var it = current.getChildren().iterator();

            while (it.hasNext()) {
                var el = it.next();
                if (key.equals(el.getValue())) {
                    it.remove();
                    return Optional.of(el);
                }
                queue.push(el);
            }

        }
        return Optional.empty();
    }


}