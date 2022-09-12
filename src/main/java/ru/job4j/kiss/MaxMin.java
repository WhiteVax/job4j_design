package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return find(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return find(value, comparator.reversed());
    }

    private <T> T find(List<T> value, Comparator<T> comparator) {
        T rsl = null;
        if (!value.isEmpty()) {
            rsl = value.get(0);
            for (var v: value) {
                if (comparator.compare(v, rsl) > 0) {
                    rsl = v;
                }
            }
        }
        return rsl;
    }
}
