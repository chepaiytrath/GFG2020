package datastructures.binarytree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTree.Node(1);
        BinaryTree.Node root = tree.root;
        root.left = new BinaryTree.Node(2);
        root.left.left = new BinaryTree.Node(4);
        root.left.right = new BinaryTree.Node(5);

        root.right = new BinaryTree.Node(3);
        root.right.left = new BinaryTree.Node(6);
        root.right.right = new BinaryTree.Node(7);
        System.out.println("\nInorder traversal before operation");
        tree.morrisInOrderTraversal();
    }
}
