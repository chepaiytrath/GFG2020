package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

import java.util.*;

public class Traversals {
    private void visit(Node node) {
        System.out.print(node.data + " ");
    }

    public void printPreorder(BinaryTree tree) {
        printPreorder(tree.root);
    }

    private void printPreorder(Node node) {
        if (node == null) {
            return;
        }
        visit(node);
        printPreorder(node.left);
        printPreorder(node.right);
    }

    public void printInorder(BinaryTree tree) {
        printInorder(tree.root);
    }

    private void printInorder(Node node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        visit(node);
        printInorder(node.right);
    }

    public void printPostorder(BinaryTree tree) {
        printPostorder(tree.root);
    }

    private void printPostorder(Node node) {
        if (node == null) {
            return;
        }
        printPostorder(node.left);
        printPostorder(node.right);
        visit(node);
    }

    public void morrisPreorderTraversal(BinaryTree tree) {
        morrisPreorderTraversalUtil(tree.root);
    }

    private void morrisPreorderTraversalUtil(Node node) {
        Node curr = node;
        while (curr != null) {
            if (curr.left == null) {
                visit(curr);
                curr = curr.right;
            } else {
                Node pred = findPredecessor(curr);
                if (pred.right != curr) {
                    visit(curr);
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    public void morrisInorderTraversal(BinaryTree tree) {
        morrisInorderTraversalUtil(tree.root);
    }

    private void morrisInorderTraversalUtil(Node node) {
        Node curr = node;
        while (curr != null) {
            if (curr.left == null) {
                visit(curr);
                curr = curr.right;
            } else {
                Node pred = findPredecessor(curr);
                if (pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    visit(curr);
                    curr = curr.right;
                }
            }
        }
    }

    private Node findPredecessor(Node node) {
        Node curr = node;
        Node left = curr.left;
        while (left.right != null && left.right != curr) {
            left = left.right;
        }
        return left;
    }

    public void printInorderWithStackWithoutRecursion(BinaryTree tree) {
        //Similar solution at : https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
        Stack<Node> st = new Stack<>();
        Node curr = tree.root;
        while (curr != null) {
            st.push(curr);
            curr = curr.left;
        }
        while (!st.isEmpty()) {
            Node node = st.pop();
            visit(node);
            if (node.right != null) {
                curr = node.right;
                while (curr != null) {
                    st.push(curr);
                    curr = curr.left;
                }
            }
        }
    }

    public void printPostOrderWithTwoStacksWithoutRecursion(BinaryTree tree) {
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        Node node = tree.root;
        s1.add(node);

        while (!s1.isEmpty()) {
            Node curr = s1.pop();
            if (null != curr.left) {
                s1.add(curr.left);
            }
            if (null != curr.right) {
                s1.add(curr.right);
            }
            s2.add(curr);
        }

        while (!s2.isEmpty()) {
            System.out.print(s2.pop().data + " ");
        }
    }

    public void printPostOrderWithOneStacksWithoutRecursion(BinaryTree tree) {
        /*BinaryTree tree = new BinaryTree(1);
        tree.root.left = new Node(2);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.left.right.left = new Node(6);
        tree.root.left.right.left.left = new Node(7);
        tree.root.left.right.right = new Node(8);
        tree.root.left.right.right.left = new Node(9);
        tree.root.right = new Node(3);
        tree.root.right.left = new Node(10);
        tree.root.right.right = new Node(11);
        tree.root.right.right.right = new Node(12);
        tree.root.right.right.right.left = new Node(13);*/

        Node curr = tree.root;
        Stack<Node> st = new Stack<>();
        while (!st.isEmpty() || curr != null) {
            //Keep adding left children till null
            if (curr != null) {
                st.add(curr);
                curr = curr.left;
            } else {
                //When null, check if top of stack has right child to explore
                Node right = st.peek().right;
                if (right == null) {
                    //Pop the stack top element and print
                    Node popped = st.pop();
                    System.out.println(popped.data);
                    //Pop elements from stack whle popped element is right child of stack's top element so as not to fall into an infinite loop
                    while (!st.isEmpty() && popped == st.peek().right) {
                        popped = st.pop();
                        System.out.println(popped.data);
                    }
                } else {
                    //Move curr to right child to explore and in next loop keep adding left children till null
                    curr = right;
                }
            }
        }
    }

    public void printPostOrderWithOneStacksWithoutRecursion2(BinaryTree tree) {
        Stack<Node> st = new Stack<>();
        Node n = tree.root;
        while (n != null) {
            st.add(n);
            n = n.left;
        }
        while (!st.isEmpty()) {
            Node node = st.peek();
            if (node.right == null) {
                Node popped = st.pop();
                System.out.print(popped.data + " ");
                while (!st.isEmpty() && popped == st.peek().right) {
                    popped = st.pop();
                    System.out.print(popped.data + " ");
                }
            } else {
                Node rightNode = node.right;
                while (rightNode != null) {
                    st.add(rightNode);
                    rightNode = rightNode.left;
                }
            }
        }
    }

    public void levelOrderTraversalWithQueue(BinaryTree tree) {
        Queue<Node> q = new LinkedList<>();
        q.add(tree.root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            System.out.print(node.data + " ");
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
    }

    public void levelOrderTraversalWithRecursion(BinaryTree tree) {
        int height = height(tree.root);
        for (int i = 1; i <= height; i++) {
            printGivenLevel(tree.root, i);
            System.out.println();
        }
    }

    public void reverseLevelOrderTraversalWithQueueandStack(BinaryTree tree) {
        Stack<Node> st = new Stack<>();
        Queue<Node> q = new LinkedList<>();
        Node node = tree.root;
        q.add(node);

        while (!q.isEmpty()) {
            Node n = q.poll();
            st.add(n);
            if (n.right != null) {
                q.add(n.right);
            }
            if (n.left != null) {
                q.add(n.left);
            }
        }
        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
    }

    public void reverseLevelOrderTraversalWithRecursion(BinaryTree tree) {
        int height = height(tree.root);
        for (int i = height; i >= 1; i--) {
            printGivenLevel(tree.root, i);
            System.out.println();
        }
    }

    private void printGivenLevel(Node node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.data + " ");
        } else if (level > 1) {
            printGivenLevel(node.left, level - 1);
            printGivenLevel(node.right, level - 1);
        }
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        int hLeft = height(node.left) + 1;
        int hRight = height(node.right) + 1;
        return Math.max(hLeft, hRight);
    }

    public void levelOrderTraversalInSpiralFormWithRecursion(BinaryTree tree) {
        //Zig Zag Traversal
        int height = height(tree.root);
        boolean dir = false;
        for (int i = 1; i <= height; i++) {
            dir = !dir;
            printGivenLevelAlternateOrder(tree.root, i, dir);
            System.out.println();
        }
    }

    public void levelOrderTraversalWithDirectionChangeAfterEveryTwoLevels(BinaryTree tree) {
        int height = height(tree.root);
        boolean dir = true;
        int prev = 1;
        for (int i = 1; i <= height; i++) {
            if (i - prev == 2) {
                prev = i;
                dir = !dir;
            }
            printGivenLevelAlternateOrder(tree.root, i, dir);
            System.out.println();
        }
    }

    private void printGivenLevelAlternateOrder(Node node, int level, boolean dir) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.data + " ");
        } else if (level > 1) {
            if (dir) {
                printGivenLevelAlternateOrder(node.left, level - 1, dir);
                printGivenLevelAlternateOrder(node.right, level - 1, dir);
            } else {
                printGivenLevelAlternateOrder(node.right, level - 1, dir);
                printGivenLevelAlternateOrder(node.left, level - 1, dir);
            }
        }
    }

