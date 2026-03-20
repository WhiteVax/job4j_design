package ru.job4j.algo.binarytree;

import java.util.ArrayList;
import java.util.List;

public class PrintTree {

    private PrintTree() {
        throw new IllegalStateException("Utility class");
    }

    public static String getTreeDisplay(VisualNode root) {
        StringBuilder buffer = new StringBuilder();
        List<List<String>> lines = new ArrayList<>();
        List<VisualNode> level = new ArrayList<>();
        List<VisualNode> next = new ArrayList<>();
        level.add(root);
        int nodeCount = 1;
        int widest = 0;
        while (nodeCount != 0) {
            nodeCount = 0;
            List<String> line = new ArrayList<>();
            for (VisualNode node : level) {
                if (node == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String key = node.getText();
                    line.add(key);
                    if (key.length() > widest) {
                        widest = key.length();
                    }
                    next.add(node.getLeft());
                    if (node.getLeft() != null) {
                        nodeCount++;
                    }
                    next.add(node.getRight());
                    if (node.getRight() != null) {
                        nodeCount++;
                    }
                }
            }
            if (widest % 2 == 1) {
                widest++;
            }
            lines.add(line);
            List temp = level;
            level = next;
            next = temp;
            next.clear();
        }
        int perPiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perPiece / 2f) - 1;
            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    char symbol = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            symbol = (line.get(j) != null) ? '\u2534' : '\u256F';
                        } else if (line.get(j) != null) {
                            symbol = '\u2570';
                        }
                    }
                    buffer.append(symbol);
                    if (line.get(j) == null) {
                        buffer.append(" ".repeat(Math.max(0, perPiece - 1)));
                    } else {
                        buffer.append(String.valueOf(j % 2 == 0 ? " " : '\u2500')
                                .repeat(Math.max(0, hpw)));
                        buffer.append(j % 2 == 0 ? '\u256D' : '\u256E');
                        buffer.append(String.valueOf(j % 2 == 0 ? '\u2500' : " ")
                                .repeat(Math.max(0, hpw)));
                    }
                }
                buffer.append('\n');
            }
            for (String word : line) {
                if (word == null) {
                    word = "";
                }
                double space = perPiece / 2f - word.length() / 2f;
                buffer.append(" ".repeat(Math.max(0, (int) Math.ceil(space))))
                        .append(word)
                        .append(" ".repeat(Math.max(0, (int) Math.floor(space))));
            }
            buffer.append('\n');
            perPiece /= 2;
        }
        return buffer.toString();
    }
}