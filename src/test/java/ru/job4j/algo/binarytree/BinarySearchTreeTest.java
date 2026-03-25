package ru.job4j.algo.binarytree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly("first");
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.put("second")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void whenAddElementThenContainElementOk() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("first");
        tree.put("second");
        tree.put("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 8, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(8);
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7}) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void whenSymmetricalOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inSymmetricalOrder()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPreOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void whenRemoveNullThenFalse() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        boolean result = tree.remove(null);
        assertThat(result).isFalse();
    }

    @Test
    void whenBSTNullThenFalse() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        boolean result = tree.remove(3);
        assertThat(result).isFalse();
    }

    @Test
    void whenBSTRemoveSomeElementsAndCheckBSTThenTrue() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(3);
        tree.put(4);
        tree.put(5);
        tree.put(6);
        tree.put(7);
        assertThat(tree.remove(3)).isTrue();
        assertThat(tree.remove(5)).isTrue();
        assertThat(tree.remove(6)).isTrue();
        tree.put(1);
        assertThat(tree.contains(1)).isTrue();
        assertThat(tree.contains(3)).isFalse();
        assertThat(tree.minimum()).isEqualTo(1);
        assertThat(tree.maximum()).isEqualTo(7);
        assertThat(tree.inPostOrder()).hasSize(3);
    }


    @Test
    void whenBSTClearThenTrue() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(3);
        tree.put(4);
        tree.put(5);
        tree.put(6);
        tree.clear();
        assertThat(tree.inPostOrder()).isEmpty();
    }

    @Test
    void whenBSTAddClearAddThenTrue() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(3);
        tree.put(6);
        tree.clear();
        tree.put(3);
        assertThat(tree.contains(3)).isTrue();
    }
}