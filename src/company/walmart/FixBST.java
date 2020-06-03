package company.walmart;

import datastructures.binarytree.BinaryTree;

public class FixBST {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTree.Node(3);
        BinaryTree.Node root = tree.root;
        root.left = new BinaryTree.Node(2);
        root.right = new BinaryTree.Node(5);
        root.left.left = new BinaryTree.Node(1);
        root.left.right = new BinaryTree.Node(4);

        //tree.printInorder();
        System.out.println();
        FixBST f = new FixBST();
        f.fixBST(tree);
        //tree.printInorder();
    }

    BinaryTree.Node prev, first, last, middle;
    void fixBST(BinaryTree tree) {
        BinaryTree.Node root = tree.root;
        fixBSTUtil(root);
        if(first != null && last != null){
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        }
        if(first != null && middle != null){
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        }
    }

    private void fixBSTUtil(BinaryTree.Node node) {
        if (node == null) {
            return;
        }
        fixBSTUtil(node.left);
        if (prev != null && prev.data > node.data) {
            if (first == null) {
                first = prev;
                middle = node;
            } else{
                last = node;
            }
        }
        prev = node;
        fixBSTUtil(node.right);
    }
}
