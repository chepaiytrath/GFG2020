package datastructures.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import datastructures.binarytree.BinaryTree.Node;

public class CheckingAndPrinting {
    TraversalAndView t = new TraversalAndView();

    public void checkForChildrenSumProperty(BinaryTree tree) {
        boolean isSatisfied = checkForChildrenSumPropertyUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkForChildrenSumPropertyUtil(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return true;
        }
        boolean isLeftSatisfied = checkForChildrenSumPropertyUtil(node.left);
        boolean isRightSatisfied = checkForChildrenSumPropertyUtil(node.right);

        int sum = 0;
        if (node.left != null) {
            sum += node.left.data;
        }
        if (node.right != null) {
            sum += node.right.data;
        }
        return isLeftSatisfied && isRightSatisfied && (sum == node.data);
    }

    public void checkIfSumOfCoveredNodesEqualsSumOfUncoveredNodesWithoutRecursionWithQueue(BinaryTree tree) {
        Queue<Node> nodes = new LinkedList<>();

        int leftBoundarySum = 0;
        int rightBoundarySum = 0;
        int uncoveredSum = tree.root.data;

        extractLeftBoundaryIntoQueue(tree.root.left, nodes);

        Node curr;
        Node prev = null;
        while (!nodes.isEmpty()) {
            curr = nodes.poll();
            uncoveredSum += curr.data;
            if (!(prev != null && prev.right == curr) && curr.left != null) {
                leftBoundarySum += sumOfNodes(curr.right);
            }
            prev = curr;
        }

        extractRightBoundaryIntoQueue(tree.root.right, nodes);
        prev = null;
        while (!nodes.isEmpty()) {
            curr = nodes.poll();
            uncoveredSum += curr.data;
            if (!(prev != null && prev.left == curr) && curr.right != null) {
                rightBoundarySum += sumOfNodes(curr.left);
            }
            prev = curr;
        }
        System.out.println(uncoveredSum == leftBoundarySum + rightBoundarySum);
    }

    private void extractRightBoundaryIntoQueue(Node node, Queue<Node> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(node);
        if (node.right != null) {
            extractRightBoundaryIntoQueue(node.right, nodes);
        } else if (node.left != null) {
            extractRightBoundaryIntoQueue(node.left, nodes);
        }
    }

    private void extractLeftBoundaryIntoQueue(Node node, Queue<Node> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(node);
        if (node.left != null) {
            extractLeftBoundaryIntoQueue(node.left, nodes);
        } else if (node.right != null) {
            extractLeftBoundaryIntoQueue(node.right, nodes);
        }
    }

