package ru.job4j.algo.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean put(T key, V value) {
        boolean rsl = false;
        if (key != null) {
            root = put(root, key, value);
            rsl = true;
        }
        return rsl;
    }

    private Node put(Node node, T key, V value) {
        Node result = new Node(key, value);
        if (node == null) {
            return result;
        }
        int cp = key.compareTo(node.key);
        if (cp == 0) {
            node.value = value;
        } else if (cp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        updateHeight(node);
        result = balance(node);

        return result;
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = node.left == null ? -1 : node.left.height;
        int rightNodeHeight = node.right == null ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    public boolean remove(T element) {
        boolean result = false;
        V found = get(element);
        if (element != null && root != null && found != null) {
            root = remove(root, element);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T element) {
        if (node == null) {
            return null;
        }
        int comparisonResult = element.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public V get(T key) {
        if (root == null) {
            return null;
        }
        return get(root, key);
    }

    private V get(Node node, T key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        }
        if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    public Set<T> keySet() {
        Set<T> list = new LinkedHashSet<>();
        Node node = root;
        return inSymmetricalOrderKey(node, list);
    }

    private Set<T> inSymmetricalOrderKey(Node node, Set<T> list) {
        if (node != null) {
            inSymmetricalOrderKey(node.left, list);
            list.add(node.key);
            inSymmetricalOrderKey(node.right, list);
        }
        return list;
    }

    public Collection<V> values() {
        Set<V> list = new LinkedHashSet<>();
        Node node = root;
        return inSymmetricalOrderValue(node, list);
    }

    private Collection<V> inSymmetricalOrderValue(Node node, Set<V> list) {
        if (node != null) {
            inSymmetricalOrderValue(node.left, list);
            list.add(node.value);
            inSymmetricalOrderValue(node.right, list);
        }
        return list;
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
