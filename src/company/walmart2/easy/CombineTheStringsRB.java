package company.walmart2.easy;

public class CombineTheStringsRB {
    public static void main(String[] args) {
        String[] arr = new String[]{"RBR", "BBR", "RRR"};
        System.out.println(getAns(arr, arr.length));
    }

    static int getAns(String str[], int n) {
        int ans = 0, rr = 0, rb = 0, br = 0, bb = 0;
        for (int i = 0; i < n; i++) {
            if (str[i].charAt(0) == 'R') {
                if (str[i].charAt(str[i].length() - 1) == 'R') {
                    rr++;
                } else
                    rb++;
            } else if (str[i].charAt(0) == 'B') {
                if (str[i].charAt(str[i].length() - 1) == 'B') {
                    bb++;
                } else
                    br++;
            }
        }
        int min = Math.min(rb, br);
        if (rb == 0 && br == 0)
            ans = Math.max(rr, bb);
        else if (rb == br)
            ans = rr + bb + (2 * min);
        else
            ans = rr + bb + (2 * min) + 1;

        ans *= str[0].length();
        if (ans == str[0].length())
            return 0;
        else return ans;
    }
}