    private int sumOfNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return sumOfNodes(node.left) + sumOfNodes(node.right) + node.data;
    }

    public void checkIfSumOfCoveredNodesEqualsSumOfUncoveredNodesWithoutRecursionWithoutQueue(BinaryTree tree) {
        Node root = tree.root;
        int sumUncovered = findUncoveredSum(root);
        int sumTotalTree = sumOfNodes(root);
        boolean isSatisfied = sumTotalTree - sumUncovered == sumUncovered;
        System.out.println(isSatisfied);
    }

    private int findUncoveredSum(Node node) {
        int lbs = findUncoveredSumLeftBoundary(node.left);
        int rbs = findUncoveredSumRightBoundary(node.right);
        return lbs + rbs + node.data;
    }

    private int findUncoveredSumLeftBoundary(Node node) {
        if (node.left == null && node.right == null) {
            return node.data;
        }
        if (node.left != null) {
            return node.data + findUncoveredSumLeftBoundary(node.left);
        } else {
            return node.data + findUncoveredSumLeftBoundary(node.right);
        }
    }

    private int findUncoveredSumRightBoundary(Node node) {
        if (node.left == null && node.right == null) {
            return node.data;
        }
        if (node.right != null) {
            return node.data + findUncoveredSumLeftBoundary(node.right);
        } else {
            return node.data + findUncoveredSumLeftBoundary(node.left);
        }
    }

    public void checkIfInorderPreorderPostorderTraversalsAreOfSameTree(String in, String pre, String post) {
        boolean isSatisfied = checkIfInorderPreorderPostorderTraversalsAreOfSameTreeUtil(in, pre, post);
        System.out.println(isSatisfied);
    }

    private boolean checkIfInorderPreorderPostorderTraversalsAreOfSameTreeUtil(String in, String pre, String post) {
        if (in.length() == 1 && pre.length() == 1 && post.length() == 1) {
            return in.charAt(0) == pre.charAt(0) && pre.charAt(0) == post.charAt(0);
        }
        int root = pre.charAt(0);
        int rootIndexInorder = in.indexOf(root); // left subtree size
        boolean isLeftSatisfied = checkIfInorderPreorderPostorderTraversalsAreOfSameTreeUtil(
                in.substring(0, rootIndexInorder), pre.substring(1, 1 + rootIndexInorder),
                post.substring(0, rootIndexInorder));
        boolean isRightSatisfied = checkIfInorderPreorderPostorderTraversalsAreOfSameTreeUtil(
                in.substring(rootIndexInorder + 1), pre.substring(1 + rootIndexInorder),
                post.substring(rootIndexInorder, post.length() - 1));
        return isLeftSatisfied && isRightSatisfied;
    }

    public void checkIfLeafTraversalOfTwoBinaryTreesIsSame(BinaryTree tree1, BinaryTree tree2) {
        boolean isSatisfied = checkIfLeafTraversalOfTwoBinaryTreesIsSameUtil(tree1.root, tree2.root);
        System.out.println(isSatisfied);
    }

    private static boolean checkIfLeafTraversalOfTwoBinaryTreesIsSameUtil(Node root1, Node root2) {
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        st1.add(root1);
        st2.add(root2);

        while (!st1.isEmpty() || !st2.isEmpty()) {
            if (st1.isEmpty() || st2.isEmpty()) {
                return false;
            }
            Node node1 = st1.pop();
            while (node1 != null && !node1.isLeaf()) {
                if (node1.right != null) {
                    st1.add(node1.right);
                }
                if (node1.left != null) {
                    st1.add(node1.left);
                }
                node1 = st1.pop();
            }

            Node node2 = st2.pop();
            while (node2 != null && !node2.isLeaf()) {
                if (node2.right != null) {
                    st1.add(node2.right);
                }
                if (node2.left != null) {
                    st1.add(node2.left);
                }
                node2 = st2.pop();
            }

            if ((node1 == null && node2 != null) || (node1 != null && node2 == null)) {
                return false;
            }
            if ((node1 != null && node2 != null) && node1.data != node2.data) {
                return false;
            }
        }
        return true;
    }

    public void checkIfLevelOrderOfCompleteBinaryTreeIsMinHeap(int[] arr) {
        // int[] arr = {10, 15, 14, 25, 30};
        // int[] arr = {30, 56, 22, 49, 30, 51, 2, 67};
        boolean isSatisfied = checkIfLevelOrderOfCompleteBinaryTreeIsMinHeapUtil(arr);
        System.out.println(isSatisfied);
    }

    private boolean checkIfLevelOrderOfCompleteBinaryTreeIsMinHeapUtil(int[] arr) {
        // Another approach to get rid of the break condition is to start from i = (n/2
        // - 1) to i = 0
        // https://www.geeksforgeeks.org/given-level-order-traversal-binary-tree-check-tree-min-heap/
        for (int i = 0; i < arr.length; i++) {
            int li = 2 * i + 1;
            int ri = 2 * i + 2;
            if (li > arr.length - 1) {
                break;
            }
            if (arr[i] > arr[li] || arr[i] > arr[ri]) {
                return false;
            }
        }
        return true;
    }

    public void checkIfBinaryTreeIsFullWithRecursion(BinaryTree tree) {
        boolean isSatisfied = checkIfBinaryTreeIsFullWithRecursionUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsFullWithRecursionUtil(Node node) {
        if (node == null || node.isLeaf()) {
            return true;
        }
        if (node.left != null && node.right != null) {
            return checkIfBinaryTreeIsFullWithRecursionUtil(node.left)
                    && checkIfBinaryTreeIsFullWithRecursionUtil(node.right);
        }
        return false;
    }

    public void checkIfBinaryTreeIsFullWithoutRecursionWithQueue(BinaryTree tree) {
        boolean isSatisfied = checkIfBinaryTreeIsFullWithoutRecursionWithQueueUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsFullWithoutRecursionWithQueueUtil(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (n.isLeaf()) {
                continue;
            }
            if (n.left != null && n.right != null) {
                q.add(n.left);
                q.add(n.right);
            } else {
                return false;
            }
        }
        return true;
    }

    public void checkIfBinaryTreeIsComplete(BinaryTree tree) {
        boolean isSatisfied = checkIfBinaryTreeIsCompleteUtil2(tree);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsCompleteUtil(BinaryTree tree) {
        Queue<Node> que = new LinkedList<>();
        que.add(tree.root);
        boolean flag = false;
        while (!que.isEmpty()) {
            Node node = que.poll();
            if (flag && !node.isLeaf()) {
                return false;
            }
            if (node.isLeaf() && !flag) {
                flag = true;
                continue;
            }
            if (node.left == null && node.right != null) {
                return false;
            }
            if (node.left != null) {
                que.add(node.left);
            }
            if (node.right != null) {
                que.add(node.right);
            }
        }
        return true;
    }

    private boolean checkIfBinaryTreeIsCompleteUtil2(BinaryTree tree) {
        Queue<Node> que = new LinkedList<>();
        que.add(tree.root);
        boolean flag = false;
        // NON-FULL node: 1 child node: either only left OR only right
        // flag : Takes care of following conditions
        // 1. Once a NON-FULL node is encountered, all subsequent nodes must be leaf
        // nodes
        // 2. If left child is null, right child should also be null
        while (!que.isEmpty()) {
            Node node = que.poll();
            if (node.left != null) {
                if (flag) { // 1.2. all subsequent nodes must be leaf nodes
                    return false;
                }
                que.add(node.left);
            } else {
                flag = true; // 2.1. If left child is null,
            }
            if (node.right != null) {
                if (flag) { // 2.2. right child should also be null
                    return false;
                }
                que.add(node.right);
            } else {
                flag = true; // 1.1. Once a NON-FULL node is encountered
            }
        }
        return true;
    }

    private int findHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(1 + findHeight(node.left), 1 + findHeight(node.right));
    }

    public void checkIfBinaryTreeIsPerfect(BinaryTree tree) {
        // Sample input
        /*
         * Perfect BinaryTree tree = new BinaryTree(10); tree.root.left = new Node(20);
         * tree.root.left.left = new Node(40); tree.root.left.right = new Node(50);
         * tree.root.right = new Node(30); tree.root.right.left = new Node(60);
         * tree.root.right.right = new Node(70);
         * 
         * Not perfect BinaryTree tree = new BinaryTree(1); tree.root.left = new
         * Node(2); tree.root.left.right = new Node(4); tree.root.right = new Node(3);
         * tree.root.right.left = new Node(5); tree.root.right.right = new Node(6);
         */

        Node root = tree.root;
        int height = findHeight(root);
        boolean isSatisfied = checkIfBinaryTreeIsPerfectUtil(root, height);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsPerfectUtil(Node node, int height) {
        if (node.isLeaf() && height == 1) {
            return true;
        } else if (node.isLeaf() && height != 1) {
            return false;
        }
        boolean isLeftSatisfied = false;
        boolean isRightSatisfied = false;
        if (node.left != null) {
            isLeftSatisfied = checkIfBinaryTreeIsPerfectUtil(node.left, height - 1);
        }
        if (node.right != null) {
            isRightSatisfied = checkIfBinaryTreeIsPerfectUtil(node.right, height - 1);
        }
        return isLeftSatisfied && isRightSatisfied;
    }

    public void checkIfBinaryTreeIsSumTree(BinaryTree tree) {
        boolean isSatisfied = checkIfBinaryTreeIsSumTreeUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsSumTreeUtil(Node node) {
        if (node == null || node.isLeaf()) {
            return true;
        }
        int sum = 0;
        if (node.left != null) {
            sum += node.left.isLeaf() ? node.left.data : 2 * node.left.data;
        }
        if (node.right != null) {
            sum += node.right.isLeaf() ? node.right.data : 2 * node.right.data;
        }
        return sum == node.data && checkIfBinaryTreeIsSumTreeUtil(node.left)
                && checkIfBinaryTreeIsSumTreeUtil(node.right);
    }

    public void checkIfBinaryTreeIsHeightBalanced(BinaryTree tree) {
        // GFG :
        // https://www.geeksforgeeks.org/check-given-binary-tree-follows-height-property-red-black-tree/
        boolean isSatisfied = checkIfBinaryTreeIsHeightBalancedUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsHeightBalancedUtil(Node node) {
        if (node == null || node.isLeaf()) {
            return true;
        }
        int lh = findHeight(node.left) + 1;
        int rh = findHeight(node.right) + 1;
        boolean flag = false;
        if (lh > rh) {
            flag = true;
        }
        return (flag ? lh <= 2 * rh : rh <= 2 * lh) && checkIfBinaryTreeIsHeightBalancedUtil(node.left)
                && checkIfBinaryTreeIsHeightBalancedUtil(node.right);
    }

    static class INT {
        int d;

        INT() {
            d = 0;
        }

        @Override
        public String toString() {
            return "INT{" + "d=" + d + '}';
        }
    }

    public void checkIfBinaryTreeIsHeightBalancedInOofN(BinaryTree tree) {
        // NOT GFG : GENERIC PROBLEM STATEMENT :
        // https://www.youtube.com/watch?v=LU4fGD-fgJQ
        INT i = new INT();
        boolean isSatisfied = checkIfBinaryTreeIsHeightBalancedInOofNUtil(tree.root, i);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsHeightBalancedInOofNUtil(Node node, INT par) {
        if (node == null) {
            par.d = -1;
            return true;
        }

        INT left = new INT();
        INT right = new INT();
        boolean leftSatisfied = checkIfBinaryTreeIsHeightBalancedInOofNUtil(node.left, left);
        boolean rightSatisfied = checkIfBinaryTreeIsHeightBalancedInOofNUtil(node.right, right);
        int diff = Math.abs(left.d - right.d);
        par.d = Math.max(left.d, right.d) + 1;
        return leftSatisfied && rightSatisfied && diff <= 1;
    }

    public void checkIfBinaryTreeIsMirrorOfItself(BinaryTree tree) {
        boolean isSatisfied = checkIfMirrorOfItselfUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfMirrorOfItselfUtil(Node root) {
        Queue<Node> que = new LinkedList<>();
        que.add(root.left);
        que.add(root.right);

        while (!que.isEmpty()) {
            Node left = que.poll();
            Node right = que.poll();

            if (left == null && right == null) {
                continue;
            }

            if ((left == null && right != null) || (left != null && right == null) || (left.data != right.data)) {
                return false;
            }
            que.add(left.left);
            que.add(right.right);
            que.add(left.right);
            que.add(right.left);
        }
        return true;
    }

    public void checkIfTwoBinaryTreesAreMirrorOfEachOtherWithRecursion(BinaryTree tree1, BinaryTree tree2) {
        boolean isSatisfied = checkIfTwoBinaryTreesAreMirrorOfEachOtherWithRecursionUtil(tree1.root, tree2.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfTwoBinaryTreesAreMirrorOfEachOtherWithRecursionUtil(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }

        return node1.data == node2.data
                && checkIfTwoBinaryTreesAreMirrorOfEachOtherWithRecursionUtil(node1.left, node2.right)
                && checkIfTwoBinaryTreesAreMirrorOfEachOtherWithRecursionUtil(node1.right, node2.left);
    }

    public void checkIfTwoBinaryTreesAreMirrorOfEachOtherWithoutRecursionWithTwoStacks(BinaryTree tree1,
            BinaryTree tree2) {
        // Use iterative inorder traversal

        boolean isSatisfied = checkIfTwoBinaryTreesAreMirrorOfEachOtherWithoutRecursionWithTwoStacksUtil(tree1.root,
                tree2.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfTwoBinaryTreesAreMirrorOfEachOtherWithoutRecursionWithTwoStacksUtil(Node root1, Node root2) {
        Node curr1 = root1, curr2 = root2;
        Stack<Node> st1 = new Stack<>(), st2 = new Stack<>();
        while (true) {
            while (curr1 != null && curr2 != null) {
                if (curr1.data != curr2.data) {
                    return false;
                }
                st1.add(curr1);
                st2.add(curr2);
                curr1 = curr1.left;
                curr2 = curr2.right;
            }

            if (!(curr1 == null && curr2 == null)) {
                return false;
            }

            if (!st1.isEmpty() && !st2.isEmpty()) {
                curr1 = st1.pop();
                curr2 = st2.pop();
                curr1 = curr1.right;
                curr2 = curr2.left;
            } else {
                break;
            }
        }
        return true;
    }

    public void checkIfTwoBinaryTreesAreIdenticalWithRecursion(BinaryTree tree1, BinaryTree tree2) {
        boolean isSatisfied = checkIfTwoBinaryTreesAreIdenticalWithRecursionUtil(tree1.root, tree2.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfTwoBinaryTreesAreIdenticalWithRecursionUtil(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (!(node1 != null && node2 != null)) {
            return false;
        }
        return node1.data == node2.data && checkIfTwoBinaryTreesAreIdenticalWithRecursionUtil(node1.left, node2.left)
                && checkIfTwoBinaryTreesAreIdenticalWithRecursionUtil(node1.right, node2.right);
    }

    public void checkIfTwoBinaryTreesAreIdenticalWithoutRecursionWithTwoStacks(BinaryTree tree1, BinaryTree tree2) {
        // Use iterative inorder traversal

        boolean isSatisfied = checkIfTwoBinaryTreesAreIdenticalWithoutRecursionWithTwoStacksUtil(tree1.root,
                tree2.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfTwoBinaryTreesAreIdenticalWithoutRecursionWithTwoStacksUtil(Node root1, Node root2) {
        Node curr1 = root1, curr2 = root2;
        Stack<Node> st1 = new Stack<>(), st2 = new Stack<>();
        while (true) {
            while (curr1 != null && curr2 != null) {
                if (curr1.data != curr2.data) {
                    return false;
                }
                st1.add(curr1);
                st2.add(curr2);
                curr1 = curr1.left;
                curr2 = curr2.left;
            }

            if (!(curr1 == null && curr2 == null)) {
                return false;
            }

            if (!st1.isEmpty() && !st2.isEmpty()) {
                curr1 = st1.pop();
                curr2 = st2.pop();
                curr1 = curr1.right;
                curr2 = curr2.right;
            } else {
                break;
            }
        }
        return true;
    }

    public void checkIfTwoBinaryTreesAreIdenticalWithoutRecursionWithQueue(BinaryTree tree1, BinaryTree tree2) {
        // Use iterative inorder traversal

        boolean isSatisfied = checkIfTwoBinaryTreesAreIdenticalWithoutRecursionWithQueueUtil(tree1.root, tree2.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfTwoBinaryTreesAreIdenticalWithoutRecursionWithQueueUtil(Node root1, Node root2) {
        Node curr1 = root1, curr2 = root2;
        Queue<Node> que = new LinkedList<>();
        que.add(curr1);
        que.add(curr2);

        while (!que.isEmpty()) {
            curr1 = que.poll();
            curr2 = que.poll();

            if (curr1 == null && curr2 == null) {
                continue;
            }

            if (!(curr1 != null && curr2 != null)) {
                return false;
            }
            if (curr1.data != curr2.data) {
                return false;
            }

            que.add(curr1.left);
            que.add(curr2.left);
            que.add(curr1.right);
            que.add(curr2.right);
        }
        return true;
    }

    public void checkIfOneTreeIsSubtreeOfAnotherTreeWithRecursion(BinaryTree parent, BinaryTree child) {
        boolean isSatisfied = checkIfOneTreeIsSubtreeOfAnotherTreeWithRecursionUtil(parent.root, child.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfOneTreeIsSubtreeOfAnotherTreeWithRecursionUtil(Node parent, Node child) {
        if (child == null) {
            return true;
        }
        if (parent == null) {
            return false;
        }

        if (isIdentical(parent, child)) {
            return true;
        }

        return checkIfOneTreeIsSubtreeOfAnotherTreeWithRecursionUtil(parent.left, child)
                && checkIfOneTreeIsSubtreeOfAnotherTreeWithRecursionUtil(parent.right, child);
    }

    private boolean isIdentical(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (!(n1 != null && n2 != null)) {
            return false;
        }
        return n1.data == n2.data && isIdentical(n1.left, n2.left) && isIdentical(n1.right, n2.right);
    }

    public void checkIfOneTreeIsSubtreeOfAnotherTreeWithoutRecursionUsingPreOrderInOrder(BinaryTree parent,
            BinaryTree child) {
        boolean isSatisfied = checkIfOneTreeIsSubtreeOfAnotherTreeWithoutRecursionUsingPreOrderInOrderUtil(parent.root,
                child.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfOneTreeIsSubtreeOfAnotherTreeWithoutRecursionUsingPreOrderInOrderUtil(Node parent,
            Node child) {
        List<String> inP = new ArrayList<>();
        populateInorder(parent, inP);
        String inParent = String.join("", inP);
        List<String> inC = new ArrayList<>();
        populateInorder(child, inC);
        String inChild = String.join("", inC);

        List<String> preP = new ArrayList<>();
        populatePreorder(parent, preP);
        String preParent = String.join("", preP);
        List<String> preC = new ArrayList<>();
        populatePreorder(child, preC);
        String preChild = String.join("", preC);

        if (!inParent.contains(inChild) || !preParent.contains(preChild)) {
            return false;
        } else {
            return true;
        }
    }

    private void populateInorder(Node node, List<String> in) {
        if (node == null) {
            return;
        }
        populateInorder(node.left, in);
        in.add(String.valueOf(node.data));
        populateInorder(node.right, in);
    }

    private void populatePreorder(Node node, List<String> pre) {
        if (node == null) {
            return;
        }
        pre.add(String.valueOf(node.data));
        populatePreorder(node.left, pre);
        populatePreorder(node.right, pre);
    }

    public void checkIfBinaryTreeHasDuplicateValuesWithRecursion(BinaryTree tree) {
        boolean isSatisfied = checkIfBinaryTreeHasDuplicateValuesWithRecursionUtil(tree.root, new HashSet<Integer>());
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeHasDuplicateValuesWithRecursionUtil(Node node, HashSet<Integer> set) {
        if (node == null) {
            return false;
        }

        if (set.contains(node.data)) {
            return true;
        }

        set.add(node.data);

        return checkIfBinaryTreeHasDuplicateValuesWithRecursionUtil(node.left, set)
                || checkIfBinaryTreeHasDuplicateValuesWithRecursionUtil(node.right, set);
    }

    public void checkIfTwoNodesAreCousins(int a, int b, BinaryTree tree) {
        Node root = tree.root;
        boolean isSatisfied = checkIfTwoNodesAreCousinsUtil(a, b, root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfTwoNodesAreCousinsUtil(int a, int b, Node root) {
        return (findLevel(a, root, 1) == findLevel(b, root, 1)) && (!isSibling(a, b, root));
    }

    private int findLevel(int val, Node node, int level) {
        if (node == null) {
            return 0;
        }
        if (node.data == val) {
            return level;
        }
        int leftLevel = findLevel(val, node.left, level + 1);
        if (leftLevel != 0) {
            return leftLevel;
        }
        return findLevel(val, node.right, level + 1);
    }

    private boolean isSibling(int a, int b, Node node) {
        if (node == null) {
            return false;
        }

        if ((node.left != null && node.right != null)
                && ((node.left.data == a && node.right.data == b) || (node.left.data == b && node.right.data == a))) {
            return true;
        }

        return isSibling(a, b, node.left) || isSibling(a, b, node.right);
    }

    public void checkIfLeafNodesAreOnSameLevelWithRecursion(BinaryTree tree) {
        Node root = tree.root;
        Integer leafLevel = null;
        boolean isSatisfied = checkIfLeafNodesAreOnSameLevelWithRecursionUtil(root, leafLevel, 1);
        System.out.println(isSatisfied);
    }

    private boolean checkIfLeafNodesAreOnSameLevelWithRecursionUtil(Node node, Integer leafLevel, int level) {
        if (node == null) {
            return true;
        }
        if (node.isLeaf()) {
            if (leafLevel == null) {
                leafLevel = level;
                return true;
            } else {
                return leafLevel == level;
            }
        }
        return checkIfLeafNodesAreOnSameLevelWithRecursionUtil(node.left, leafLevel, level + 1)
                && checkIfLeafNodesAreOnSameLevelWithRecursionUtil(node.right, leafLevel, level + 1);
    }

    public void checkIfLeafNodesAreOnSameLevelWithRecursionAndMap(BinaryTree tree) {
        Node root = tree.root;
        Map<Integer, Integer> map = new HashMap<>();
        checkIfLeafNodesAreOnSameLevelWithRecursionAndMapUtil(root, map, 1);
        if (new HashSet<Integer>(map.values()).size() > 1) {
            System.out.println("LEAF NODES NOT ON SAME LEVEL");
        } else {
            System.out.println("LEAF NODES ON SAME LEVEL");
        }
    }

    private void checkIfLeafNodesAreOnSameLevelWithRecursionAndMapUtil(Node node, Map<Integer, Integer> map,
            int level) {
        if (node.isLeaf()) {
            map.put(node.data, level);
            return;
        }

        if (node.left != null) {
            checkIfLeafNodesAreOnSameLevelWithRecursionAndMapUtil(node.left, map, level + 1);
        }
        if (node.right != null) {
            checkIfLeafNodesAreOnSameLevelWithRecursionAndMapUtil(node.right, map, level + 1);
        }
    }

    public void checkIfLeafNodesAreOnSameLevelWithoutRecursionWithStack(BinaryTree tree) {
        boolean isSatisfied = checkIfLeafNodesAreOnSameLevelWithoutRecursionWithStackUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfLeafNodesAreOnSameLevelWithoutRecursionWithStackUtil(Node root) {
        if (root.isLeaf()) {
            return true;
        }

        Node curr = root;
        int leafLevel = -1;
        Stack<Node> st = new Stack<>();
        while (curr != null || (!st.isEmpty() && st.size() > 1)) {
            while (curr != null) {
                st.add(curr);
                curr = curr.left;
            }
            int size = st.size();
            curr = st.peek();
            if (curr.right == null) {
                curr = st.pop();
                if (curr.isLeaf()) {
                    if (leafLevel == -1) {
                        leafLevel = size;
                    } else if (leafLevel != size) {
                        return false;
                    }
                }
                while (!st.isEmpty() && curr == st.peek().right) {
                    curr = st.pop();
                }
                curr = null;
                continue;
            }
            curr = curr.right;
        }
        return true;
    }

    public void checkIfLeafNodesAreOnSameLevelWithoutRecursionWithQueueLevelOrderTraversal(BinaryTree tree) {
        boolean isSatisfied = checkIfLeafNodesAreOnSameLevelWithoutRecursionWithQueueLevelOrderTraversalUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfLeafNodesAreOnSameLevelWithoutRecursionWithQueueLevelOrderTraversalUtil(Node root) {
        if (root == null)
            return true;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int result = Integer.MAX_VALUE;
        int level = 0;
        while (q.size() != 0) {
            int size = q.size();
            level++;
            while (size > 0) {
                Node temp = q.remove();
                if (temp.left != null) {
                    q.add(temp.left);
                    if (temp.left.left == null && temp.left.right == null) {
                        if (result == Integer.MAX_VALUE)
                            result = level;
                        else if (result != level)
                            return false;
                    }
                }
                if (temp.right != null) {
                    q.add(temp.right);
                    if (temp.right.left == null && temp.right.right == null) {
                        if (result == Integer.MAX_VALUE)
                            result = level;
                        else if (result != level)
                            return false;
                    }
                }
                size--;
            }

        }
        return true;
    }

    public void checkIfRemovingAnEdgeInBinaryTreeCanDivideItIntoTwoHalvesWithEqualSizeTopDown(BinaryTree tree) {
        // 2 approaches: Top down: O(n2), Bottom up: O(n)
        int totalSize = tree.size();
        boolean isSatisfied = checkIfRemovingAnEdgeInBinaryTreeCanDivideItIntoTwoHalvesWithEqualSizeTopDownUtil(
                tree.root, totalSize);
        System.out.println(isSatisfied);
    }

    private boolean checkIfRemovingAnEdgeInBinaryTreeCanDivideItIntoTwoHalvesWithEqualSizeTopDownUtil(Node node,
            int totalSize) {
        if (node == null) {
            return false;
        }
        int currSize = findSize(node);
        if (totalSize - currSize == currSize) {
            return true;
        }
        return checkIfRemovingAnEdgeInBinaryTreeCanDivideItIntoTwoHalvesWithEqualSizeTopDownUtil(node.left, totalSize)
                || checkIfRemovingAnEdgeInBinaryTreeCanDivideItIntoTwoHalvesWithEqualSizeTopDownUtil(node.right,
                        totalSize);
    }

    private int findSize(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + findSize(node.left) + findSize(node.right);
    }

    class RESULT {
        Boolean res = false;
    }

    public void checkIfRemovingAnEdgeInBinaryTreeCanDivideItIntoTwoHalvesWithEqualSizeBottomUp(BinaryTree tree) {
        RESULT obj = new RESULT();
        int totalSize = findSize(tree.root);
        checkIfRemovingAnEdgeInBinaryTreeCanDivideItIntoTwoHalvesWithEqualSizeBottomUpUtil(tree.root, totalSize, obj);
        System.out.println(obj.res);
    }

    private int checkIfRemovingAnEdgeInBinaryTreeCanDivideItIntoTwoHalvesWithEqualSizeBottomUpUtil(Node node,
            int totalSize, RESULT obj) {
        if (node == null) {
            return 0;
        }
        int currSize = checkIfRemovingAnEdgeInBinaryTreeCanDivideItIntoTwoHalvesWithEqualSizeBottomUpUtil(node.left,
                totalSize, obj)
                + checkIfRemovingAnEdgeInBinaryTreeCanDivideItIntoTwoHalvesWithEqualSizeBottomUpUtil(node.left,
                        totalSize, obj)
                + 1;
        if (totalSize - currSize == currSize) {
            obj.res = true;
        }
        return currSize;
    }

    public void checkIfThereIsARootToLeafPathWithGivenSequence(BinaryTree tree, int[] arr) {
        boolean isSatisfied = checkIfThereIsARootToLeafPathWithGivenSequenceUtil(tree.root, arr, arr.length, 0);
        System.out.println(isSatisfied);
    }

    private boolean checkIfThereIsARootToLeafPathWithGivenSequenceUtil(Node node, int[] arr, int arrSize, int index) {
        if (index == arrSize) {
            return true;
        }
        if (node == null) {
            return false;
        }
        return ((index < arrSize) && (arr[index] == node.data))
                && (checkIfThereIsARootToLeafPathWithGivenSequenceUtil(node.left, arr, arrSize, index + 1)
                        || checkIfThereIsARootToLeafPathWithGivenSequenceUtil(node.right, arr, arrSize, index + 1));
    }

    public void printSiblingOfNodeInBinaryTree(BinaryTree tree, int data) {
        // BinaryTree tree = new BinaryTree(1);
        // tree.root.left = new Node(2);
        // tree.root.left.left = new Node(4);
        // tree.root.left.left.left = new Node(8);
        // tree.root.left.left.right = new Node(9);
        // tree.root.left.right = new Node(5);
        // tree.root.left.right.left = new Node(10);
        // tree.root.left.right.right = new Node(11);
        // tree.root.right = new Node(3);
        // tree.root.right.left = new Node(6);
        // tree.root.right.left.left = new Node(12);
        // tree.root.right.left.right = new Node(13);
        // tree.root.right.right = new Node(7);
        // tree.root.right.right.left = new Node(14);
        // tree.root.right.right.right = new Node(15);

        int level = findLevel(data, tree.root, 1);
        printSiblingOfNodeInBinaryTreeUtil(tree.root, data, level - 1);
    }

    private void printSiblingOfNodeInBinaryTreeUtil(Node node, int data, int level) {
        if (node == null) {
            return;
        }
        if (level == 1 && notChild(node, data)) {
            if (node.left != null) {
                System.out.print(node.left.data + " ");
            }
            if (node.right != null) {
                System.out.print(node.right.data + " ");
            }
        } else {
            printSiblingOfNodeInBinaryTreeUtil(node.left, data, level - 1);
            printSiblingOfNodeInBinaryTreeUtil(node.right, data, level - 1);
        }
    }

    private boolean notChild(Node node, int data) {
        if (node.left != null && node.left.data == data) {
            return false;
        }
        if (node.right != null && node.right.data == data) {
            return false;
        }
        return true;
    }

    public void printAllNodesThatHaveNoSiblings(BinaryTree tree) {
        printAllNodesThatHaveNoSiblingsUtil(tree.root);
    }

    private void printAllNodesThatHaveNoSiblingsUtil(Node node) {
        if (node == null || node.isLeaf()) {
            return;
        }
        if (node.left != null && node.right != null) {
            printAllNodesThatHaveNoSiblingsUtil(node.left);
            printAllNodesThatHaveNoSiblingsUtil(node.right);
        } else if (node.left != null) {
            System.out.print(node.left.data + " ");
            printAllNodesThatHaveNoSiblingsUtil(node.left);
        } else if (node.right != null) {
            System.out.print(node.right.data + " ");
            printAllNodesThatHaveNoSiblingsUtil(node.right);
        }
    }

    public void printAllRootToLeafPathsOnePerLineWithRecursion(BinaryTree tree) {
        List<Integer> list = new ArrayList<>();
        printAllRootToLeafPathsOnePerLineWithRecursionUtil(tree.root, list);
    }

    private void printAllRootToLeafPathsOnePerLineWithRecursionUtil(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.data);
        if (node.isLeaf()) {
            System.out.print(list);
        } else {
            printAllRootToLeafPathsOnePerLineWithRecursionUtil(node.left, new ArrayList<Integer>(list));
            printAllRootToLeafPathsOnePerLineWithRecursionUtil(node.right, new ArrayList<Integer>(list));
        }
    }

    public void printAllRootToLeafPathsOnePerLineWithoutRecursion(BinaryTree tree) {
        Node root = tree.root;
        Stack<Node> st = new Stack<>();
        Node curr = root;
        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                st.add(curr);
                curr = curr.left;
            }
            curr = st.peek();
            if (curr.isLeaf()) {
                printStackInBottomUp(st);
                System.out.println();
                curr = st.pop();
                while (!st.isEmpty() && curr == st.peek().right) {
                    curr = st.pop();
                }
                curr = null;
            } else {
                curr = curr.right;
            }
        }
    }

    private void printStackInBottomUp(Stack<Node> st) {
        if (st.isEmpty()) {
            return;
        }
        Node node = st.pop();
        printStackInBottomUp(st);
        System.out.print(node.data + " ");
        st.push(node);
    }

    public void printAllRootToLeafPathsWithTheirRelativePositions(BinaryTree tree) {
        printAllRootToLeafPathsWithTheirRelativePositionsUtil(tree.root, new ArrayList<Integer>(),
                new ArrayList<Integer>(), 0, 0);
    }

    private void printAllRootToLeafPathsWithTheirRelativePositionsUtil(Node node, List<Integer> list,
            List<Integer> dirList, int lowestNegative, int dir) {
        if (node == null) {
            return;
        }
        lowestNegative = Math.min(lowestNegative, dir);
        list.add(node.data);
        dirList.add(dir);
        if (node.isLeaf()) {
            int delta = Math.abs(lowestNegative);
            for (int i = 0; i < list.size(); i++) {
                if (lowestNegative < 0) {
                    printChar('_', delta + dirList.get(i));
                } else {
                    printChar('_', dirList.get(i));
                }
                System.out.print(list.get(i) + " ");
                System.out.println("");
            }
            System.out.println("");
        } else {
            printAllRootToLeafPathsWithTheirRelativePositionsUtil(node.left, new ArrayList<Integer>(list),
                    new ArrayList<Integer>(dirList), lowestNegative, dir - 1);
            printAllRootToLeafPathsWithTheirRelativePositionsUtil(node.right, new ArrayList<Integer>(list),
                    new ArrayList<Integer>(dirList), lowestNegative, dir + 1);
        }
    }

    private void printChar(char ch, int lim) {
        while (lim > 0) {
            System.out.print(ch + " ");
            lim--;
        }
    }

    public void printPathFromRootToGivenNodeInBinaryTree(BinaryTree tree, int data) {
        int[] arr = new int[7];
        if (!printPathFromRootToGivenNodeInBinaryTreeUtil(tree.root, data, arr, 0)) {
            System.out.println("No Path");
        }
    }

    private boolean printPathFromRootToGivenNodeInBinaryTreeUtil(Node node, int data, int[] arr, int index) {
        if (node == null) {
            return false;
        }
        arr[index] = node.data;
        if (node.data == data) {
            printArr(arr, index);
            return true;
        }
        index++;
        return printPathFromRootToGivenNodeInBinaryTreeUtil(node.left, data, arr, index)
                || printPathFromRootToGivenNodeInBinaryTreeUtil(node.right, data, arr, index);
    }

    private void printArr(int[] arr, int ind) {
        for (int i = 0; i <= ind; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public void calculateDiameterOfBinaryTreeWithRecursionApproach1(BinaryTree tree) {
        // SAMPLE INPUT
        // BinaryTree tree = new BinaryTree(1);
        // tree.root.left = new Node(2);
        // tree.root.left.left = new Node(4);
        // tree.root.left.left.left = new Node(6);
        // tree.root.left.left.right = new Node(7);
        // tree.root.left.left.right.left = new Node(9);
        // tree.root.left.left.right.left.right = new Node(12);
        // tree.root.left.right = new Node(5);
        // tree.root.left.right.right = new Node(8);
        // tree.root.left.right.right.left = new Node(10);
        // tree.root.left.right.right.right = new Node(11);
        // tree.root.left.right.right.right.left = new Node(13);
        // tree.root.right = new Node(3);

        // Basic approach : O(n2)
        // Only one Rule: Diamter of a tree is largest value of the following 3
        // 1. Left subtree diameter
        // 2. Right subtree diameter
        // 3. 1 + left subtree height + right subtree height

        int diameter = calculateDiameterOfBinaryTreeWithRecursionApproach1Util(tree.root);
        System.out.println(diameter);
    }

    private int calculateDiameterOfBinaryTreeWithRecursionApproach1Util(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = findHeight(node.left);
        int rightHeight = findHeight(node.right);

        int leftDiameter = calculateDiameterOfBinaryTreeWithRecursionApproach1Util(node.left);
        int rightDiameter = calculateDiameterOfBinaryTreeWithRecursionApproach1Util(node.right);

        return Math.max(1 + leftHeight + rightHeight, Math.max(leftDiameter, rightDiameter));
    }

    public void calculateDiameterOfBinaryTreeWithRecursionApproach2(BinaryTree tree) {
        // Improvement over basic approach : O(n)
        // Same one Rule: Diamter of a tree is largest value of the following 3
        // 1. Left subtree diameter
        // 2. Right subtree diameter
        // 3. 1 + left subtree height + right subtree height

        int diameter = calculateDiameterOfBinaryTreeWithRecursionApproach2Util(tree.root, new Height());
        System.out.println(diameter);
    }

    class Height {
        int val = Integer.MIN_VALUE;
        Node node = null;
        int leftHeight = 0, rightHeight = 0;
    }

    private int calculateDiameterOfBinaryTreeWithRecursionApproach2Util(Node node, Height h) {
        if (node == null) {
            h.val = 0;
            return 0;
        }
        Height leftHeight = new Height();
        Height rightHeight = new Height();
        int leftDiameter = calculateDiameterOfBinaryTreeWithRecursionApproach2Util(node.left, leftHeight);
        int rightDiameter = calculateDiameterOfBinaryTreeWithRecursionApproach2Util(node.right, rightHeight);

        h.val = 1 + Math.max(leftHeight.val, rightHeight.val);
        return Math.max(1 + leftHeight.val + rightHeight.val, Math.max(leftDiameter, rightDiameter));
    }

    public void calculateDiameterOfBinaryTreeWithRecursionApproach3(BinaryTree tree) {
        // Different Rule: Diameter of a tree is max of 1 + left subtree height + right
        // subtree height for each node in tree
        Height h = new Height();
        calculateDiameterOfBinaryTreeWithRecursionApproach3Util(tree.root, h);
        int diameter = h.val;
        System.out.println(diameter);
    }

    private int calculateDiameterOfBinaryTreeWithRecursionApproach3Util(Node node, Height height) {
        if (node == null) {
            return 0;
        }

        int leftHeight = calculateDiameterOfBinaryTreeWithRecursionApproach3Util(node.left, height);
        int rightHeight = calculateDiameterOfBinaryTreeWithRecursionApproach3Util(node.right, height);
        height.val = Math.max(height.val, 1 + leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public void printLongestLeafToLeafPath(BinaryTree tree) {
        // Print diameter path of tree
        Height h = new Height();
        calculateMiddleNodeInDiameter(tree.root, h);
        printDiameter(h.node.left, h.leftHeight, false);
        System.out.print(h.node.data + " ");
        printDiameter(h.node.right, h.rightHeight, true);
    }

    private int calculateMiddleNodeInDiameter(Node node, Height h) {
        if (node == null) {
            return 0;
        }
        int lh = calculateMiddleNodeInDiameter(node.left, h);
        int rh = calculateMiddleNodeInDiameter(node.right, h);
        if (h.val < 1 + lh + rh) {
            h.val = 1 + lh + rh;
            h.leftHeight = lh;
            h.rightHeight = rh;
            h.node = node;
        }
        return 1 + Math.max(lh, rh);
    }

    private void printDiameter(Node node, int height, boolean flag) {
        int arr[] = new int[100];
        int index = 1;
        fetchLongestNodeToLeafRoute(node, arr, index, height);
        printArray(arr, height, flag);
    }

    private void printArray(int[] arr, int height, boolean flag) {
        if (flag) {
            for (int i = 1; i <= height; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            for (int i = height; i >= 1; i--) {
                System.out.print(arr[i] + " ");
            }
        }
    }

    private boolean fetchLongestNodeToLeafRoute(Node node, int[] arr, int index, int height) {
        if (node == null) {
            return false;
        }
        arr[index] = node.data;
        if (index == height) {
            return true;
        }
        index++;
        return fetchLongestNodeToLeafRoute(node.left, arr, index, height)
                || fetchLongestNodeToLeafRoute(node.right, arr, index, height);
    }

    public void printAllNodesAtKDistanceFromRootWithRecursion(BinaryTree tree, int k) {
        printAllNodesAtKDistanceFromRootWithRecursionUtil(tree.root, k);
    }

    private void printAllNodesAtKDistanceFromRootWithRecursionUtil(Node node, int k) {
        if (node == null) {
            return;
        }
        if (k == 0) {
            System.out.print(node.data + " ");
            return;
        }
        printAllNodesAtKDistanceFromRootWithRecursionUtil(node.left, k - 1);
        printAllNodesAtKDistanceFromRootWithRecursionUtil(node.right, k - 1);
    }

    public void printAllNodesAtKDistanceFromRootWithoutRecursionWithStack(BinaryTree tree, int k) {
        printAllNodesAtKDistanceFromRootWithoutRecursionWithStackUtil(tree.root, k);
    }

    private void printAllNodesAtKDistanceFromRootWithoutRecursionWithStackUtil(Node root, int k) {
        Node curr = root;
        Stack<Node> st = new Stack<>();
        while (curr != null || st.size() > 1) {
            while (curr != null && st.size() < 3) {
                st.add(curr);
                curr = curr.left;
            }
            curr = st.peek();
            if (st.size() == k) {
                System.out.print(curr.data + " ");
            }
            curr = st.pop();
            while (curr != null && !st.isEmpty() && curr == st.peek().right) {
                curr = st.pop();
            }
            if (curr == root) {
                break;
            }
            curr = st.peek().right;
        }
    }

    public void printAllNodesAtKDistanceFromGivenNodeWithRecursion(BinaryTree tree, Node target, int k) {
        printAllNodesAtKDistanceFromGivenNodeWithRecursionUtil(tree.root, target, k);
    }

    private int printAllNodesAtKDistanceFromGivenNodeWithRecursionUtil(Node node, Node target, int k) {
        if (node == null) {
            return -1;
        }
        if (node == target) {
            printAllNodesAtKDistanceFromRootWithRecursionUtil(target, k);
            return 0;
        }
        int leftDis = printAllNodesAtKDistanceFromGivenNodeWithRecursionUtil(node.left, target, k);
        if (leftDis != -1) {
            if (k - 1 == leftDis) {
                System.out.print(node.data + " ");
            } else {
                printAllNodesAtKDistanceFromRootWithRecursionUtil(node.right, k - 2 - leftDis);
            }
            return 1 + leftDis;
        }

        int rightDis = printAllNodesAtKDistanceFromGivenNodeWithRecursionUtil(node.right, target, k);
        if (rightDis != -1) {
            if (k - 1 == rightDis) {
                System.out.print(node.data + " ");
            } else {
                printAllNodesAtKDistanceFromRootWithRecursionUtil(node.left, k - 2 - rightDis);
            }
            return 1 + rightDis;
        }
        return -1;
    }

    public void printNodesAtOddLevelsWithRecursion(BinaryTree tree) {
        printNodesAtOddLevelsWithRecursionUtil(tree.root, true);
    }

    private void printNodesAtOddLevelsWithRecursionUtil(Node node, boolean isOdd) {
        if (node == null) {
            return;
        }
        if (isOdd) {
            System.out.println(node.data);
        }
        printNodesAtOddLevelsWithRecursionUtil(node.left, !isOdd);
        printNodesAtOddLevelsWithRecursionUtil(node.right, !isOdd);
    }

    public void printNodesAtOddLevelsWithoutRecursionWithQueue(BinaryTree tree) {
        printNodesAtOddLevelsWithoutRecursionWithQueueUtil(tree.root);
    }

    private void printNodesAtOddLevelsWithoutRecursionWithQueueUtil(Node root) {
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        que.add(null);
        int level = 1;
        while (!que.isEmpty()) {
            Node popped = que.poll();
            if (popped == null) {
                if (que.peek() != null) {
                    que.add(null);
                }
                level++;
                continue;
            }
            if (level % 2 != 0) {
                System.out.println(popped.data);
            }
            if (popped.left != null) {
                que.add(popped.left);
            }
            if (popped.right != null) {
                que.add(popped.right);
            }
        }
    }

    public void printNodesBetweenTwoGivenLevelNumbersOfBinaryTree(BinaryTree tree, int lo, int hi) {
        printNodesBetweenTwoGivenLevelNumbersOfBinaryTreeUtil(tree.root, lo, hi);
    }

    private void printNodesBetweenTwoGivenLevelNumbersOfBinaryTreeUtil(Node node, int lo, int hi) {
        Queue<Node> que = new LinkedList<>();
        que.add(node);
        que.add(null);
        int level = 1;
        while (!que.isEmpty()) {
            Node popped = que.poll();
            if (popped == null) {
                level++;
                if (que.isEmpty() || level > hi) {
                    break;
                }
                que.add(null);
                continue;
            }
            if (level >= lo) {
                System.out.print(popped.data + " ");
            }
            if (popped.left != null) {
                que.add(popped.left);
            }
            if (popped.right != null) {
                que.add(popped.right);
            }
        }
    }

    public void printNumbersPresentAKthLevelOfFibonacciBinaryTree(int k) {
        printNumbersPresentAKthLevelOfFibonacciBinaryTreeUtil(k);
    }

    private void printNumbersPresentAKthLevelOfFibonacciBinaryTreeUtil(int k) {
        int[] arr = new int[1000000 + 1];
        populateFibonacciSeries(arr);

        int lo = (int) Math.pow(2, k - 1) - 1;
        int hi = (int) Math.pow(2, k) - 1 - 1;

        while (lo <= hi) {
            System.out.print(arr[lo] + " ");
            lo++;
        }
    }

    private void populateFibonacciSeries(int[] arr) {
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 2] + arr[i - 1];
        }
    }

    public void printPrimeNumbersPresentAKthLevelOfBinaryTree(int k) {
        printPrimeNumbersPresentAKthLevelOfBinaryTreeUtil(k);
    }

    private void printPrimeNumbersPresentAKthLevelOfBinaryTreeUtil(int k) {
        List<Integer> list = new ArrayList<>();
        populatePrimeNumberArray(list);
        int lo = (int) Math.pow(2, k - 1) - 1;
        int hi = (int) Math.pow(2, k) - 1 - 1;
        while (lo <= hi) {
            System.out.print(list.get(lo) + " ");
            lo++;
        }
    }

    private void populatePrimeNumberArray(List<Integer> list) {
        boolean[] arr = new boolean[1000000 + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = true;
        }

        for (int i = 2; i * i < arr.length; i++) {
            if (arr[i]) {
                for (int j = i * i; j < arr.length; j += i) {
                    arr[j] = false;
                }
            }
        }
        for (int i = 2; i < arr.length; i++) {
            if (arr[i]) {
                list.add(i);
            }
        }
    }

    public void printLevelOfNodeInBinaryTreeWithRecursion(BinaryTree tree, int data) {
        System.out.println(printLevelOfNodeInBinaryTreeWithRecursionUtil(tree.root, data, 1));
    }

    private int printLevelOfNodeInBinaryTreeWithRecursionUtil(Node node, int data, int level) {
        if (node == null) {
            return 0;
        }
        if (node.data == data) {
            return level;
        }
        int left = printLevelOfNodeInBinaryTreeWithRecursionUtil(node.left, data, level + 1);
        if (left != 0) {
            return left;
        }
        return printLevelOfNodeInBinaryTreeWithRecursionUtil(node.right, data, level + 1);
    }

    public void printLevelOfAllNodesInBinaryTreeWithoutRecursionWithQueue(BinaryTree tree) {
        printLevelOfAllNodesInBinaryTreeWithoutRecursionWithQueueUtil(tree.root);
    }

    private void printLevelOfAllNodesInBinaryTreeWithoutRecursionWithQueueUtil(Node root) {
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        que.add(null);

        int level = 1;
        while (!que.isEmpty()) {
            Node popped = que.poll();
            if (popped == null) {
                if (que.isEmpty()) {
                    break;
                }
                level++;
                que.add(null);
                continue;
            }
            System.out.println("Level of " + popped.data + " is " + level);
            if (popped.left != null) {
                que.add(popped.left);
            }
            if (popped.right != null) {
                que.add(popped.right);
            }
        }

    }
}