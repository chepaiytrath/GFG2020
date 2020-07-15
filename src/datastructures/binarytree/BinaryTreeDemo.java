package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        Summation s = new Summation();

        BinaryTree tree = new BinaryTree(new Node(5));
        tree.root.left = new Node(2);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(4);
        tree.root.left.right.left = new Node(3);
        tree.root.right = new Node(6);
        tree.root.right.right = new Node(8);
        tree.root.right.right.left = new Node(7);
        tree.root.right.right.right = new Node(9);

        s.findSumOfNodesAtKthLevelInTreeRepresentedAsString("(0(5(6()())(4()(9()())))(7(1()())(3()())))", 2);
    }
}
