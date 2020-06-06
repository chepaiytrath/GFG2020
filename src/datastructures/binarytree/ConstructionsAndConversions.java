package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

import java.util.Arrays;

public class ConstructionsAndConversions {
    Traversals t = new Traversals();

    public void constructFromInorderAndPreorder(String in, String pre) {
        BinaryTree tree = new BinaryTree(constructFromInorderAndPreorderUtil(in, pre));
        t.printInorder(tree);
    }

    private Node constructFromInorderAndPreorderUtil(String in, String pre) {
        if (pre.length() == 0) {
            return null;
        }
        Node node = new Node(Character.getNumericValue(pre.charAt(0)));
        final String substring = pre.substring(1, 1 + in.indexOf(pre.charAt(0)));
        node.left = constructFromInorderAndPreorderUtil(in.substring(0, in.indexOf(pre.charAt(0))), substring);
        node.right = constructFromInorderAndPreorderUtil(in.substring(in.indexOf(pre.charAt(0)) + 1), pre.substring(1 + in.indexOf(pre.charAt(0))));
        return node;
    }

    public void constructFromInorderAndPostorder(String in, String post) {
        BinaryTree tree = new BinaryTree(constructFromInorderAndPostorderUtil(in, post));
        t.printInorder(tree);
    }

    private Node constructFromInorderAndPostorderUtil(String in, String post) {
        if (in.isEmpty() && post.isEmpty()) {
            return null;
        }
        int last = post.charAt(post.length() - 1);
        int inIndex = in.indexOf(last);
        int rightSubTreeSize = in.length() - inIndex - 1;

        Node node = new Node(Character.getNumericValue(last));
        node.left = constructFromInorderAndPostorderUtil(in.substring(0, inIndex), post.substring(0, inIndex));
        node.right = constructFromInorderAndPostorderUtil(in.substring(inIndex + 1), post.substring(inIndex, post.length() - 1));
        return node;
    }

    public void constructFromInorderAndLevelorder(int[] in, int[] level) {
        BinaryTree tree = new BinaryTree(constructFromInorderAndLevelorderUtil(in, level));
        t.printInorder(tree);
    }

    private Node constructFromInorderAndLevelorderUtil(int[] in, int[] level) {
        if(in.length == 0){
            return null;
        }
        if (in.length == 1) {
            return new Node(in[0]);
        }
        int iIndex = -1;
        int i = 0;
        while (iIndex == -1 && i < level.length) {
            iIndex = findIndexInIntArray(in, level[i]);
            i++;
        }
        Node node = new Node(in[iIndex]);
        /*node.left = constructFromInorderAndLevelorderUtil(Arrays.copyOfRange(in, 0, iIndex), Arrays.copyOfRange(level, 1, level.length));
        node.right = constructFromInorderAndLevelorderUtil(Arrays.copyOfRange(in, iIndex + 1, in.length), Arrays.copyOfRange(level, 1, level.length));*/

        node.left = constructFromInorderAndLevelorderUtil(Arrays.copyOfRange(in, 0, iIndex), level);
        node.right = constructFromInorderAndLevelorderUtil(Arrays.copyOfRange(in, iIndex + 1, in.length), level);
        return node;
    }

    private int findIndexInIntArray(int[] arr, int tar) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == tar) {
                return i;
            }
        }
        return -1;
    }
}
