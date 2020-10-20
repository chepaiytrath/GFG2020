package algorithm.basictechniques.kadane;

public class A06MaximumProductSubarray {
    public static void main(String[] args) {
        maxProd();
        maxProduct();
    }

    // Using two arrays : Overflows even for long[] ex. {90, 91, -91, 91, -91, -90, 90, 90, -90, -90}
    private static void maxProd() {
        long[] arr = new long[]{90, 91, -91, 91, -91, -90, 90, 90, -90, -90};

        int n = arr.length;
        long[] maxDP = new long[n]; //Stores the max value possible till i out of arr[i], maxDP[i - 1] * arr[i], minDP[i - 1] * arr[i]
        long[] minDP = new long[n]; //Stores the min value possible till i out of arr[i], maxDP[i - 1] * arr[i], minDP[i - 1] * arr[i]

        maxDP[0] = arr[0];
        minDP[0] = arr[0];

        long maxProd = arr[0];

        for (int i = 1; i < n; i++) {

            long x = arr[i];
            long y = maxDP[i - 1] * x;
            long z = minDP[i - 1] * x;


            maxDP[i] = Math.max(x, Math.max(y, z));
            minDP[i] = Math.min(x, Math.min(y, z));

            maxProd = Math.max(maxProd, maxDP[i]);
        }
        System.out.println(maxProd);
    }


    // Without using extra space : Overflows even for long[] ex. {90, 91, -91, 91, -91, -90, 90, 90, -90, -90}
    public static void maxProduct() {
        long[] arr = new long[]{90, 91, -91, 91, -91, -90, 90, 90, -90, -90};

        if (arr == null || arr.length == 0) {
            return;
        }
        //Use only variables instead of array
        long max = arr[0];
        long min = arr[0];
        long maxProd = arr[0];

        for (int i = 1; i < arr.length; i++) {
            long x = arr[i];
            long y = max * x;
            long z = min * x;

            max = Math.max(arr[i], Math.max(y, z));
            min = Math.min(arr[i], Math.min(y, z));

            maxProd = Math.max(maxProd, max);
        }
        System.out.println(maxProd);
    }

    // Doesn't overflow : Best solution
    private static void maxProductNonOverflow() {
        long[] arr = new long[]{90, 91, -91, 91, -91, -90, 90, 90, -90, -90};

        long max = arr[0];
        long min = arr[0];
        long maxProduct = arr[0];

        //RULE : Ensure that after multiplying max and min with arr[i], max will always hold the max value and min will always hold the min value
        for (int i = 1; i < arr.length; i++) {

            //TO ENSURE RULE: When multiplied by -ve number, max becomes min and min becomes max.
            if (arr[i] < 0) {
                long temp = max;
                max = min;
                min = temp;
            }

            // As per rule,
            // multiplying max with arr[i], max will always hold the max value
            // multiplying min with arr[i], min will always hold the min value
            max = Math.max(arr[i], max * arr[i]);
            min = Math.min(arr[i], min * arr[i]);

            maxProduct = Math.max(maxProduct, max);
        }
        System.out.println(maxProduct);
    }
}
