package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;
import datastructures.binarytree.BinaryTree.QueueNode;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class Views {
    public void topViewWithQueue(BinaryTree tree) {
        //Recursive approach for top view needs an extra level check of the node in addition to distance because of traversing the left subtree completely first
        Queue<QueueNode> q = new LinkedList<>();
        q.add(new QueueNode(0, tree.root));
        Map<Integer, Integer> map = new TreeMap<>();
        while (!q.isEmpty()) {
            QueueNode popped = q.poll();
            if (popped.node.left != null) {
                q.add(new QueueNode(popped.dis - 1, popped.node.left));
            }
            if (popped.node.right != null) {
                q.add(new QueueNode(popped.dis + 1, popped.node.right));
            }
            map.computeIfAbsent(popped.dis, k -> popped.node.data);
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
    }

    public void topViewWithRecursion(BinaryTree tree) {
        Map<Integer, BinaryTree.ViewNode> visited = new TreeMap<>();
        topViewWithRecursionUtil(tree.root, 0, 0, visited);
        for (Map.Entry entry : visited.entrySet()) {
            System.out.print(((BinaryTree.ViewNode) entry.getValue()).data + " ");
        }
    }

    private void topViewWithRecursionUtil(Node node, int dis, int level, Map<Integer, BinaryTree.ViewNode> visited) {
        if (node == null) {
            return;
        }
        if (visited.get(dis) == null || visited.get(dis).level > level) {
            visited.put(dis, new BinaryTree.ViewNode(node.data, level));
        }
        topViewWithRecursionUtil(node.left, dis - 1, level + 1, visited);
        topViewWithRecursionUtil(node.right, dis + 1, level + 1, visited);
    }

    public void bottomViewWithQueue(BinaryTree tree) {
        Queue<QueueNode> q = new LinkedList<>();
        q.add(new QueueNode(0, tree.root));
        Map<Integer, Integer> map = new TreeMap<>();
        while (!q.isEmpty()) {
            QueueNode popped = q.poll();
            if (popped.node.left != null) {
                q.add(new QueueNode(popped.dis - 1, popped.node.left));
            }
            if (popped.node.right != null) {
                q.add(new QueueNode(popped.dis + 1, popped.node.right));
            }
            map.put(popped.dis, popped.node.data);
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
    }

    public void bottomViewWithRecursion(BinaryTree tree) {
        Map<Integer, BinaryTree.ViewNode> visited = new TreeMap<>();
        bottomViewWithRecursionUtil(tree.root, 0, 0, visited);
        for (Map.Entry entry : visited.entrySet()) {
            System.out.print(((BinaryTree.ViewNode) entry.getValue()).data + " ");
        }
    }

    private void bottomViewWithRecursionUtil(Node node, int dis, int level, Map<Integer, BinaryTree.ViewNode> visited) {
        if (node == null) {
            return;
        }
        if (visited.get(dis) == null || visited.get(dis).level <= level) {
            visited.put(dis, new BinaryTree.ViewNode(node.data, level));
        }
        bottomViewWithRecursionUtil(node.left, dis - 1, level + 1, visited);
        bottomViewWithRecursionUtil(node.right, dis + 1, level + 1, visited);
    }

    public void leftViewWithRecursion(BinaryTree tree) {
        Map<Integer, Integer> map = new TreeMap<>();
        leftViewWithRecursionUtil(tree.root, 0, map);
        for (Map.Entry entry : map.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
    }

    private void leftViewWithRecursionUtil(Node node, int level, Map<Integer, Integer> map) {
        // Traverse in preorder manner
        if (node == null) {
            return;
        }

        map.computeIfAbsent(level, k -> node.data);
        leftViewWithRecursionUtil(node.left, level + 1, map);
        leftViewWithRecursionUtil(node.right, level + 1, map);
    }

    public void rightViewUsingQueue(BinaryTree tree) {
        Queue<Node> q = new LinkedList<>();
        q.add(tree.root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 1; i <= size; i++) {
                Node temp = q.poll();
                if (i == size) {
                    System.out.print(temp.data + " ");
                }
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
        }
    }

    public void rightViewWithRecursion(BinaryTree tree) {
        Map<Integer, Integer> map = new TreeMap<>();
        rightViewWithRecursionUtil(tree.root, 0, map);
        for (Map.Entry entry : map.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
    }

    private void rightViewWithRecursionUtil(Node node, int level, Map<Integer, Integer> map) {
        // Traverse in preorder manner
        if (node == null) {
            return;
        }

        map.put(level, node.data);

        rightViewWithRecursionUtil(node.left, level + 1, map);
        rightViewWithRecursionUtil(node.right, level + 1, map);
    }
}
