package datastructures.binarytree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        ConstructionsAndConversions c = new ConstructionsAndConversions();
        /*int in[] = new int[]{4, 8, 10, 12, 14, 20, 22};
        int level[] = new int[]{20, 8, 22, 4, 12, 10, 14};*/

        int in[] = new int[]{4, 5, 8, 7, 6, 2, 1, 9, 11, 13, 14, 10, 3, 12};
        int level[] = new int[]{1, 2, 3, 4, 9, 10, 5, 10, 6, 11, 7, 13, 8, 14};
        c.constructFromInorderAndLevelorder(in, level);

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
