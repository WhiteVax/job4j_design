package ru.job4j.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallestRangeFinder {
    public static int[] findSmallestRange(int[] nums, int k) {
        int start = -1, end = -1, minRange = Integer.MAX_VALUE, left = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            while (map.size() >= k) {
                if (map.size() == k) {
                    int range = i - left;
                    if (range < minRange) {
                        minRange = range;
                        start = left;
                        end = i;
                    }
                }

                int leftNums = nums[left];
                map.put(leftNums, map.get(leftNums) - 1);
                if (map.get(leftNums) == 0) {
                    map.remove(leftNums);
                }
                left++;
            }
        }

        if (start == -1) {
            return null;
        }
        return new int[]{start, end};
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}