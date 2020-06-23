package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CheckingAndPrinting {
    Traversals t = new Traversals();

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

    public void checkIfSumOfCoveredNodesEqualsSumOfUncoveredNodesWithQueue(BinaryTree tree) {
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

    public void checkIfSumOfCoveredNodesEqualsSumOfUncoveredNodesWithoutQueue(BinaryTree tree) {
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
        int rootIndexInorder = in.indexOf(root); //left subtree size
        boolean isLeftSatisfied = checkIfInorderPreorderPostorderTraversalsAreOfSameTreeUtil(in.substring(0, rootIndexInorder), pre.substring(1, 1 + rootIndexInorder), post.substring(0, rootIndexInorder));
        boolean isRightSatisfied = checkIfInorderPreorderPostorderTraversalsAreOfSameTreeUtil(in.substring(rootIndexInorder + 1), pre.substring(1 + rootIndexInorder), post.substring(rootIndexInorder, post.length() - 1));
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
        //int[] arr = {10, 15, 14, 25, 30};
        //int[] arr = {30, 56, 22, 49, 30, 51, 2, 67};
        boolean isSatisfied = checkIfLevelOrderOfCompleteBinaryTreeIsMinHeapUtil(arr);
        System.out.println(isSatisfied);
    }

    private boolean checkIfLevelOrderOfCompleteBinaryTreeIsMinHeapUtil(int[] arr) {
        //Another approach to get rid of the break condition is to start from i = (n/2 - 1) to i = 0
        //https://www.geeksforgeeks.org/given-level-order-traversal-binary-tree-check-tree-min-heap/
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
            return checkIfBinaryTreeIsFullWithRecursionUtil(node.left) && checkIfBinaryTreeIsFullWithRecursionUtil(node.right);
        }
        return false;
    }

    public void checkIfBinaryTreeIsFullWithoutRecursion(BinaryTree tree) {
        boolean isSatisfied = checkIfBinaryTreeIsFullWithoutRecursionUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfBinaryTreeIsFullWithoutRecursionUtil(Node node) {
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
        //NON-FULL node: 1 child node: either only left OR only right
        //flag : Takes care of following conditions
        //1. Once a NON-FULL node is encountered, all subsequent nodes must be leaf nodes
        //2. If left child is null, right child should also be null
        while (!que.isEmpty()) {
            Node node = que.poll();
            if (node.left != null) {
                if (flag) {           //1.2. all subsequent nodes must be leaf nodes
                    return false;
                }
                que.add(node.left);
            } else {
                flag = true;        //2.1. If left child is null,
            }
            if (node.right != null) {
                if (flag) {           //2.2. right child should also be null
                    return false;
                }
                que.add(node.right);
            } else {
                flag = true;        //1.1. Once a NON-FULL node is encountered
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
        //Sample input
        /*Perfect
        BinaryTree tree = new BinaryTree(10);
        tree.root.left = new Node(20);
        tree.root.left.left = new Node(40);
        tree.root.left.right = new Node(50);
        tree.root.right = new Node(30);
        tree.root.right.left = new Node(60);
        tree.root.right.right = new Node(70);

        Not perfect
        BinaryTree tree = new BinaryTree(1);
        tree.root.left = new Node(2);
        tree.root.left.right = new Node(4);
        tree.root.right = new Node(3);
        tree.root.right.left = new Node(5);
        tree.root.right.right = new Node(6);*/

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
        return sum == node.data && checkIfBinaryTreeIsSumTreeUtil(node.left) && checkIfBinaryTreeIsSumTreeUtil(node.right);
    }

    public void checkIfBinaryTreeIsHeightBalanced(BinaryTree tree) {
        //GFG : https://www.geeksforgeeks.org/check-given-binary-tree-follows-height-property-red-black-tree/
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
        return (flag ? lh <= 2 * rh : rh <= 2 * lh) && checkIfBinaryTreeIsHeightBalancedUtil(node.left) && checkIfBinaryTreeIsHeightBalancedUtil(node.right);
    }

    static class INT {
        int d;

        INT() {
            d = 0;
        }

        @Override
        public String toString() {
            return "INT{" +
                    "d=" + d +
                    '}';
        }
    }

    public void checkIfBinaryTreeIsHeightBalancedInOofN(BinaryTree tree) {
        // NOT GFG : GENERIC PROBLEM STATEMENT : https://www.youtube.com/watch?v=LU4fGD-fgJQ
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

    public void checkIfBinaryTreeIsMirrorOfItself(BinaryTree tree){
        boolean isSatisfied = checkIfMirrorOfItselfUtil(tree.root);
        System.out.println(isSatisfied);
    }

    private boolean checkIfMirrorOfItselfUtil(Node root) {
        Queue<Node> que = new LinkedList<>();
        que.add(root.left);
        que.add(root.right);

        while(!que.isEmpty()){
            Node left = que.poll();
            Node right = que.poll();

            if(left == null && right == null){
                continue;
            }

            if((left == null && right != null) || (left != null && right == null) || (left.data != right.data)){
                return false;
            }
            que.add(left.left);
            que.add(right.right);
            que.add(left.right);
            que.add(right.left);
        }
        return true;
    }
}
