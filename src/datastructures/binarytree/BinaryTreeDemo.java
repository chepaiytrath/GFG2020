package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        CheckingAndPrinting c = new CheckingAndPrinting();

        BinaryTree tree = new BinaryTree(1);
        tree.root.left = new Node(2);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.left.right.left = new Node(3);
        tree.root.left.right.left.left = new Node(6);
        tree.root.left.right.left.right = new Node(7);
        tree.root.right = new Node(9);
        tree.root.right.left = new Node(8);
        tree.root.right.right = new Node(3);
        tree.root.right.right.left = new Node(6);
        /*tree.root.right.right.left.left = new Node(12);
        tree.root.right.right.left.right= new Node(13);*/
        tree.root.right.right.right = new Node(7);
        /*tree.root.right.right.right.left = new Node(10);
        tree.root.right.right.right.right = new Node(11);*/

        c.checkIfBinaryTreeContainsDuplicateSubtreesOfSizeTwoOrMore(tree);
    }
}
