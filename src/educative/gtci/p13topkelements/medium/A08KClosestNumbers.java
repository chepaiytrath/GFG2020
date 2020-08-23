package educative.gtci.p13topkelements.medium;

import java.util.PriorityQueue;

public class A08KClosestNumbers {
    public static void main(String[] args) {
        kClosest();
    }

    private static void kClosest() {
        /*int[] arr = new int[]{1, 5, 8, 9, 10, 15};

        // K Closest Numbers
        int k = 3;

        // Target value
        int target = 7;*/

        /*int[] arr = new int[]{5, 6, 7, 8, 9};
        int k = 3;
        int target = 7;*/

        int[] arr = new int[]{2, 4, 5, 6, 9};
        int k = 3;
        int target = 10;


        int closestElementIndex = binarySearch(arr, target);

        // Take lo and hi indices to track k nearest neighbours
        int lo = closestElementIndex - k;
        int hi = closestElementIndex + k;

        // Prevent AIOOB
        lo = Math.max(lo, 0);
        hi = Math.min(hi, arr.length - 1);

        PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> b.diff - a.diff);
        for (int i = lo; i <= hi; i++) {
            Element e = new Element();
            e.val = arr[i];
            e.diff = Math.abs(arr[i] - target);

            pq.add(e);

            // Limit addition of elements to maintain k closest neighbours
            if (pq.size() > k) {
                pq.poll();
            }
        }
        System.out.println(pq);
    }

    static class Element {
        int val;
        int diff;

        @Override
        public String toString() {
            return val + "";
        }
    }

    // Finds index of closest number in array which has minimum difference same as A06MinimumDifferenceElementClosestElement
    private static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int minDiff = Integer.MAX_VALUE;
        int minIndex = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                if (target - arr[mid] < minDiff) {
                    minIndex = mid;
                    minDiff = target - arr[mid];
                }
                start = mid + 1;
            } else if (arr[mid] > target) {
                if (arr[mid] - target < minDiff) {
                    minIndex = mid;
                    minDiff = arr[mid] - target;
                }
                end = mid - 1;
            }
        }
        return minIndex;
    }
}
