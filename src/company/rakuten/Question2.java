package company.rakuten;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Question2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer N = sc.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int sum = findSumOfEvenOccuringNumberInArray(arr);
        System.out.println(sum);
    }

    private static int findSumOfEvenOccuringNumberInArray(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int val : arr){
            if(countMap.get(val) == null){
                countMap.put(val, 1);
            }else{
                countMap.put(val, countMap.get(val)+1);
            }
        }

        int sum = 0;
        for(Map.Entry entry : countMap.entrySet()){
            if((Integer) entry.getValue() % 2 == 0){
                sum += (Integer) entry.getKey();
            }
        }
        return sum;
    }
}
