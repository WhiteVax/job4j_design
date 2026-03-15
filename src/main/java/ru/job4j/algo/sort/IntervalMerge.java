package ru.job4j.algo.sort;

import java.util.ArrayList;

public class IntervalMerge {
    public int[][] merge(int[][] intervals) {
        shortArray(intervals);
        int[] current = intervals[0];
        ArrayList<int[]> result = new ArrayList<>();
        result.add(current);
        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                current = next;
                result.add(current);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    private void shortArray(int[][] intervals) {
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1][1] > intervals[i][1]) {
                int[] temp = intervals[i - 1];
                intervals[i - 1] = intervals[i];
                intervals[i] = temp;
            }
        }
    }
}
