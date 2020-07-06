package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        CheckingAndPrinting c = new CheckingAndPrinting();

        BinaryTree tree = new BinaryTree(8);
        tree.root.left = new Node(3);
        tree.root.left.left = new Node(1);
        tree.root.left.left.left = new Node(7);
        tree.root.left.left.right = new Node(13);
        tree.root.left.right = new Node(6);
        tree.root.right = new Node(10);
        tree.root.right.left = new Node(14);
        tree.root.right.right = new Node(4);

        c.printOddPositionedNodesOfEvenLevelsInLevelOrder(tree);
    }
}
