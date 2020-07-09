package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        LowestCommonAncestor lca = new LowestCommonAncestor();

        BinaryTree tree = new BinaryTree(new Node(1));
        tree.root.left = new Node(2);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right = new Node(3);


        lca.printAncestorsOfGivenNodeWithRecursionWithoutExtraArray(tree, 4);
    }
}
