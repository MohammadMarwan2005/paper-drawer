package com.alaishat.mohammad.javalibrary.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Al-Aishat on May/04/2024.
 * Problem Solving Project.
 */

public class BoxTreeNode {

    public char value;
    public int height;
    public int width;
    public BoxTreeNode left;
    public BoxTreeNode right;

    private static final char open = '(';
    private static final char close = ')';

    public BoxTreeNode(char value, BoxTreeNode left, BoxTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BoxTreeNode(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public BoxTreeNode(char value, int height, int width) {
        this.value = value;
        this.height = height;
        this.width = width;
        this.left = null;
        this.right = null;
    }

    public static BoxTreeNode importBoxTreeNode(List<Character> immutableInput) {
        List<Character> input = new ArrayList<>(immutableInput);

        if (input.get(0) == open && input.get(input.size() - 1) == close) {
            input.remove(0);
            input.remove(input.size() - 1);
        }
        BoxTreeNode result = null;


        // Not a paper!
        if (input.contains('-') || input.contains('|')) {
            int p = 0;
            int rootIndex = -1;
            for (int i = 0; i < input.size(); i++) {
                if (input.get(i) == open)
                    p++;
                if (input.get(i) == close)
                    p--;
                if (p == 0 && (input.get(i) == '-' || input.get(i) == '|')) {
                    rootIndex = i;
                    break;
                }
            }

            List<Character> leftList = input.subList(0, rootIndex);
            List<Character> rightList = input.subList(rootIndex + 1, input.size());
            result = new BoxTreeNode(input.get(rootIndex), importBoxTreeNode(leftList), importBoxTreeNode(rightList));

            if (result.value == '|') {
                // A B
                result.height = Math.max(result.left.height, result.right.height);
                result.width = result.left.width + result.right.width;
            }
            if (result.value == '-') {
                // A
                // B
                result.height = result.left.height + result.right.height;
                result.width = Math.max(result.left.width, result.right.width);
            }

        } else {
            int i = 0;
            for (i = 0; i < input.size(); i++) {
                char character = input.get(i);
                // A
                if (character <= 90 && character >= 65) {
                    List<Character> tempList = new ArrayList<>();
                    int j = 0;
                    for (j = 0; true; j++) {
                        tempList.add(input.get(i + j));
                        if (input.get(i + j) == ']')
                            break;
                    }
                    // A[100,23300]
                    i += j;
                    result = new BoxTreeNode('@');
                    result = refactorNode(tempList);
                }
            }
        }
        return result;
    }

    public static BoxTreeNode refactorNode(List<Character> immutableNodeList) {
//        System.out.println("Let's refactor this" + nodeList);
        List<Character> localList = new ArrayList<>(immutableNodeList);
        // input = A[20,300]
        char value = localList.get(0); // value = 'A'
        localList.remove(0);    // remove ('A') -> [20,300]
        localList.remove(0);    // 20,300]
        localList.remove(localList.size() - 1); // ['2', '0', ',', '3', '0', '0']

        // "20,3000"
        StringBuilder myString = new StringBuilder();
        for (Character character : localList) {
            myString.append(character);
        }
        String[] heightAndWidth = myString.toString().split(",");     // ["20", "300"]
        int height = Integer.parseInt(heightAndWidth[0]);
        int width = Integer.parseInt(heightAndWidth[1]);
        return new BoxTreeNode(value, height, width);
    }

    public String exportNode() {
        if (this.value == '-' || this.value == '|')
            return "(" + this.left.exportNode() + " " + this.value + " " + this.right.exportNode() + ")";

        if (this.left == null && this.right == null) {
            return this.value + "[" + this.height + "," + this.width + "]";
        }
        return null;
    }
}
