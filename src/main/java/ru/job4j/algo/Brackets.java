package ru.job4j.algo;

import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        var stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char el = stack.pop();
                if (c == ')' && el != '(') {
                    return false;
                }
                if (c == ']' && el != '[') {
                    return false;
                }
                if (c == '}' && el != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}