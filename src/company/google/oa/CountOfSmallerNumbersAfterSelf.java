package company.google.oa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// #REVISIT
public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        int[] nums = new int[]{7, 4, 5, 2, 6, 1};
        System.out.println(countSmaller(nums));
    }


//    Explanation with an example:
//    arr[] = {7,4,5,2,6,1}
//    sol[] = {5,2,2,1,1,0}
//
//    We'll traverse from right to left on the arr. So initialize res with 0 because nothing's gonna be lesser than 1 in the array as it's the last element.
//
//    Create a TreeNode root with val = 1 and count = 1(default = 1, I know this is confusing, but will explain ahead. Just know that the count in each TreeNode is the number of values that tree node's value is greater than. This comes in handy when we reach a number while traversing the array which is greater than this tree node's value itself.)
//
//    Start array traversal from length - 2 to 0 because the last element is already processed and we have the count 0 in the list corresponding to the last element.
//    In each iteration, try to insert the number into the BST.
//
//    Each insertion gives you the count of numbers(TreeNode vals) that the current num is greater than. Save this count in the res list.
//
//    Visualize this in form of a BST. If your number is greater than the current treenode val, then go right. This would also mean that your number is greater than all the numbers which the treenode was greater than. Keep summing up these counts and when you reach a null, just insert the new node and return the count you maintained. The node you insert however stores count = 1 by default.
//
//    For 6 you get back the count as 1 and you store treenode with val = 6, count = 1
//    For 2, you get back count as 1 because it was greater than 1 but less than 6 so one right one left. In the same insertion, you update the count of 6 to 2.
//    For 5, one right, one left, one right and then insert 5 with default count = 1. Two rights at 1 and 2 give the count back as 0 + 1 + 1 = 2. Like in previous step, you update the count of 6 to 3.
//    For 4, one right, left, right, left and insert 4 with default count = 1. Two rights at 1 and 2 give the count back as 0 + 1 + 1 = 2. Update counts of 6 to 4 and of 5 to 2.
//    For 7, one right, another right. Right at 1 gives count = 0 + 1 = 1. Right at 6 gives count = 1 + 4 = 5. Return this count - 5. Save treenode with default count = 1.
    public static List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }

        List<Integer> res = new ArrayList<>();
        res.add(0);

        int len = nums.length;
        int lastNum = nums[len - 1];

        TreeNode root = new TreeNode(lastNum);

        for (int i = len - 2; i >= 0; i--) {
            res.add(insert(root, nums[i]));
        }
        Collections.reverse(res);
        return res;
    }

    public static int insert(TreeNode root, int num) {
        TreeNode node = root;
        int rightCounts = 0;
        while (true) {
            if (num > node.val) {
                rightCounts += node.count;
                if (node.right == null) {
                    node.right = new TreeNode(num);
                    break;
                } else {
                    node = node.right;
                }

            } else {
                node.count++;
                if (node.left == null) {
                    node.left = new TreeNode(num);
                    break;
                } else {
                    node = node.left;
                }
            }
        }
        return rightCounts;
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;

        int val;
        int count;

        TreeNode(int val) {
            this.count = 1;
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", count=" + count +
                    '}';
        }
    }
}

