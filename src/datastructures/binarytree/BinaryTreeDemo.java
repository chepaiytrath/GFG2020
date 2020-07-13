package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        Summation s = new Summation();

        BinaryTree tree1 = new BinaryTree(new Node(1));
        tree1.root.left = new Node(2);
        tree1.root.left.left = new Node(3);


        BinaryTree tree2 = new BinaryTree(new Node(2));
        tree2.root.right = new Node(1);
        tree2.root.right.right = new Node(0);

        s.mergeTwoBinaryTreesByDoingNodeSumWithoutRecursion(tree1, tree2);
    }
}
