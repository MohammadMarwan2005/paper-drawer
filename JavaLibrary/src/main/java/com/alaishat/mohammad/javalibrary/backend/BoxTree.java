package com.alaishat.mohammad.javalibrary.backend;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Mohammad Al-Aishat on May/04/2024.
 * Problem Solving Project.
 */
public class BoxTree {
    public BoxTreeNode root;

    public BoxTree(List<Character> input) {
        Iterator<Character> myIterator = input.iterator();
        while (myIterator.hasNext()) {
            if (myIterator.next() == ' ')
                myIterator.remove();
        }
        this.root = BoxTreeNode.importBoxTreeNode(input);
    }

    public BoxTree(BoxTreeNode root) {
        this.root = root;
    }


    public boolean isRectangle() {
        return isRectangleRecursively(this.root);
    }

    public boolean isRectangleRecursively(BoxTreeNode boxTreeNode) {
        if (boxTreeNode == null)
            return true;
        if (boxTreeNode.value == '|' && boxTreeNode.left.height != boxTreeNode.right.height)
            return false;
        if (boxTreeNode.value == '-' && boxTreeNode.left.width != boxTreeNode.right.width)
            return false;
        // | h1 = h2
        // -: w1 = w2
        //
        return isRectangleRecursively(boxTreeNode.left) && isRectangleRecursively(boxTreeNode.right);
    }

    public BoxTreeNode rotate() {
        rotateRecursively(this.root);
        return this.root;
    }

    public void rotateRecursively(BoxTreeNode root) {
        if (root == null)
            return;
        int height = root.height;
        root.height = root.width;
        root.width = height;

        if (root.value == '|') {
            root.value = '-';
            BoxTreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        else if (root.value == '-')
            root.value = '|';

        rotateRecursively(root.left);
        rotateRecursively(root.right);
    }
}
