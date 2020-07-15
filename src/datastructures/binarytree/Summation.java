package datastructures.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import datastructures.binarytree.BinaryTree.Node;

public class Summation {
    TraversalAndView t = new TraversalAndView();

    static class INTEGER {
        int val;
    }

    public void findRootOfTheTreeWhereChildrenIdSumForEveryNodeIsGiven(int[][] mat) {
        int res = 0;
        for (int i = 0; i < mat.length; i++) {
            res += mat[i][0] - mat[i][1];
        }
        System.out.println(res);
    }

    public void findSumOfAllParentNodesHavingGivenChildNode(BinaryTree tree, int x) {
        INTEGER sum = new INTEGER();
        findSumOfAllParentNodesHavingGivenChildNodeUtil(tree.root, x, sum);
        System.out.println(sum.val);
    }

    private void findSumOfAllParentNodesHavingGivenChildNodeUtil(Node node, int x, INTEGER sum) {
        if (node == null || node.isLeaf()) {
            return;
        }

        if ((node.left != null && node.left.data == x) || (node.right != null && node.right.data == x)) {
            sum.val += node.data;
        }
        findSumOfAllParentNodesHavingGivenChildNodeUtil(node.left, x, sum);
        findSumOfAllParentNodesHavingGivenChildNodeUtil(node.right, x, sum);
    }

    public void mergeTwoBinaryTreesByDoingNodeSumWithRecursion(BinaryTree tree1, BinaryTree tree2) {
        Node res = mergeTwoBinaryTreesByDoingNodeSumWithRecursionUtil(tree1.root, tree2.root);
        t.levelOrderTraversalWithRecursion(new BinaryTree(res));
    }

