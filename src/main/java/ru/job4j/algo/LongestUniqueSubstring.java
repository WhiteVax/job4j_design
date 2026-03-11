package ru.job4j.algo;

import java.util.HashMap;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        if (str.isEmpty() || str.length() == 1) {
            return str;
        }
        int startIndex = 0, leftIndex = 0, max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.containsKey(c)) {
                leftIndex = map.get(c) + 1;
            }

            map.put(c, i);

            if (i - leftIndex + 1 > max) {
                max = i - leftIndex + 1;
                startIndex = leftIndex;
            }
        }
        return str.substring(startIndex, startIndex + max);
    }
}
