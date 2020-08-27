package spacedrepetition;

class Solution {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};
        int num = 0;
        int i = 2;
        while (true) {
            boolean flag = true;
            for (int j = 0; j < arr.length; j++) {
                if (gcd(i, arr[j]) > 1) {
                    flag = false;
                }
            }
            if (flag == true) {
                num = i;
                break;
            }
            i++;
        }
        System.out.println("Number: " + num);
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}