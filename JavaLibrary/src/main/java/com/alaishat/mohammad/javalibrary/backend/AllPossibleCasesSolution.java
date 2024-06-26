package com.alaishat.mohammad.javalibrary.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Al-Aishat on Jun/10/2024.
 * Draw Your Paper Project.
 */
public class AllPossibleCasesSolution {
    public static void main(String[] args) {
        // Get the start time
        long startTime = System.nanoTime();

        List<DataPaper> current = new ArrayList<>();
        List<DataPaper> remains = new ArrayList<>();


        remains.add(new DataPaper('A', 50, 50));
        remains.add(new DataPaper('B', 50, 3000));
//        remains.add(new DataPaper('C', 150, 60));
//        remains.add(new DataPaper('D', 30, 70));
//        remains.add(new DataPaper('E', 100, 50));
//        remains.add(new DataPaper('F', 100, 50));
//        remains.add('G');


        sol(current, remains, 0, remains.size() - 1);
//        System.out.println("R = " + R);

        BoxTreeNode temp = null;

        for (List<DataPaper> dataPapers : R) {
            for (DataPaper dataPaper : dataPapers) {
                System.out.print(dataPaper.value + ", ");
            }
            System.out.println();
        }

        for (BoxTree tree : getAllPossibleBoxTrees(remains)) {
            if (tree.isRectangle()) {
                System.out.println(tree.root.exportNode());
            }
        }
        System.out.println(R.size());


        // Get the end time
        long endTime = System.nanoTime();
        // Calculate the elapsed time
        long elapsedTime = endTime - startTime;
        // Print the elapsed time in nanoseconds
        System.out.println("Execution time in nanoseconds: " + elapsedTime);
        // Optionally, convert to milliseconds for readability
        System.out.println("Execution time in milliseconds: " + elapsedTime / 1_000_000);
        System.out.println("Execution time in seconds: " + ((double) elapsedTime / 1_000_000_000));

    }

    public static List<List<DataPaper>> getAllPossibleDataPapersLists(List<DataPaper> remains) {
        R.clear();
        sol(new ArrayList<>(), remains, 0, remains.size() - 1);
        return R;
    }

    public static List<BoxTree> getAllPossibleBoxTrees(List<DataPaper> remains) {
        getAllPossibleDataPapersLists(remains);
        List<BoxTree> result = new ArrayList<>();
        for (List<DataPaper> dataPapers : R) {
            result.add(new BoxTree(listToBoxTreeNode(dataPapers)));
        }
        return result;
    }

    public static void sol(List<DataPaper> current, List<DataPaper> remains, int numberOfCurrentRCs, int targetNumberOfRCs) {
        if (remains.size() > 7) {
            throw new RuntimeException("Our brute force algorithm cannot handle a list containing more than 6 papers");
        }
        // First Base Case: No remains chars
        if (remains.isEmpty()) {
            R.add(current);
            return;
        }
        // Second BaseCase: Can't handle all remains Rows and Columns!
        if (remains.size() <= (targetNumberOfRCs - numberOfCurrentRCs)) {
            R.add(current);
            return;
        }

        if (numberOfCurrentRCs < targetNumberOfRCs) {
            // take a -
            List<DataPaper> newDashCurrentObject = new ArrayList<>(current);
            newDashCurrentObject.add(new DataPaper('-', -1, -1));
            sol(newDashCurrentObject, remains, numberOfCurrentRCs + 1, targetNumberOfRCs);
            // take a |
            List<DataPaper> newSlashCurrentObject = new ArrayList<>(current);
            newSlashCurrentObject.add(new DataPaper('|', -1, -1));
            sol(newSlashCurrentObject, remains, numberOfCurrentRCs + 1, targetNumberOfRCs);
        }

        // take a character
        for (int i = 0; i < remains.size(); i++) {
            List<DataPaper> newRemainsObject = new ArrayList<>(remains);
            newRemainsObject.remove(i);
            List<DataPaper> newCharCurrentObject = new ArrayList<>(current);
            newCharCurrentObject.add(remains.get(i));
            sol(newCharCurrentObject, newRemainsObject, numberOfCurrentRCs, targetNumberOfRCs);
        }
    }

    public static BoxTreeNode listToBoxTreeNode(List<DataPaper> papers) {
        boolean[] isVisited = new boolean[papers.size()];
        return listToBoxTreeNode(papers, 0, isVisited);
    }

    public static List<List<DataPaper>> R = new ArrayList<>();

    static int lastI = 0;

    // papers = [|, -, C, A, B] -> BoxTreeNode?
    private static BoxTreeNode listToBoxTreeNode(List<DataPaper> papers, int i, boolean[] isVisited) {
        if (isVisited[i]) {
            return listToBoxTreeNode(papers, i + 1, isVisited);
        }
        if (papers.get(i).value != '-' && papers.get(i).value != '|') {
            lastI = i;
            return new BoxTreeNode(papers.get(i).value, papers.get(i).height, papers.get(i).width);
        }

        BoxTreeNode result = new BoxTreeNode(papers.get(i).value);
        BoxTreeNode left = listToBoxTreeNode(papers, i + 1, isVisited);
        BoxTreeNode right = listToBoxTreeNode(papers, lastI + 1, isVisited);
        result.left = left;
        result.right = right;
        if (result.value == '-') {
            result.height = left.height + right.height;
            result.width = Math.max(left.width, right.width);
        } else if (result.value == '|') {
            result.width = left.width + right.width;
            result.height = Math.max(left.height, right.height);
        }
        return result;
    }
}