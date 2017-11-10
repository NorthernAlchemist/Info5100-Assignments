package Assignment7;

import java.util.Arrays;

public class PartitionTwoSubset {
    public static void main(String[] args){
        //int[] test = {1, 3, 5, 9};
        //int[] test = {1, 3, 5, 11};
        //int[] test = {1, 3};
        int[] test = {1, 11, 5, 5};
        System.out.print(findPartition(test));
    }

    public static boolean findPartition (int[] arr) {
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