    private Node mergeTwoBinaryTreesByDoingNodeSumWithRecursionUtil(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return node1 != null ? node1 : node2;
        }
        // To save space, merged value can be put into node1 and the same can be
        // returned
        Node res = new Node(node1.data + node2.data);
        res.left = mergeTwoBinaryTreesByDoingNodeSumWithRecursionUtil(node1.left, node2.left);
        res.right = mergeTwoBinaryTreesByDoingNodeSumWithRecursionUtil(node1.right, node2.right);
        return res;
    }

    static class NODEPAIR {
        NODEPAIR(Node n1, Node n2) {
            this.node1 = n1;
            this.node2 = n2;
        }

        Node node1;
        Node node2;
    }

    public void mergeTwoBinaryTreesByDoingNodeSumWithoutRecursion(BinaryTree tree1, BinaryTree tree2) {
        if (tree1.root == null) {
            tree1.root = tree2.root;
        } else {
            NODEPAIR node = new NODEPAIR(tree1.root, tree2.root);
            Stack<NODEPAIR> st = new Stack<>();
            st.add(node);
            while (!st.isEmpty()) {
                node = st.pop();
                Node n1 = node.node1;
                Node n2 = node.node2;
                if (n2 == null) { // Only n2 can be null
                    continue;
                }
                node.node1.data += node.node2.data;
                if (n1.left == null && n2.left != null) {
                    n1.left = n2.left;
                    st.push(new NODEPAIR(n1.left, null));
                } else if (n1.left != null) { // Doesnt matter if n2.left is null or non null
                    st.push(new NODEPAIR(n1.left, n2.left));
                }
                if (n1.right == null && n2.right != null) {
                    n1.right = n2.right;
                    st.push(new NODEPAIR(n1.right, null));
                } else if (n1.right != null) { // Doesnt matter if n2.right is null or non null
                    st.push(new NODEPAIR(n1.right, n2.right));
                }
            }
        }
        t.levelOrderTraversalWithRecursion(new BinaryTree(tree1.root));
    }

    public void replaceEachNodeWithSumOfItsInorderPredecessorAndSuccessor(BinaryTree tree) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        t.levelOrderTraversalWithRecursion(tree);
        System.out.println();
        replaceEachNodeWithSumOfItsInorderPredecessorAndSuccessorUtil1(tree.root, list);
        list.add(0);
        INTEGER index = new INTEGER();
        index.val = 1;
        replaceEachNodeWithSumOfItsInorderPredecessorAndSuccessorUtil2(tree.root, list, index);
        t.levelOrderTraversalWithRecursion(tree);
    }

    private void replaceEachNodeWithSumOfItsInorderPredecessorAndSuccessorUtil1(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        replaceEachNodeWithSumOfItsInorderPredecessorAndSuccessorUtil1(node.left, list);
        list.add(node.data);
        replaceEachNodeWithSumOfItsInorderPredecessorAndSuccessorUtil1(node.right, list);
    }

    private void replaceEachNodeWithSumOfItsInorderPredecessorAndSuccessorUtil2(Node node, List<Integer> list,
            INTEGER index) {
        if (node == null) {
            return;
        }
        replaceEachNodeWithSumOfItsInorderPredecessorAndSuccessorUtil2(node.left, list, index);
        node.data = list.get(index.val - 1) + list.get(index.val + 1);
        index.val++;
        replaceEachNodeWithSumOfItsInorderPredecessorAndSuccessorUtil2(node.right, list, index);
    }

    public void printAllPathsFromRootWithSpecifiedSum(BinaryTree tree, int target) {
        // Sample Input
        /*
         * BinaryTree tree = new BinaryTree(new Node(10)); tree.root.left = new
         * Node(28); tree.root.left.left = new Node(1); tree.root.left.left.right = new
         * Node(-1); tree.root.right = new Node(13); tree.root.right.left = new
         * Node(14); tree.root.right.left.left = new Node(21);
         * tree.root.right.left.right = new Node(22); tree.root.right.right = new
         * Node(15); tree.root.right.right.left = new Node(23);
         * tree.root.right.right.right = new Node(24);
         */

        List<Integer> list = new ArrayList<>();
        printAllPathsFromRootWithSpecifiedSumUtil(tree.root, 0, target, list);
    }

    private void printAllPathsFromRootWithSpecifiedSumUtil(Node node, int sum, int target, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.data);
        sum += node.data;
        if (sum == target) {
            System.out.println(list);
        }
        printAllPathsFromRootWithSpecifiedSumUtil(node.left, sum, target, list);
        printAllPathsFromRootWithSpecifiedSumUtil(node.right, sum, target, list);
        list.remove(list.size() - 1);
    }

    public void printAllKSumPaths(BinaryTree tree, int k) {
        // Sample Input
        /*
         * BinaryTree tree = new BinaryTree(new Node(1)); tree.root.left = new Node(3);
         * tree.root.left.left = new Node(2); tree.root.left.right = new Node(1);
         * tree.root.left.right.left = new Node(1); tree.root.right = new Node(-1);
         * tree.root.right.left = new Node(4); tree.root.right.left.left = new Node(1);
         * tree.root.right.left.right = new Node(2); tree.root.right.right = new
         * Node(5); tree.root.right.right.right = new Node(6);
         */

        List<Integer> list = new ArrayList<>();
        printAllKSumPathsUtil(tree.root, k, list);
    }

    private void printAllKSumPathsUtil(Node node, int k, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.data);
        printAllKSumPathsUtil(node.left, k, list);
        printAllKSumPathsUtil(node.right, k, list);

        int sum = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            sum += list.get(i);
            if (sum == k) {
                printKSumPath(i, list);
            }
        }
        list.remove(list.size() - 1);
    }

    private void printKSumPath(int index, List<Integer> list) {
        for (int i = index; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    public void removeAllNodesWhichDontLieInAnyPathWithSumGreaterThanEqualToK(BinaryTree tree, int k) {
        // Sample Input
        /*
         * BinaryTree tree = new BinaryTree(new Node(1)); tree.root.left = new Node(2);
         * tree.root.left.left = new Node(4); tree.root.left.left.left = new Node(8);
         * tree.root.left.left.right = new Node(9); tree.root.left.left.right.left = new
         * Node(13); tree.root.left.left.right.right = new Node(14);
         * tree.root.left.left.right.right.left = new Node(15); tree.root.left.right =
         * new Node(5); tree.root.left.right.left = new Node(12); tree.root.right = new
         * Node(3); tree.root.right.left = new Node(6); tree.root.right.right = new
         * Node(7); tree.root.right.right.left = new Node(10);
         * tree.root.right.right.left.right = new Node(11);
         */

        removeAllNodesWhichDontLieInAnyPathWithSumGreaterThanEqualToKUtil(tree.root, k);
        t.levelOrderTraversalWithRecursion(tree);
    }

    private boolean removeAllNodesWhichDontLieInAnyPathWithSumGreaterThanEqualToKUtil(Node node, int k) {
        if (node == null) {
            return false;
        }
        if (node.data >= k) {
            return true;
        }

        boolean left = removeAllNodesWhichDontLieInAnyPathWithSumGreaterThanEqualToKUtil(node.left, k - node.data);
        if (!left) {
            node.left = null;
        }
        boolean right = removeAllNodesWhichDontLieInAnyPathWithSumGreaterThanEqualToKUtil(node.right, k - node.data);
        if (!right) {
            node.right = null;
        }
        return left || right;
    }

    public void findMaximumLevelSum(BinaryTree tree) {
        Queue<Node> que = new LinkedList<>();
        que.add(tree.root);

        int maxSum = Integer.MIN_VALUE;
        while (!que.isEmpty()) {
            int size = que.size();
            int levelSum = 0;
            while (size > 0) {
                Node popped = que.poll();
                levelSum += popped.data;
                size--;
                if (popped.left != null) {
                    que.add(popped.left);
                }
                if (popped.right != null) {
                    que.add(popped.right);
                }

            }
            maxSum = Math.max(levelSum, maxSum);
        }
        System.out.println(maxSum);
    }

    public void findDifferenceBetweenSumsOfOddLevelAndEvenLevelNodes(BinaryTree tree) {
        Queue<Node> que = new LinkedList<>();
        int oddSum = 0;
        int evenSum = 0;
        boolean oddFlag = true;
        que.add(tree.root);
        while (!que.isEmpty()) {
            int size = que.size();
            while (size > 0) {
                Node popped = que.poll();
                if (oddFlag) {
                    oddSum += popped.data;
                } else {
                    evenSum += popped.data;
                }
                if (popped.left != null) {
                    que.add(popped.left);
                }
                if (popped.right != null) {
                    que.add(popped.right);
                }
                size--;
            }
            oddFlag = !oddFlag;
        }
        System.out.println(oddSum - evenSum);
    }

    public void findSumOfNodesAtKthLevelInTreeRepresentedAsString(String str, int k) {
        INTEGER sum = new INTEGER();
        findSumOfNodesAtKthLevelInTreeRepresentedAsStringUtil(str, 0, k, sum);
    }

    private void findSumOfNodesAtKthLevelInTreeRepresentedAsStringUtil(String str, int level, int k, INTEGER sum)
            throws NumberFormatException {
        if (str == "()") {
            return;
        }
        int node = Character.getNumericValue(str.charAt(1));
        if (level == k) {
            sum.val += node;
        }
        int rightSTI = findRightSTI(str.substring(2));
        String leftST = str.substring(2, rightSTI);
        String rightST = str.substring(rightSTI);
        findSumOfNodesAtKthLevelInTreeRepresentedAsStringUtil(leftST, level + 1, k, sum);
        findSumOfNodesAtKthLevelInTreeRepresentedAsStringUtil(rightST, level + 1, k, sum);
    }

    private int findRightSTI(String str) {
        int index = 0;
        Stack<Character> st = new Stack<>();
        st.push(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ')' && st.peek() == '(') {
                st.pop();
            } else if (str.charAt(i) == '(') {
                st.push(str.charAt(i));
            }
            if (st.isEmpty()) {
                index = i + 1;
                break;
            }
        }
        return index;
    }
}