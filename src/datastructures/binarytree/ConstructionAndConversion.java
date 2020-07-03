package datastructures.binarytree;

import datastructures.binarytree.BinaryTree.Node;

import java.util.*;

public class ConstructionAndConversion {
    static class ListNode {
        int data;
        ListNode next;

        ListNode(int d) {
            data = d;
            next = null;
        }
    }

    TraversalAndView t = new TraversalAndView();

    public void constructBinaryTreeFromInOrderAndPreOrder(String in, String pre) {
        BinaryTree tree = new BinaryTree(constructBinaryTreeFromInOrderAndPreOrderUtil(in, pre));
        t.printInOrder(tree);
    }

    private Node constructBinaryTreeFromInOrderAndPreOrderUtil(String in, String pre) {
        if (pre.isEmpty()) {
            return null;
        }
        char preFirstChar = pre.charAt(0);
        int inorderIndexOfPreFirstChar = in.indexOf(preFirstChar);
        Node node = new Node(Character.getNumericValue(preFirstChar));
        node.left = constructBinaryTreeFromInOrderAndPreOrderUtil(in.substring(0, inorderIndexOfPreFirstChar), pre.substring(1, 1 + inorderIndexOfPreFirstChar));
        node.right = constructBinaryTreeFromInOrderAndPreOrderUtil(in.substring(1 + inorderIndexOfPreFirstChar), pre.substring(1 + inorderIndexOfPreFirstChar));
        return node;
    }

    public void constructBinaryTreeFromInOrderAndPostOrder(String in, String post) {
        BinaryTree tree = new BinaryTree(constructBinaryTreeFromInOrderAndPostOrderUtil(in, post));
        t.printInOrder(tree);
    }

    private Node constructBinaryTreeFromInOrderAndPostOrderUtil(String in, String post) {
        if (in.isEmpty() && post.isEmpty()) {
            return null;
        }
        int postLastIndex = post.length() - 1;
        char postLastChar = post.charAt(postLastIndex);
        int inorderIndexOfPostLastChar = in.indexOf(postLastChar);
        Node node = new Node(Character.getNumericValue(post.charAt(post.length() - 1)));
        node.left = constructBinaryTreeFromInOrderAndPostOrderUtil(in.substring(0, inorderIndexOfPostLastChar), post.substring(0, inorderIndexOfPostLastChar));
        node.right = constructBinaryTreeFromInOrderAndPostOrderUtil(in.substring(1 + inorderIndexOfPostLastChar), post.substring(inorderIndexOfPostLastChar, post.length() - 1));
        return node;
    }

    public void constructBinaryTreeFromInOrderAndLevelOrder(int[] in, int[] level) {
        BinaryTree tree = new BinaryTree(constructBinaryTreeFromInOrderAndLevelOrderUtil(in, level));
        t.printInOrder(tree);
    }

