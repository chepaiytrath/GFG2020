package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        Summation s = new Summation();

        BinaryTree tree = new BinaryTree(new Node(4));
        tree.root.left = new Node(2);
        tree.root.left.left = new Node(7);
        tree.root.left.right = new Node(2);
        tree.root.right = new Node(5);
        tree.root.right.left = new Node(2);
        tree.root.right.right = new Node(3);
        
        s.findSumOfAllParentNodesHavingGivenChildNode(tree, 2);
    }
}
