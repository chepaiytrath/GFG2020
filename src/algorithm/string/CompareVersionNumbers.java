package algorithm.string;

class CompareVersionNumbers {
    public static void main(String[] args) {
        System.out.println(VersionCompare("1.0.1", "1"));
    }

    public static int VersionCompare(String v1, String v2) {
        String[] s1 = v1.split("\\.");
        String[] s2 = v2.split("\\.");

        int i = 0;
        int j = 0;

        int res = 0;
        while (i < s1.length || j < s2.length) {
            int x = 0;
            int y = 0;
            if (i < s1.length) {
                x = Integer.valueOf(s1[i]);
            }

            if (j < s2.length) {
                y = Integer.valueOf(s2[j]);
            }

            if (x == y) {
                i++;
                j++;
            } else {
                res = x - y;
                break;
            }
        }

        return res < 0 ? -1 : res == 0 ? 0 : 1;
    }
}