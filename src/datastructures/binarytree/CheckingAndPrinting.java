package datastructures.binarytree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import datastructures.binarytree.BinaryTree.Node;

public class CheckingAndPrinting {
    Traversals t = new Traversals();

    public void checkForChildrenSumProperty(final BinaryTree tree) {
        final boolean isSatisfied = checkForChildrenSumPropertyUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkForChildrenSumPropertyUtil(final Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return true;
        }
        final boolean isLeftSatisfied = checkForChildrenSumPropertyUtil(node.left);
        final boolean isRightSatisfied = checkForChildrenSumPropertyUtil(node.right);

        int sum = 0;
        if (node.left != null) {
            sum += node.left.data;
        }
        if (node.right != null) {
            sum += node.right.data;
        }
        return isLeftSatisfied && isRightSatisfied && (sum == node.data);
    }

    public void checkIfSumOfCoveredNodesEqualsSumOfUncoveredNodesWithQueue(final BinaryTree tree) {
        final Queue<Node> nodes = new LinkedList<>();

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

    private void extractRightBoundaryIntoQueue(final Node node, final Queue<Node> nodes) {
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

    private void extractLeftBoundaryIntoQueue(final Node node, final Queue<Node> nodes) {
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

    private int sumOfNodes(final Node node) {
        if (node == null) {
            return 0;
        }
        return sumOfNodes(node.left) + sumOfNodes(node.right) + node.data;
    }

    public void checkIfSumOfCoveredNodesEqualsSumOfUncoveredNodesWithoutQueue(final BinaryTree tree) {
        final Node root = tree.root;
        final int sumUncovered = findUncoveredSum(root);
        final int sumTotalTree = sumOfNodes(root);
        final boolean isSatisfied = sumTotalTree - sumUncovered == sumUncovered;
        System.out.println(isSatisfied);
    }

    private int findUncoveredSum(final Node node) {
        final int lbs = findUncoveredSumLeftBoundary(node.left);
        final int rbs = findUncoveredSumRightBoundary(node.right);
        return lbs + rbs + node.data;
    }

    private int findUncoveredSumLeftBoundary(final Node node) {
        if (node.left == null && node.right == null) {
            return node.data;
        }
        if (node.left != null) {
            return node.data + findUncoveredSumLeftBoundary(node.left);
        } else {
            return node.data + findUncoveredSumLeftBoundary(node.right);
        }
    }

    private int findUncoveredSumRightBoundary(final Node node) {
        if (node.left == null && node.right == null) {
            return node.data;
        }
        if (node.right != null) {
            return node.data + findUncoveredSumLeftBoundary(node.right);
        } else {
            return node.data + findUncoveredSumLeftBoundary(node.left);
        }
    }

    public void checkIfInorderPreorderPostorderTraversalsAreOfSameTree(final String in, final String pre,
            final String post) {
        final boolean isSatisfied = checkIfInorderPreorderPostorderTraversalsAreOfSameTreeUtil(in, pre, post);
        System.out.println(isSatisfied);
    }

    private boolean checkIfInorderPreorderPostorderTraversalsAreOfSameTreeUtil(final String in, final String pre,
            final String post) {
        if (in.length() == 1 && pre.length() == 1 && post.length() == 1) {
            return in.charAt(0) == pre.charAt(0) && pre.charAt(0) == post.charAt(0);
        }
        final int root = pre.charAt(0);
        final int rootIndexInorder = in.indexOf(root); // left subtree size
        final boolean isLeftSatisfied = checkIfInorderPreorderPostorderTraversalsAreOfSameTreeUtil(
                in.substring(0, rootIndexInorder), pre.substring(1, 1 + rootIndexInorder),
                post.substring(0, rootIndexInorder));
        final boolean isRightSatisfied = checkIfInorderPreorderPostorderTraversalsAreOfSameTreeUtil(
                in.substring(rootIndexInorder + 1), pre.substring(1 + rootIndexInorder),
                post.substring(rootIndexInorder, post.length() - 1));
        return isLeftSatisfied && isRightSatisfied;
    }

    public void checkIfLeafTraversalOfTwoBinaryTreesIsSame(final BinaryTree tree1, final BinaryTree tree2) {
        final boolean isSatisfied = checkIfLeafTraversalOfTwoBinaryTreesIsSameUtil(tree1.root, tree2.root);
        System.out.println(isSatisfied);
    }

    private static boolean checkIfLeafTraversalOfTwoBinaryTreesIsSameUtil(final Node root1, final Node root2) {
        final Stack<Node> st1 = new Stack<>();
        final Stack<Node> st2 = new Stack<>();
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

    public void checkIfLevelOrderOfCompleteBinaryTreeIsMinHeap(final int[] arr) {
        // int[] arr = {10, 15, 14, 25, 30};
        // int[] arr = {30, 56, 22, 49, 30, 51, 2, 67};
        final boolean isSatisfied = checkIfLevelOrderOfCompleteBinaryTreeIsMinHeapUtil(arr);
        System.out.println(isSatisfied);
    }

    private boolean checkIfLevelOrderOfCompleteBinaryTreeIsMinHeapUtil(final int[] arr) {
        // Another approach to get rid of the break condition is to start from i = (n/2
        // - 1) to i = 0
        // https://www.geeksforgeeks.org/given-level-order-traversal-binary-tree-check-tree-min-heap/
        for (int i = 0; i < arr.length; i++) {
            final int li = 2 * i + 1;
            final int ri = 2 * i + 2;
            if (li > arr.length - 1) {
                break;
            }
            if (arr[i] > arr[li] || arr[i] > arr[ri]) {
                return false;
            }
        }
        return true;
    }

    public void checkIfBinaryTreeIsFullWithRecursion(final BinaryTree tree) {
        final boolean isSatisfied = checkIfBinaryTreeIsFullWithRecursionUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsFullWithRecursionUtil(final Node node) {
        if (node == null || node.isLeaf()) {
            return true;
        }
        if (node.left != null && node.right != null) {
            return checkIfBinaryTreeIsFullWithRecursionUtil(node.left)
                    && checkIfBinaryTreeIsFullWithRecursionUtil(node.right);
        }
        return false;
    }

    public void checkIfBinaryTreeIsFullWithoutRecursion(final BinaryTree tree) {
        final boolean isSatisfied = checkIfBinaryTreeIsFullWithoutRecursionUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsFullWithoutRecursionUtil(final Node node) {
        final Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            final Node n = q.poll();
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

    public void checkIfBinaryTreeIsComplete(final BinaryTree tree) {
        final boolean isSatisfied = checkIfBinaryTreeIsCompleteUtil2(tree);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsCompleteUtil(final BinaryTree tree) {
        final Queue<Node> que = new LinkedList<>();
        que.add(tree.root);
        boolean flag = false;
        while (!que.isEmpty()) {
            final Node node = que.poll();
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

    private boolean checkIfBinaryTreeIsCompleteUtil2(final BinaryTree tree) {
        final Queue<Node> que = new LinkedList<>();
        que.add(tree.root);
        boolean flag = false;
        // NON-FULL node: 1 child node: either only left OR only right
        // flag : Takes care of following conditions
        // 1. Once a NON-FULL node is encountered, all subsequent nodes must be leaf
        // nodes
        // 2. If left child is null, right child should also be null
        while (!que.isEmpty()) {
            final Node node = que.poll();
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

    private int findHeight(final Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(1 + findHeight(node.left), 1 + findHeight(node.right));
    }

    public void checkIfBinaryTreeIsPerfect(final BinaryTree tree) {
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

        final Node root = tree.root;
        final int height = findHeight(root);
        final boolean isSatisfied = checkIfBinaryTreeIsPerfectUtil(root, height);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsPerfectUtil(final Node node, final int height) {
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

    public void checkIfBinaryTreeIsSumTree(final BinaryTree tree) {
        final boolean isSatisfied = checkIfBinaryTreeIsSumTreeUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsSumTreeUtil(final Node node) {
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

    public void checkIfBinaryTreeIsHeightBalanced(final BinaryTree tree) {
        // GFG :
        // https://www.geeksforgeeks.org/check-given-binary-tree-follows-height-property-red-black-tree/
        final boolean isSatisfied = checkIfBinaryTreeIsHeightBalancedUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsHeightBalancedUtil(final Node node) {
        if (node == null || node.isLeaf()) {
            return true;
        }
        final int lh = findHeight(node.left) + 1;
        final int rh = findHeight(node.right) + 1;
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

    public void checkIfBinaryTreeIsHeightBalancedInOofN(final BinaryTree tree) {
        // NOT GFG : GENERIC PROBLEM STATEMENT :
        // https://www.youtube.com/watch?v=LU4fGD-fgJQ
        final INT i = new INT();
        final boolean isSatisfied = checkIfBinaryTreeIsHeightBalancedInOofNUtil(tree.root, i);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsHeightBalancedInOofNUtil(final Node node, final INT par) {
        if (node == null) {
            par.d = -1;
            return true;
        }

        final INT left = new INT();
        final INT right = new INT();
        final boolean leftSatisfied = checkIfBinaryTreeIsHeightBalancedInOofNUtil(node.left, left);
        final boolean rightSatisfied = checkIfBinaryTreeIsHeightBalancedInOofNUtil(node.right, right);
        final int diff = Math.abs(left.d - right.d);
        par.d = Math.max(left.d, right.d) + 1;
        return leftSatisfied && rightSatisfied && diff <= 1;
    }

    public void checkIfBinaryTreeIsMirrorOfItself(final BinaryTree tree) {
        final boolean isSatisfied = checkIfMirrorOfItselfUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfMirrorOfItselfUtil(final Node root) {
        final Queue<Node> que = new LinkedList<>();
        que.add(root.left);
        que.add(root.right);

        while (!que.isEmpty()) {
            final Node left = que.poll();
            final Node right = que.poll();

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

    public void checkIfTwoBinaryTreesAreMirrorOfEachOtherWithRecursion(final BinaryTree tree1, final BinaryTree tree2) {
        final boolean isSatisfied = checkIfTwoBinaryTreesAreMirrorOfEachOtherWithRecursionUtil(tree1.root, tree2.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfTwoBinaryTreesAreMirrorOfEachOtherWithRecursionUtil(final Node node1, final Node node2) {
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

    public void checkIfTwoBinaryTreesAreMirrorOfEachOtherWithoutRecursion(final BinaryTree tree1,
            final BinaryTree tree2) {
        // Use iterative inorder traversal

        final boolean isSatisfied = checkIfTwoBinaryTreesAreMirrorOfEachOtherWithoutRecursionUtil(tree1.root,
                tree2.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfTwoBinaryTreesAreMirrorOfEachOtherWithoutRecursionUtil(final Node root1, final Node root2) {
        Node curr1 = root1, curr2 = root2;
        final Stack<Node> st1 = new Stack<>(), st2 = new Stack<>();
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

    public void checkIfTwoBinaryTreesAreIdenticalWithRecursion(final BinaryTree tree1, final BinaryTree tree2) {
        final boolean isSatisfied = checkIfTwoBinaryTreesAreIdenticalWithRecursionUtil(tree1.root, tree2.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfTwoBinaryTreesAreIdenticalWithRecursionUtil(final Node node1, final Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (!(node1 != null && node2 != null)) {
            return false;
        }
        return node1.data == node2.data && checkIfTwoBinaryTreesAreIdenticalWithRecursionUtil(node1.left, node2.left)
                && checkIfTwoBinaryTreesAreIdenticalWithRecursionUtil(node1.right, node2.right);
    }

    public void checkIfTwoBinaryTreesAreIdenticalWithoutRecursionUsingTwoStacks(final BinaryTree tree1,
            final BinaryTree tree2) {
        // Use iterative inorder traversal

        final boolean isSatisfied = checkIfTwoBinaryTreesAreIdenticalWithoutRecursionUsingTwoStacksUtil(tree1.root,
                tree2.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfTwoBinaryTreesAreIdenticalWithoutRecursionUsingTwoStacksUtil(final Node root1,
            final Node root2) {
        Node curr1 = root1, curr2 = root2;
        final Stack<Node> st1 = new Stack<>(), st2 = new Stack<>();
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

    public void checkIfTwoBinaryTreesAreIdenticalWithoutRecursionUsingQueue(final BinaryTree tree1,
            final BinaryTree tree2) {
        // Use iterative inorder traversal

        final boolean isSatisfied = checkIfTwoBinaryTreesAreIdenticalWithoutRecursionUsingQueueUtil(tree1.root,
                tree2.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfTwoBinaryTreesAreIdenticalWithoutRecursionUsingQueueUtil(final Node root1,
            final Node root2) {
        Node curr1 = root1, curr2 = root2;
        final Queue<Node> que = new LinkedList<>();
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

    public void checkIfOneTreeIsSubtreeOfAnotherTreeWithRecursion(final BinaryTree parent, final BinaryTree child) {
        final boolean isSatisfied = checkIfOneTreeIsSubtreeOfAnotherTreeWithRecursionUtil(parent.root, child.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfOneTreeIsSubtreeOfAnotherTreeWithRecursionUtil(final Node parent, final Node child) {
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

    private boolean isIdentical(final Node n1, final Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (!(n1 != null && n2 != null)) {
            return false;
        }
        return n1.data == n2.data && isIdentical(n1.left, n2.left) && isIdentical(n1.right, n2.right);
    }

    public void checkIfOneTreeIsSubtreeOfAnotherTreeWithoutRecursion(BinaryTree parent, BinaryTree child) {
        boolean isSatisfied = checkIfOneTreeIsSubtreeOfAnotherTreeWithoutRecursionUtil(parent.root, child.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfOneTreeIsSubtreeOfAnotherTreeWithoutRecursionUtil(Node parent, Node child) {
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
}