package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

import java.util.ArrayList;
import java.util.List;

public class FindAndReplace {
    static int count = 0;
    static Node result = new Node();

    public Node findNthNodeOfInorderTraversal(BinaryTree tree, int k) {
        findNthNodeOfInorderTraversalUtil(tree.root, k, result);
        return result;
    }

    private void findNthNodeOfInorderTraversalUtil(Node node, int k, Node result) {
        if (node == null) {
            return;
        }
        if (count <= k) {
            findNthNodeOfInorderTraversalUtil(node.left, k, result);
            count++;
            if (count == k && result.data == 0) {
                result.data = node.data;
            }
            findNthNodeOfInorderTraversalUtil(node.right, k, result);
        }
    }

    static class INT {
        int data;
    }

    public void replaceNodeWithSumOfInorderPredecessorAndSuccessor(BinaryTree tree) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        putInorderTraversalNodesIntoList(list, tree.root);
        list.add(0);
        INT i = new INT();
        i.data = 1;
        replaceNodeWithSumOfInorderUtil(list, tree.root, i);
    }

    private void putInorderTraversalNodesIntoList(List<Integer> list, Node node) {
        if (node == null) {
            return;
        }
        putInorderTraversalNodesIntoList(list, node.left);
        list.add(node.data);
        putInorderTraversalNodesIntoList(list, node.right);
    }

    private void replaceNodeWithSumOfInorderUtil(List<Integer> list, Node node, INT i) {
        if (node == null) {
            return;
        }
        replaceNodeWithSumOfInorderUtil(list, node.left, i);
        node.data = list.get(i.data - 1) + list.get(i.data + 1);
        i.data++;
        replaceNodeWithSumOfInorderUtil(list, node.right, i);
    }
}
