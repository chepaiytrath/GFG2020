package company.walmart2.easy;

public class PowerOfNumberToItsReverse {
    public static void main(String[] args) {
        int x = 12;
        int rev = reverse(x);
//        String res = pow(x, rev);
        String res = pow(2, 100);
        System.out.println(res);
    }

    private static int reverse(int x) {
        int ans = 0;
        while (x > 0) {
            ans += x % 10;
            x /= 10;
            if (x != 0) {
                ans *= 10;
            }
        }
        return ans;
    }

    private static String pow(int x, int n) {
        int[] res = new int[9999999];
        int temp = x;
        int index = 0;
        while (temp > 0) {
            int d = temp % 10;
            res[index++] = d;
            temp /= 10;
        }

        for (int i = 0; i < n-1; i++) {
            index = calculateProduct(res, x, index);
        }
        String ans = "";
        for (int j = index - 1; j >= 0; j--) {
            ans += res[j];
        }
        return ans;
    }

    private static int calculateProduct(int[] arr, int num, int index) {
        int carry = 0;
        int i = 0;
        while (i < index) {
            int product = arr[i] * num + carry;
            arr[i] = product % 10;
            carry = product / 10;
            i++;
        }
        while (carry > 0) {
            arr[i] = carry % 10;
            carry /= 10;
            i++;
        }
        return i;
    }
}
