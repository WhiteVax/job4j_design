package ru.job4j.algo.avl;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AvlTreeTest {
    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        AvlTree<String> tree = new AvlTree<>();
        assertThat(tree.insert("first")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly("first");
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        AvlTree<String> tree = new AvlTree<>();
        assertThat(tree.insert("first")).isTrue();
        assertThat(tree.insert("second")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void whenAddElementThenContainElementOk() {
        AvlTree<String> tree = new AvlTree<>();
        tree.insert("first");
        tree.insert("second");
        tree.insert("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 8, 7}) {
            tree.insert(element);
        }
        assertThat(tree.maximum()).isEqualTo(8);
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.insert(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7}) {
            tree.insert(element);
        }
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void whenSymmetricalOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inSymmetricalOrder()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPreOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void whenRemoveNullThenFalse() {
        AvlTree<Integer> tree = new AvlTree<>();
        boolean result = tree.remove(null);
        assertThat(result).isFalse();
    }

    @Test
    void whenAVLNullThenFalse() {
        AvlTree<Integer> tree = new AvlTree<>();
        boolean result = tree.remove(3);
        assertThat(result).isFalse();
    }

    @Test
    void whenAVLRemoveSomeElementsAndCheckBSTThenTrue() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        assertThat(tree.remove(3)).isTrue();
        assertThat(tree.remove(5)).isTrue();
        assertThat(tree.remove(6)).isTrue();
        tree.insert(1);
        assertThat(tree.contains(1)).isTrue();
        assertThat(tree.contains(3)).isFalse();
        assertThat(tree.minimum()).isEqualTo(1);
        assertThat(tree.maximum()).isEqualTo(7);
        assertThat(tree.inPostOrder()).hasSize(3);
    }


    @Test
    void whenAVLClearThenTrue() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.clear();
        assertThat(tree.inPostOrder()).isEmpty();
    }

    @Test
    void whenAVLAddClearAddThenTrue() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(3);
        tree.insert(6);
        tree.clear();
        tree.insert(3);
        assertThat(tree.contains(3)).isTrue();
    }

    @Test
    void whenAVLAddFromGreatestThenChekOrderElements() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(9);
        tree.insert(8);
        tree.insert(7);
        tree.insert(6);
        tree.insert(5);
        tree.insert(4);
        assertThat(tree.inSymmetricalOrder()).containsExactly(4, 5, 6, 7, 8, 9, 10);
    }


    @Test
    void whenAVLAddFromGreatestThenRemoveAndChekSymmetricalOrderElements() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(9);
        tree.insert(8);
        tree.insert(7);
        tree.insert(6);
        tree.insert(5);
        tree.insert(4);
        tree.remove(4);
        tree.remove(6);
        tree.remove(9);
        tree.remove(7);
        tree.remove(5);
        assertThat(tree.inSymmetricalOrder()).containsExactly(8, 10);
    }

    @Test
    void whenRemoveThenTreeStillBalanced() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i <= 10; i++) {
            tree.insert(i);
        }
        tree.remove(4);
        tree.remove(7);
        tree.remove(9);
        assertThat(tree.inSymmetricalOrder())
                .containsExactly(1, 2, 3, 5, 6, 8, 10);
    }

    @Test
    void whenLeftCaseThenRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(9);
        tree.insert(8);
        assertThat(tree.inPreOrder())
                .containsExactly(9, 8, 10);
    }

    @Test
    void whenRightCaseThenRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(8);
        tree.insert(9);
        tree.insert(10);

        assertThat(tree.inPreOrder())
                .containsExactly(9, 8, 10);
    }
}