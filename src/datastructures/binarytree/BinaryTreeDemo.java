package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        CheckingAndPrinting c = new CheckingAndPrinting();
        
        BinaryTree tree1 = new BinaryTree(1);
        tree1.root.left = new Node(2);
        tree1.root.left.right = new Node(4);
        tree1.root.right = new Node(3);
        
        BinaryTree tree2 = new BinaryTree(5);
        tree2.root.left = new Node(1);
        tree2.root.left.left = new Node(2);
        tree2.root.left.left.right = new Node(4);
        tree2.root.left.right = new Node(3);
        tree2.root.right = new Node(6);
        tree2.root.right.right = new Node(7);

        c.checkIfBinaryTreeHasDuplicateValuesWithRecursion(tree1);
    

        /*
         * root.left.left.left = new BinaryTree.Node(8); root.left.left.right = new
         * BinaryTree.Node(9); root.left.right.left = new BinaryTree.Node(10);
         * root.left.right.right = new BinaryTree.Node(11); root.right.left.left = new
         * BinaryTree.Node(12); root.right.left.right = new BinaryTree.Node(13);
         * root.right.right.left = new BinaryTree.Node(14); root.right.right.right = new
         * BinaryTree.Node(15); root.right.right.right.left = new BinaryTree.Node(16);
         * root.right.right.right.right = new BinaryTree.Node(17);
         */

        // tree.diagonalViewUsingRecursively();
    }
}
