package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        var lists = new CyclicIterator<>(nodes);
        while (source.hasNext() && lists.hasNext()) {
            lists.next().add(source.next());
        }
    }
}