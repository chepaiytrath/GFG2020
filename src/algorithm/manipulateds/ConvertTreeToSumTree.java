package algorithm.manipulateds;

import datastructures.binarytree.BinaryTree;

public class ConvertTreeToSumTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTree.Node(10);
        BinaryTree.Node root = tree.root;
        root.left = new BinaryTree.Node(-2);
        root.right = new BinaryTree.Node(6);
        root.left.left = new BinaryTree.Node(8);
        root.left.right = new BinaryTree.Node(-4);
        root.right.left = new BinaryTree.Node(7);
        root.right.right = new BinaryTree.Node(5);

        //tree.printInorder();
        ConvertTreeToSumTree mt = new ConvertTreeToSumTree();
        System.out.println();
        mt.convertTreeToSumTree(tree.root);
        //tree.printInorder();
    }

    private int convertTreeToSumTree(BinaryTree.Node node) {
        if(node == null){
            return 0;
        }
        int data = node.data;
        int sum = convertTreeToSumTree(node.left) + convertTreeToSumTree(node.right);
        node.data = sum;
        return data + sum;
    }
}
