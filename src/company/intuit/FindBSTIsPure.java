//https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
package company.intuit;

public class FindBSTIsPure {
    static class BinaryTree {
        Node root;

        static class Node {
            Node(int data) {
                this.data = data;
                left = null;
                right = null;
            }

            int data;
            Node left;
            Node right;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTree.Node(4);
        tree.root.left = new BinaryTree.Node(2);
        tree.root.right = new BinaryTree.Node(5);
        tree.root.left.left = new BinaryTree.Node(1);
        tree.root.left.right = new BinaryTree.Node(3);
        FindBSTIsPure obj = new FindBSTIsPure();
        if (obj.findBSTIsPure(tree))
            System.out.println("IS BST");
        else
            System.out.println("Not a BST");
    }

    private boolean findBSTIsPure(BinaryTree tree) {
        return findBSTIsPureUtil(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean findBSTIsPureUtil(BinaryTree.Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.data < min || node.data > max) {
            return false;
        }

        return findBSTIsPureUtil(node.left, min, node.data - 1) && findBSTIsPureUtil(node.right, node.data + 1, max);
    }

}
