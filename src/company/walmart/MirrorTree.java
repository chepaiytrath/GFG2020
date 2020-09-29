package company.walmart;

import datastructures.binarytree.BinaryTree;

public class MirrorTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTree.Node(1);
        BinaryTree.Node root = tree.root;
        root.left = new BinaryTree.Node(2);
        root.right = new BinaryTree.Node(3);
        root.left.left = new BinaryTree.Node(4);
        root.left.right = new BinaryTree.Node(5);
        root.right.left = new BinaryTree.Node(6);
        root.right.right = new BinaryTree.Node(7);

        //tree.printInorder();
        MirrorTree mt = new MirrorTree();
        System.out.println();
        mt.mirrorTree(tree);
        //tree.printInorder();
    }

    public void mirrorTree(BinaryTree tree) {
        if (tree == null && tree.root == null) {
            return;
        }

        mirrorTreeUtil(tree.root);
    }

    private void mirrorTreeUtil(BinaryTree.Node node) {
        if (node == null) {
            return;
        }
        mirrorTreeUtil(node.left);
        mirrorTreeUtil(node.right);

        BinaryTree.Node left = node.left;
        node.left = node.right;
        node.right = left;
    }
}
