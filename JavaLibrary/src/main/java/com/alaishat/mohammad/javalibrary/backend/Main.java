package com.alaishat.mohammad.javalibrary.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Al-Aishat on May/04/2024.
 * Problem Solving Project.
 */
public class Main {
    public static void main(String[] args) {
        List<Character> characters = new ArrayList<>();
        characters.add('A');
        characters.add('[');
        characters.add('2');
        characters.add('0');
        characters.add(',');
        characters.add('3');
        characters.add('0');
        characters.add('0');
        characters.add(']');

        String s = "((A[20,10] | (B[20,10]|C[30,10])) – (D[30,50] | (E[40,30] – F[40,20])))";
//        String s = "((A[20,10] | (B[20,10] | C[30,10])) - (D[30,50] | (E[40,30] - F[40,20])))";
//        // (())
//        String s2 = "(A[20,10] | (B[20,10] | C[30,10])) - (D[30,50] | (E[40,30] - F[40,20]))";
//        String s3 = "A[20,10] | (B[20,10] | C[30,10])";
//        s = "((A[20,10]|(B[20,10]|C[30,10]))-(D[30,50]|(E[40,30]-F[40,20])))";
        List<Character> input = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            input.add(s.charAt(i));
        }

//        System.out.println(s);
        BoxTree boxTree = new BoxTree(input);       // import()
        BoxTreeNode boxTreeNode = boxTree.root;

        System.out.println("export(): \t\t" + boxTreeNode.exportNode());   // export()
        System.out.println("Source input: \t" + s);


        boxTree.rotate();
        boxTreeNode = boxTree.root;
        System.out.println("export(): \t\t" + boxTreeNode.exportNode());   // export()
        System.out.println("Source input: \t" + s);
    }
//
//    public static List<BoxTreeNode> generateAllCases(List<DataPaper> papers) {
//        List<List<Character>> dashesCases = getDashesCases(papers.size() - 1);
//
//    }

//    private static List<List<Character>> getDashesCases(int maxSize) {
//        List<List<Character>> result = new ArrayList<>();
//
//
//    }

//    private static List<List<Character>> getDashesCases(int maxSize) {
//        List<List<Character>> result = new ArrayList<>();
//    }
//
//    private static void recursive(int i, List<Character> current, int maxSize, List<List<Character>> result) {
//        if (i == maxSize) {
//            result.add(current);
//        }
//        List<Character> firstCopy = new ArrayList<>(current);
//        List<Character> secondCopy = new ArrayList<>(current);
//        List<Character> thirdCopy = new ArrayList<>(current);
//        recursive(i + 1, firstCopy);

//    }


}
