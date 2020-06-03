package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        Node root = tree.root;
        root.left = new Node(2);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.right = new Node(4);

        Views t = new Views();
        t.bottomRightViewWithRecursion(tree);

/*        root.left.left.left = new BinaryTree.Node(8);
        root.left.left.right = new BinaryTree.Node(9);
        root.left.right.left = new BinaryTree.Node(10);
        root.left.right.right = new BinaryTree.Node(11);
        root.right.left.left = new BinaryTree.Node(12);
        root.right.left.right = new BinaryTree.Node(13);
        root.right.right.left = new BinaryTree.Node(14);
        root.right.right.right = new BinaryTree.Node(15);
        root.right.right.right.left = new BinaryTree.Node(16);
        root.right.right.right.right = new BinaryTree.Node(17);*/

//        tree.diagonalViewUsingRecursively();
    }
}
