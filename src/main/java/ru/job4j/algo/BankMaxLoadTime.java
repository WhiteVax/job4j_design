package ru.job4j.algo;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List<int[]> visitTimes) {
        visitTimes.sort(Comparator.comparing(o -> o[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int maxLoadTime = 0, maxStart = 0, maxEnd = 0;

        for (var el : visitTimes) {
            int start = el[0];
            int end = el[1];
            while (!pq.isEmpty() && pq.peek() < start) {
                pq.poll();
            }
            pq.add(end);

            if (pq.size() > maxLoadTime) {
                maxLoadTime = pq.size();
                maxStart = start;
                maxEnd = pq.peek();
            }
        }
        return new int[]{maxStart, maxEnd};
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event o) {
            if (this.time == o.time) {
                return this.type == EventType.ARRIVAL ? -1 : 1;
            }
            return Integer.compare(this.time, o.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}