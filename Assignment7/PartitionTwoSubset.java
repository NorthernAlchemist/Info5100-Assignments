package Assignment7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PartitionTwoSubset {
    public static void main(String[] args){
        //int[] test = {1, 3, 5, 9};
        //int[] test = {1, 3, 5, 11};
        //int[] test = {1, 3};
        //int[] test = {1, 11, 5, 5};
        //int[] test = {1, -3 , -6, 8, -4, 2, -6};
        int[] test = {-2, -3, 6, 4, 10, -5, -118};
        System.out.print(findPartition(test));
    }

    //Works for all integer input
    //But I thought if the integer call be both positive or negative
    // then it loks like a NP-problem.
    public static boolean findPartition(int[] arr){
        if (arr == null || arr.length == 0) return true;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int num : arr) {
            sum += num;

            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else{
                map.put(num, 1);
            }
        }

        if (sum % 2 > 0) return false;
        sum /= 2;
        return helper(map, sum);
    }

    private static boolean helper(Map<Integer, Integer> map, int sum){
        if (map.containsKey(sum) && map.get(sum) > 0) return true;

        for (int key : map.keySet()){
            if (key < sum && map.get(key) > 0){
                map.put(key, map.get(key) - 1);
                if (helper(map, sum - key)) return true;
                //backtracking
                map.put(key, map.get(key) + 1);
            }
        }
        return false;
    }



    //This should only works when all integers in array are positive
    public static boolean findPartitionAllPositive (int[] arr) {
        if (arr == null || arr.length == 0) return true;

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        if (sum % 2 > 0) return false;
        sum /= 2;
        int len = arr.length;

        //set up DP matrix
        boolean[][] dp = new boolean[len + 1][sum + 1];
        for (boolean[] subDP: dp){
            Arrays.fill(subDP, false);
        }
        for (int i = 1; i < len + 1; i++){
            dp[i][0] = true;
        }
        for (int i = 1; i < sum + 1; i++){
            dp[0][i] = false;
        }
        dp[0][0] = true;

        //transfer function
        for (int i = 1; i < len + 1; i++){
            for (int j = 1; j < sum + 1; j++){
                if (j < arr[i - 1]){
                    dp[i][j] = dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        return dp[len][sum];
    }
}