    public void levelOrderTraversalInSpiralFormWithTwoStacks(BinaryTree tree) {
        if (tree.root == null) {
            return;
        }

        Node curr = tree.root;
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        st1.push(curr);

        while (!st1.isEmpty() || !st2.isEmpty()) {
            Node node = null;
            while (!st1.isEmpty()) {
                node = st1.pop();
                System.out.print(node.data + " ");
                if (node.right != null) {
                    st2.push(node.right);
                }
                if (node.left != null) {
                    st2.push(node.left);
                }
            }

            while (!st2.isEmpty()) {
                node = st2.pop();
                System.out.print(node.data + " ");
                if (node.left != null) {
                    st1.push(node.left);
                }
                if (node.right != null) {
                    st1.push(node.right);
                }
            }
        }
    }

    public void middleToUpDownTraversal(BinaryTree tree) {
        int height = height(tree.root);
        int middleLevel = (height / 2) + 1;
        int count = 1;
        printGivenLevel(tree.root, middleLevel);
        System.out.println();
        while (count >= 1 && count <= height) {
            printGivenLevel(tree.root, middleLevel - count);
            System.out.println();
            printGivenLevel(tree.root, middleLevel + count);
            System.out.println();
            count++;
        }
    }

    public void diagonalOrderTraversalWithRecursion(BinaryTree tree) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        diagonalOrderTraversalWithRecursionUtil(tree.root, 0, map);
        /*for (Map.Entry e : map.entrySet()) {
            System.out.println(e.getValue());
        }*/
        map.values().forEach(System.out::println);
    }

    private void diagonalOrderTraversalWithRecursionUtil(Node node, int dis, Map<Integer, List<Integer>> map) {
        if (node == null) {
            return;
        }
        List<Integer> list = map.computeIfAbsent(dis, k -> new ArrayList<>());
        list.add(node.data);
        diagonalOrderTraversalWithRecursionUtil(node.left, dis + 1, map);
        diagonalOrderTraversalWithRecursionUtil(node.right, dis, map);
    }

    public void diagonalOrderTraversalWithoutRecursion(BinaryTree tree) {
        Queue<Node> q = new LinkedList<>();
        q.add(tree.root);
        Node curr = tree.root;
        while (!q.isEmpty()) {
            curr = q.poll();
            while (curr != null) {
                System.out.print(curr + " ");
                q.add(curr.left);
                curr = curr.right;
            }
        }
    }

    public void verticalOrderTraversalWithQueue(BinaryTree tree) {
        Queue<BinaryTree.QueueNode> q = new LinkedList<>();
        q.add(new BinaryTree.QueueNode(0, tree.root));
        Map<Integer, List<Integer>> map = new TreeMap<>();
        while (!q.isEmpty()) {
            BinaryTree.QueueNode popped = q.poll();
            if (popped.node.left != null) {
                q.add(new BinaryTree.QueueNode(popped.dis - 1, popped.node.left));
            }
            if (popped.node.right != null) {
                q.add(new BinaryTree.QueueNode(popped.dis + 1, popped.node.right));
            }
            List<Integer> listVal = map.computeIfAbsent(popped.dis, k -> new ArrayList<>());
            listVal.add(popped.node.data);
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.print(entry.getValue());
        }
    }

    public void verticalOrderTraversalWithRecursion(BinaryTree tree) {
        Node node = tree.root;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        verticalOrderTraversalWithRecursionUtil(node, 0, map);
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            System.out.print(entry.getValue());
        }
    }

    private void verticalOrderTraversalWithRecursionUtil(Node node, int i, Map<Integer, List<Integer>> map) {
        if (null == node) {
            return;
        }
        List<Integer> list = map.computeIfAbsent(i, k -> new ArrayList<>());
        list.add(node.data);
        verticalOrderTraversalWithRecursionUtil(node.left, i - 1, map);
        verticalOrderTraversalWithRecursionUtil(node.right, i + 1, map);
    }

    public void boundaryTraversalCompleteBinaryTree(BinaryTree tree) {
        if (tree.root != null) {
            System.out.print(tree.root.data + " ");
            printLeftBoundaryCompleteBinaryTree(tree.root.left);
            printLeafNodesCompleteBinaryTree(tree.root);
            printRightBoundaryCompleteBinaryTree(tree.root.right);
        }
    }

    private void printLeftBoundaryCompleteBinaryTree(Node node) {
        while (node != null && node.left != null) {
            System.out.print(node.data + " ");
            node = node.left;
        }
    }

    private void printLeafNodesCompleteBinaryTree(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            if (temp.left == null && temp.right == null) {
                System.out.print(temp.data + " ");
            }
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
    }

    private void printRightBoundaryCompleteBinaryTree(Node node) {
        List<Node> list = new ArrayList<>();
        while (node != null && node.right != null) {
            list.add(node);
            node = node.right;
        }
        Collections.reverse(list);
        /*for (Node x : list) {
            System.out.println(x.data + " ");
        }*/
        list.forEach(n -> System.out.println(n.data + " "));
    }

    public void boundaryTraversalIncompleteBinaryTree(BinaryTree tree) {
        if (tree.root != null) {
            System.out.print(tree.root.data + " ");
            printLeftBoundaryIncompleteBinaryTree(tree.root.left);
            printLeafNodesIncompleteBinaryTree(tree.root.left);
            printLeafNodesIncompleteBinaryTree(tree.root.right);
            printRightBoundaryIncompleteBinaryTree(tree.root.right);
        }
    }

    private void printLeftBoundaryIncompleteBinaryTree(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            System.out.print(node.data + " ");
            printLeftBoundaryIncompleteBinaryTree(node.left);
        } else if (node.right != null) {
            System.out.print(node.data + " ");
            printLeftBoundaryIncompleteBinaryTree(node.right);
        }
    }

    private void printLeafNodesIncompleteBinaryTree(Node node) {
        if (node == null) {
            return;
        }
        printLeafNodesIncompleteBinaryTree(node.left);
        if (node.left == null && node.right == null) {
            System.out.print(node.data + " ");
        }
        printLeafNodesIncompleteBinaryTree(node.right);
    }

    private void printRightBoundaryIncompleteBinaryTree(Node node) {
        if (node == null) {
            return;
        }
        if (node.right != null) {
            printRightBoundaryIncompleteBinaryTree(node.right);
            System.out.print(node.data + " ");
        } else if (node.left != null) {
            printRightBoundaryIncompleteBinaryTree(node.left);
            System.out.print(node.data + " ");
        }
    }

    public void boundaryLevelOrderTraversal(BinaryTree tree) {
        System.out.print(tree.root.data + "  ");
        Queue<Node> queue = new LinkedList<>();
        queue.add(tree.root);
        int count = 0, limit = 1;
        while (!queue.isEmpty()) {
            List<Node> list = new ArrayList<>();
            int newLimit = 0;
            while (count < limit) {
                Node n = queue.poll();
                if (n.left != null) {
                    list.add(n.left);
                    newLimit++;
                }
                if (n.right != null) {
                    list.add(n.right);
                    newLimit++;
                }
                count++;
            }
            printList(list);
            queue.addAll(list);
            count = 0;
            limit = newLimit;
        }
    }

    private void printList(List<Node> list) {
        int i = 0, j = list.size() - 1;
        while (i < j) {
            System.out.print(list.get(i) + " ");
            System.out.print(list.get(j) + " ");
            i++;
            j--;
        }
        if (i == j) {
            System.out.println(list.get(i));
        }
    }
}