    private Node constructBinaryTreeFromInOrderAndLevelOrderUtil(int[] in, int[] level) {
        if (in.length == 0) {
            return null;
        }
        if (in.length == 1) {
            return new Node(in[0]);
        }
        int inorderIndexOfFirstFoundLevelChar = -1;
        int i = 0;
        while (inorderIndexOfFirstFoundLevelChar == -1 && i < level.length) {
            inorderIndexOfFirstFoundLevelChar = findIndexInIntArray(in, level[i]);
            i++;
        }
        Node node = new Node(in[inorderIndexOfFirstFoundLevelChar]);
        node.left = constructBinaryTreeFromInOrderAndLevelOrderUtil(Arrays.copyOfRange(in, 0, inorderIndexOfFirstFoundLevelChar), level);
        node.right = constructBinaryTreeFromInOrderAndLevelOrderUtil(Arrays.copyOfRange(in, 1 + inorderIndexOfFirstFoundLevelChar, in.length), level);
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

    public void constructSpecialTreeFromPreOrder(int[] pre, char[] ln) {
        //Special Tree where each node has either 0 or 2 nodes

        //SAMPLE INPUT
        /*int[] pre = {1, 2, 4, 5, 6, 7, 12, 13, 3, 8, 14, 9, 11, 15, 10};
        char[] ln = {'N', 'N', 'L', 'N', 'N', 'L', 'L', 'L', 'N', 'N', 'L', 'N', 'L', 'L', 'L'};*/

        //ln = L:LEAF, N=NON LEAF node
        BinaryTree tree = new BinaryTree(constructSpecialTreeFromPreOrderUtil(pre, ln));
        t.printPreOrder(tree);
    }

    private Node constructSpecialTreeFromPreOrderUtil(int[] pre, char[] ln) {
        if (ln[0] == 'L') {
            return new Node(pre[0]);
        }
        Node node = new Node(pre[0]);
        int n = calculateLeftSubtreeLength(Arrays.copyOfRange(ln, 1, ln.length));
        node.left = constructSpecialTreeFromPreOrderUtil(Arrays.copyOfRange(pre, 1, pre.length), Arrays.copyOfRange(ln, 1, ln.length));
        node.right = constructSpecialTreeFromPreOrderUtil(Arrays.copyOfRange(pre, 1 + n, pre.length), Arrays.copyOfRange(ln, 1 + n, ln.length));
        return node;
    }

    private int calculateLeftSubtreeLength(char[] ln) {
        if (ln[0] == 'L') {
            return 1;
        } else {
            int nCount = 1;
            int lCount = 0;
            int i = 1;
            for (; (lCount != nCount + 1) && (i < ln.length); i++) {
                if (ln[i] == 'N') {
                    nCount++;
                } else if (ln[i] == 'L') {
                    lCount++;
                }
            }
            return i;
        }
    }

    public void constructSpecialTreeFromInOrder(int[] in) {
        //Special Binary Tree: Each node is bigger than either of its children

        BinaryTree tree = new BinaryTree(constructSpecialTreeFromInOrderUtil(in));
        t.printInOrder(tree);
    }

    private Node constructSpecialTreeFromInOrderUtil(int[] in) {
        if (in.length == 0) {
            return null;
        }
        int ind = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == Math.max(max, in[i])) {
                ind = i;
                max = Math.max(max, in[i]);
            }
        }
        Node node = new Node(in[ind]);
        node.left = constructSpecialTreeFromInOrderUtil(Arrays.copyOfRange(in, 0, ind));
        node.right = constructSpecialTreeFromInOrderUtil(Arrays.copyOfRange(in, 1 + ind, in.length));
        return node;
    }

    public void constructFullBinaryTreeFromPreOrderAndPostOrder(int[] pre, int[] post) {
        //FBT : Either 0 OR 2 children
        //Sample Input
        /*int[] pre = {1, 2, 4, 8, 9, 5, 3, 6, 7};
        int[] post = {8, 9, 4, 5, 2, 6, 7, 3, 1};*/

        BinaryTree tree = new BinaryTree(constructFullBinaryTreeFromPreOrderAndPostOrderUtil(pre, post));
        t.printPreOrder(tree);
    }

    private Node constructFullBinaryTreeFromPreOrderAndPostOrderUtil(int[] pre, int[] post) {
        if (pre.length == post.length && post.length == 1) {
            return new Node(pre[0]);
        }
        /*if (pre.length == post.length && post.length == 3) {
            return new Node(post[0]);
        }*/
        int len = pre.length;
        Node node = new Node(post[len - 1]);
        int preIndexRightChild = findIndex(pre, post[len - 2]);
        int rightSubtreeLen = pre.length - preIndexRightChild;
        node.left = constructFullBinaryTreeFromPreOrderAndPostOrderUtil(Arrays.copyOfRange(pre, 1, preIndexRightChild), Arrays.copyOfRange(post, 0, len - 1 - rightSubtreeLen));
        node.right = constructFullBinaryTreeFromPreOrderAndPostOrderUtil(Arrays.copyOfRange(pre, preIndexRightChild, len), Arrays.copyOfRange(post, len - 1 - rightSubtreeLen, len - 1));
        return node;
    }

    public void constructFullBinaryTreeFromPreOrderAndMirrorPreOrder(int[] pre, int[] preme) {
        BinaryTree tree = new BinaryTree(constructFullBinaryTreeFromPreOrderAndMirrorPreOrderUtil(pre, preme, pre.length));
        t.printPreOrder(tree);
    }

    private Node constructFullBinaryTreeFromPreOrderAndMirrorPreOrderUtil(int[] pre, int[] preme, int len) {
        if (pre.length == 1) {
            return new Node(pre[0]);
        }
        int nodeData = pre[0];
        Node node = new Node(nodeData);
        int rightChild = preme[1];
        int preIndexRightChild = findIndex(pre, rightChild);
        int rightSubtreeLen = pre.length - preIndexRightChild;
        node.left = constructFullBinaryTreeFromPreOrderAndMirrorPreOrderUtil(Arrays.copyOfRange(pre, 1, preIndexRightChild), Arrays.copyOfRange(preme, rightSubtreeLen + 1, len), preIndexRightChild - 1);
        node.right = constructFullBinaryTreeFromPreOrderAndMirrorPreOrderUtil(Arrays.copyOfRange(pre, preIndexRightChild, len), Arrays.copyOfRange(preme, 1, rightSubtreeLen + 1), rightSubtreeLen);
        return node;
    }

    public void constructCompleteBinaryTreeFromLevelOrderArray(int[] arr) {
        //SAMPLE INPUT
        /*int[] arr = {1, 2, 3, 4, 5, 6, 6, 6, 6, 6};*/
        BinaryTree tree = new BinaryTree(constructCompleteBinaryTreeFromLevelOrderArrayUtil(arr, 0));
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    private Node constructCompleteBinaryTreeFromLevelOrderArrayUtil(int[] arr, int index) {
        if (index > arr.length - 1) {
            return null;
        }
        Node node = new Node(arr[index]);
        node.left = constructCompleteBinaryTreeFromLevelOrderArrayUtil(arr, (2 * index) + 1);
        node.right = constructCompleteBinaryTreeFromLevelOrderArrayUtil(arr, (2 * index) + 2);
        return node;
    }

    private int findIndex(int[] pre, int x) {
        for (int i = 0; i < pre.length; i++) {
            if (pre[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public void constructCompleteBinaryTreeFromLinkedList(ListNode list) {
        //Sample Input
        /*ListNode list = new ListNode(10);
        list.next = new ListNode(12);
        list.next.next = new ListNode(15);
        list.next.next.next = new ListNode(25);
        list.next.next.next.next = new ListNode(30);
        list.next.next.next.next.next = new ListNode(36);*/

        BinaryTree tree = new BinaryTree(constructCompleteBinaryTreeFromLinkedListUtil(list));
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    private Node constructCompleteBinaryTreeFromLinkedListUtil(ListNode node) {
        ListNode curr = node;
        Queue<Node> que = new LinkedList<>();

        Node root = new Node(node.data);
        que.add(root);
        curr = curr.next;
        while (curr != null && !que.isEmpty()) {
            Node nod = que.poll();
            Node left = new Node(curr.data);
            que.add(left);
            curr = curr.next;
            Node right = null;
            if (curr != null) {
                right = new Node(curr.data);
                curr = curr.next;
                que.add(right);
            }
            nod.left = left;
            nod.right = right;
        }
        return root;
    }

    static Node head = null;
    static Node prev = null;

    public void convertBinaryTreeToInOrderCircularDoublyLinkedListWithRecursionAndStaticField(Node node) {
        convertBinaryTreeToInOrderCircularDoublyLinkedListWithRecursionAndStaticFieldUtil(node);
        head.left = prev;
        prev.right = head;
        Node curr = head;
        do {
            System.out.print(curr.data + " ");
            curr = curr.right;
        } while (curr != head);
    }

    public void convertBinaryTreeToInOrderCircularDoublyLinkedListWithRecursionAndStaticFieldUtil(Node node) {
        if (node == null) {
            return;
        }
        convertBinaryTreeToInOrderCircularDoublyLinkedListWithRecursionAndStaticFieldUtil(node.left);
        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        convertBinaryTreeToInOrderCircularDoublyLinkedListWithRecursionAndStaticFieldUtil(node.right);
    }

    public void convertBinaryTreeToInOrderDoublyLinkedListWithRecursionAndStaticField(Node node) {
        convertBinaryTreeToInOrderCircularDoublyLinkedListWithRecursionAndStaticFieldUtil(node);
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.right;
        }
    }

    public void convertBinaryTreeToInOrderDoublyLinkedListWithRecursionWithoutStaticField(Node node) {
        Node n = convertBinaryTreeToInOrderDoublyLinkedListWithRecursionWithoutStaticFieldUtil(node);
        while (n.left != null) {
            n = n.left;
        }
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.right;
        }
    }

    private Node convertBinaryTreeToInOrderDoublyLinkedListWithRecursionWithoutStaticFieldUtil(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            Node left = convertBinaryTreeToInOrderDoublyLinkedListWithRecursionWithoutStaticFieldUtil(node.left);
            for (; left.right != null; left = left.right) ;
            left.right = node;
            node.left = left;
        }
        if (node.right != null) {
            Node right = convertBinaryTreeToInOrderDoublyLinkedListWithRecursionWithoutStaticFieldUtil(node.right);
            for (; right.left != null; right = right.left) ;
            right.left = node;
            node.right = right;
        }
        return node;
    }

    private Node findPredecessor(Node curr) {
        Node left = curr.left;
        while (left.right != null && left.right != curr) {
            left = left.right;
        }
        return left;
    }

    public void convertBinaryTreeToLevelOrderSpiralFashionDoublyLinkedListWithoutRecursionWithQueueAndStack(BinaryTree tree) {
        Deque<Node> queue = new LinkedList<>();
        queue.add(tree.root);
        Stack<Node> stack = new Stack<>();

        List<Node> list = new ArrayList<>();
        int height = findHeight(tree.root);
        boolean flag = false;
        while (height > 0) {
            list.clear();
            while (!queue.isEmpty()) {
                Node node = queue.pollLast();
                if (flag) {
                    if (node.left != null) {
                        list.add(node.left);
                    }
                    if (node.right != null) {
                        list.add(node.right);
                    }
                } else {
                    if (node.right != null) {
                        list.add(node.right);
                    }
                    if (node.left != null) {
                        list.add(node.left);
                    }
                }
                stack.add(node);
            }
            queue.addAll(list);
            flag = !flag;
            height--;
        }
        Node head = null;
        while (!stack.isEmpty()) {
            if (head == null) {
                head = stack.pop();
                head.right = null;
                head.left = null;
            } else {
                Node nod = stack.pop();
                nod.left = null;
                head.left = nod;
                nod.right = head;
                head = nod;
            }
        }

        while (head != null) {
            System.out.print(head.data + " ");
            head = head.right;
        }
    }

    private int findHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(1 + findHeight(node.left), 1 + findHeight(node.right));
    }

    public void constructBinaryTreeFromAncestorMatrix(int[][] mat) {
        //https://www.techiedelight.com/construct-binary-tree-ancestor-matrix/

        //SAMPLE INPUT
        //int[][] mat = {{0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 1, 1, 1}, {0, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0}};
        int N = mat.length;
        Map<Integer, List<Integer>> parentOfHowManyMap = new TreeMap<>();
        //1 is parent of 2 elements and 6 is a parent of 2 elements: parentOfHowManyMap will have an entry of 2->1,6
        for (int i = 0; i < N; i++) {
            int sum = Arrays.stream(mat[i]).sum();
            /*List<Integer> list = parentOfHowManyMap.computeIfAbsent(sum, k -> new ArrayList<>());
            list.add(i);*/
            parentOfHowManyMap.computeIfAbsent(sum, k -> new ArrayList<>()).add(i);
        }

        boolean[] parentFlagSet = new boolean[N];
        Node[] nodeStorageArr = new Node[N];
        int last = 0;
        for (Map.Entry<Integer, List<Integer>> entry : parentOfHowManyMap.entrySet()) {
            for (int val : entry.getValue()) { //val corresponds to row index in matrix : Each such index corresponds to an actual node in the tree
                last = val;
                Node node = new Node(val);
                nodeStorageArr[val] = node;

                if (entry.getKey() == 0) {
                    continue;
                }

                for (int i = 0; i < N; i++) {
                    if (!parentFlagSet[i] && mat[val][i] == 1) {
                        if (nodeStorageArr[val].left == null) {
                            nodeStorageArr[val].left = nodeStorageArr[i];
                        } else {
                            nodeStorageArr[val].right = nodeStorageArr[i];
                        }
                        parentFlagSet[i] = true;
                    }
                }
            }
        }
        t.levelOrderTraversalWithoutRecursionWithQueue(new BinaryTree(nodeStorageArr[last]));
    }

    public void constructAncestorMatrixFromBinaryTreeUsingExtraArray(BinaryTree tree) {
        Node node = tree.root;
        int size = findSize(node);
        int[][] mat;
        mat = new int[size][size];
        constructAncestorMatrixFromBinaryTreeUsingExtraArrayUtil(node, mat, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int findSize(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + findSize(node.left) + findSize(node.right);
    }

    private void addArrays(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = Math.max(arr1[i], arr2[i]);
        }
    }

    private Node constructAncestorMatrixFromBinaryTreeUsingExtraArrayUtil(Node node, int[][] mat, int size) {
        if (node == null) {
            return null;
        }
        int[] result = new int[size];
        Node left = constructAncestorMatrixFromBinaryTreeUsingExtraArrayUtil(node.left, mat, size);
        Node right = constructAncestorMatrixFromBinaryTreeUsingExtraArrayUtil(node.right, mat, size);
        if (left != null) {
            result[left.data] = 1;
            addArrays(result, mat[left.data]);
        }
        if (right != null) {
            result[right.data] = 1;
            addArrays(result, mat[right.data]);
        }
        mat[node.data] = result;
        return node;
    }

    public void constructAncestorMatrixFromBinaryTreeWithoutUsingExtraArray(BinaryTree tree) {
        Node node = tree.root;
        int size = findSize(node);
        int[][] mat;
        mat = new int[size][size];
        constructAncestorMatrixFromBinaryTreeWithoutUsingExtraArrayUtil(node, mat, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void constructAncestorMatrixFromBinaryTreeWithoutUsingExtraArrayUtil(Node node, int[][] mat, int size) {
        if (node == null) {
            return;
        }
        constructAncestorMatrixFromBinaryTreeWithoutUsingExtraArrayUtil(node.left, mat, size);
        constructAncestorMatrixFromBinaryTreeWithoutUsingExtraArrayUtil(node.right, mat, size);
        if (node.left != null) {
            mat[node.data][node.left.data] = 1;
            for (int i = 0; i < size; i++) {
                if (mat[node.left.data][i] == 1) {
                    mat[node.data][i] = 1;
                }
            }
        }
        if (node.right != null) {
            mat[node.data][node.right.data] = 1;
            for (int i = 0; i < size; i++) {
                if (mat[node.right.data][i] == 1) {
                    mat[node.data][i] = 1;
                }
            }
        }
    }

    public void constructBinaryTreeFromParentArrayWithoutRecursionWithQueue(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = map.computeIfAbsent(arr[i], k -> new ArrayList<>());
            list.add(i);
        }

        int rootVal = map.get(-1).get(0);
        Node root = new Node(rootVal);
        Queue<Node> que = new LinkedList<>();
        que.add(root);

        while (!que.isEmpty()) {
            Node node = que.poll();
            if (map.containsKey(node.data)) {
                for (Integer child : map.get(node.data)) {
                    if (node.left == null) {
                        Node left = new Node(child);
                        que.add(left);
                        node.left = left;
                    } else {
                        Node right = new Node(child);
                        que.add(right);
                        node.right = right;
                    }
                }
            }
        }

        t.levelOrderTraversalWithoutRecursionWithQueue(new BinaryTree(root));
    }

    Node root = null;

    public void constructBinaryTreeFromParentArrayWithRecursion(int[] parent) {
        Node[] nodes = new Node[parent.length];

        for (int i = 0; i < parent.length; i++) {
            constructBinaryTreeFromParentArrayWithRecursionUtil(parent, nodes, i);
        }
        t.levelOrderTraversalWithoutRecursionWithQueue(new BinaryTree(root));
    }

    private void constructBinaryTreeFromParentArrayWithRecursionUtil(int[] parent, Node[] nodes, int i) {
        if (nodes[i] != null) {
            return;
        }
        nodes[i] = new Node(i);
        if (parent[i] == -1) {
            root = nodes[i];
            return;
        }
        if (nodes[parent[i]] == null) {
            constructBinaryTreeFromParentArrayWithRecursionUtil(parent, nodes, parent[i]);
        }
        if (nodes[parent[i]].left == null) {
            nodes[parent[i]].left = nodes[i];
        } else {
            nodes[parent[i]].right = nodes[i];
        }
    }

    public void constructBinaryTreeWithLeftChildRightSiblingRelationship() {
        Node root = new Node(10);
        Node n2 = addChildToConstructBinaryTreeWithLeftChildRightSiblingRelationship(root, 2);
        Node n3 = addChildToConstructBinaryTreeWithLeftChildRightSiblingRelationship(root, 3);
        Node n4 = addChildToConstructBinaryTreeWithLeftChildRightSiblingRelationship(root, 4);
        Node n5 = addChildToConstructBinaryTreeWithLeftChildRightSiblingRelationship(root, 5);
        Node n6 = addChildToConstructBinaryTreeWithLeftChildRightSiblingRelationship(n4, 6);
        Node n7 = addChildToConstructBinaryTreeWithLeftChildRightSiblingRelationship(n5, 7);
        Node n8 = addChildToConstructBinaryTreeWithLeftChildRightSiblingRelationship(n5, 8);
        Node n9 = addChildToConstructBinaryTreeWithLeftChildRightSiblingRelationship(n5, 9);

        t.levelOrderTraversalWithoutRecursionWithQueue(new BinaryTree(root));
    }

    private Node addChildToConstructBinaryTreeWithLeftChildRightSiblingRelationship(Node node, int data) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            node.left = new Node(data);
            return node.left;
        } else {
            return addSiblingToConstructBinaryTreeWithLeftChildRightSiblingRelationship(node.left, data);
        }
    }

    private Node addSiblingToConstructBinaryTreeWithLeftChildRightSiblingRelationship(Node node, int data) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        node.right = new Node(data);
        return node.right;
    }

    public void convertBinaryTreeToLeftChildRightSiblingRelationshipTree(BinaryTree tree) {
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
        convertBinaryTreeToLeftChildRightSiblingRelationshipTreeUtil(tree.root);
        System.out.println();
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    private void convertBinaryTreeToLeftChildRightSiblingRelationshipTreeUtil(Node node) {
        if (node == null) {
            return;
        }
        convertBinaryTreeToLeftChildRightSiblingRelationshipTreeUtil(node.left);
        convertBinaryTreeToLeftChildRightSiblingRelationshipTreeUtil(node.right);
        if (node.left == null) {
            node.left = node.right;
        } else {
            Node left = node.left;
            while (left.right != null) {
                left = left.right;
            }
            left.right = node.right;
        }
        node.right = null;
    }

    public void convertBinaryTreeToTreeWithChildrenSumProperty(BinaryTree tree) {
        System.out.println("Before");
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
        System.out.println();
        convertBinaryTreeToTreeWithChildrenSumPropertyUtil(tree.root);
        System.out.println("After");
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    private void convertBinaryTreeToTreeWithChildrenSumPropertyUtil(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return;
        }

        convertBinaryTreeToTreeWithChildrenSumPropertyUtil(node.left);
        convertBinaryTreeToTreeWithChildrenSumPropertyUtil(node.right);
        int sumChildren = 0;
        if (node.left != null) {
            sumChildren += node.left.data;
        }
        if (node.right != null) {
            sumChildren += node.right.data;
        }
        int diff = sumChildren - node.data;

        if (diff > 0) {
            node.data = node.data + diff;
        } else if (diff < 0) {
            if (node.left.data < node.right.data) {
                node.left.data = node.left.data + Math.abs(diff);
                convertBinaryTreeToTreeWithChildrenSumPropertyUtil(node.left);
            } else {
                node.right.data = node.right.data + Math.abs(diff);
                convertBinaryTreeToTreeWithChildrenSumPropertyUtil(node.right);
            }
        }
    }

    public void convertBinaryTreeToItsSumTree(BinaryTree tree) {
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
        System.out.println();
        convertBinaryTreeToItsSumTreeUtil(tree.root);
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    private int convertBinaryTreeToItsSumTreeUtil(Node node) {
        if (node == null) {
            return 0;
        }
        int sum = 0;
        if (node.left == null && node.right == null) {
            sum += node.data;
            node.data = 0;
        } else {
            int leftSum = convertBinaryTreeToItsSumTreeUtil(node.left);
            int rightSum = convertBinaryTreeToItsSumTreeUtil(node.right);
            sum += node.data;
            node.data = leftSum + rightSum;
            sum += node.data;
        }
        return sum;
    }

    public void convertBinaryTreeToTreeInWhichEveryNodeStoresSumOfAllNodesInLeftSubtreeAndItsOwn(BinaryTree tree) {
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
        System.out.println();
        convertBinaryTreeToTreeInWhichEveryNodeStoresSumOfAllNodesInLeftSubtreeAndItsOwnUtil(tree.root);
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    private int convertBinaryTreeToTreeInWhichEveryNodeStoresSumOfAllNodesInLeftSubtreeAndItsOwnUtil(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return node.data;
        }
        int leftSum = convertBinaryTreeToTreeInWhichEveryNodeStoresSumOfAllNodesInLeftSubtreeAndItsOwnUtil(node.left);
        int rightSum = convertBinaryTreeToTreeInWhichEveryNodeStoresSumOfAllNodesInLeftSubtreeAndItsOwnUtil(node.right);
        node.data = node.data + leftSum;
        return node.data + rightSum;
    }

    public void convertBinaryTreeToItsMirrorTree(BinaryTree tree) {
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
        System.out.println();
        convertBinaryTreeToItsMirrorTreeUtil(tree.root);
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    private void convertBinaryTreeToItsMirrorTreeUtil(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return;
        }
        convertBinaryTreeToItsMirrorTreeUtil(node.left);
        convertBinaryTreeToItsMirrorTreeUtil(node.right);
        Node right = node.right;
        node.right = node.left;
        node.left = right;
    }

    public void flipBinaryTreeWithRecursion(BinaryTree tree) {
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
        System.out.println();
        tree = new BinaryTree(flipBinaryTreeWithRecursionUtil(tree.root));
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    private Node flipBinaryTreeWithRecursionUtil(Node node) {
        if (node.left == null) {
            return node;
        }
        Node flippedRoot = flipBinaryTreeWithRecursionUtil(node.left);
        Node left = node.left;
        Node right = node.right;
        left.left = right;
        left.right = node;

        node.left = null;
        node.right = null;
        return flippedRoot;
    }

    public void flipBinaryTreeWithoutRecursion(BinaryTree tree) {
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
        System.out.println();
        tree = new BinaryTree(flipBinaryTreeWithoutRecursionUtil(tree.root));
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    public Node flipBinaryTreeWithoutRecursionUtil(Node root) {
        Node curr = root;
        Node next = null, prev = null, prevCurrentRight = null;
        while (curr != null) {
            next = curr.left;
            curr.left = prevCurrentRight;
            prevCurrentRight = curr.right;
            curr.right = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public void constructBinaryTreeFromStringWithBracketRepresentation(String str) {
        BinaryTree tree = new BinaryTree(constructBinaryTreeFromStringWithBracketRepresentationUtil(str));
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    private Node constructBinaryTreeFromStringWithBracketRepresentationUtil(String str) {
        if (str.length() == 1) {
            return new Node(Character.getNumericValue(str.charAt(0)));
        }
        Node node = new Node(Character.getNumericValue(str.charAt(0)));
        int leftEndIndex = findEndIndexFromStringWithBracketRepresentation(str, 1);
        node.left = constructBinaryTreeFromStringWithBracketRepresentationUtil(str.substring(2, leftEndIndex));
        if (leftEndIndex < str.length() - 1) {
            node.right = constructBinaryTreeFromStringWithBracketRepresentationUtil(str.substring(leftEndIndex + 2, str.length() - 1));
        }
        return node;
    }

    private int findEndIndexFromStringWithBracketRepresentation(String str, int ind) {
        Queue<Character> que = new LinkedList();
        que.add(str.charAt(ind));
        int i = ind + 1;
        for (; i < str.length(); i++) {
            if(!(str.charAt(i) == '(' || str.charAt(i) == ')')){
                continue;
            }
            char ch = str.charAt(i);
            if (ch == '(') {
                que.add(ch);
                continue;
            } else if (que.peek() == '(') {
                que.poll();
            }
            if (que.isEmpty()) {
                break;
            }
        }
        return i;
    }

    public void constructBinaryTreeFromStringWithTernaryExpression(String str) {
        BinaryTree tree = new BinaryTree(constructBinaryTreeFromStringWithTernaryExpressionUtil(str));
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    private Node constructBinaryTreeFromStringWithTernaryExpressionUtil(String str) {
        if (str.length() == 1) {
            return new Node(Character.getNumericValue(str.charAt(0)));
        }
        Node node = new Node(Character.getNumericValue(str.charAt(0)));
        int leftEndIndex = findEndIndexFromStringWithTernaryExpression(str, 1);
        node.left = constructBinaryTreeFromStringWithTernaryExpressionUtil(str.substring(2, leftEndIndex));
        if (leftEndIndex < str.length() - 1) {
            node.right = constructBinaryTreeFromStringWithTernaryExpressionUtil(str.substring(leftEndIndex + 1, str.length()));
        }
        return node;
    }

    private int findEndIndexFromStringWithTernaryExpression(String str, int ind) {
        Queue<Character> que = new LinkedList();
        que.add(str.charAt(ind));
        int i = ind + 1;
        for (; i < str.length(); i++) {
            if(!(str.charAt(i) == '?' || str.charAt(i) == ':')){
                continue;
            }
            char ch = str.charAt(i);
            if (ch == '?') {
                que.add(ch);
                continue;
            } else if (que.peek() == '?') {
                que.poll();
            }
            if (que.isEmpty()) {
                break;
            }
        }
        return i;
    }

    public void convertBinaryTreeToTreeThatHoldsLogicalAndProperty(BinaryTree tree){
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
        System.out.println();
        Node root = tree.root;
        convertBinaryTreeToTreeThatHoldsLogicalAndPropertyUtil(root);
        t.levelOrderTraversalWithoutRecursionWithQueue(tree);
    }

    private void convertBinaryTreeToTreeThatHoldsLogicalAndPropertyUtil(Node node) {
        if(node == null || (node.left == null && node.right == null)){
            return;
        }
        convertBinaryTreeToTreeThatHoldsLogicalAndPropertyUtil(node.left);
        convertBinaryTreeToTreeThatHoldsLogicalAndPropertyUtil(node.right);

        node.data = node.left.data & node.right.data;
    }
}