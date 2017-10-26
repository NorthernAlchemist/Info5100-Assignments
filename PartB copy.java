package class1;

import java.util.ArrayList;

public class PartB{

    public int[] reverseEvenIndices(int[] nums){
        if(nums == null) return null;

        int left = 0;
        int right = nums.length - 1;

        while (left < right){
            if ((left + right) % 2 != 0){
                right--;
            } else {
                swap(nums,left,right);
                left += 2;
                right -= 2;
            }
        }

        return nums;
    }


    private void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }


    public int arrangeCoins(int n){
        int row = 0;
        int rowK = 1;

        while (true){
            n -= rowK;
            if (n < 0) break;
            row++;
            rowK *= 2;
        }
        return row;
    }


    public int minMoves(int[] nums){
        int move = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int length = nums.length;

        for (int i = 0; i < length; i++){
            sum += nums[i];
            if (nums[i] < min) min = nums[i];
        }
        move = sum - length * min;

        return move;
    }


    public int countNumberOfPossibleWays(int m, int n, int x){

        if (x < n) return 0;
        if (n == 0){
            if (x == 0) return 1;
            return 0;
        }

        int count = 0;

        for (int i = 1; i <= m; i++){
            count += countNumberOfPossibleWays(m, n - 1, x - i);
        }

        return count;
    }


    public ArrayList<Cell> findPath(int[][] maze){
        ArrayList<ArrayList<Cell>> result = new ArrayList<>();
        int len = maze.length;

        helper(maze,result, new ArrayList<Cell>(), 0, 0, len);
        return result.get(0);
    }

    private void helper(int[][] maze, ArrayList<ArrayList<Cell>> result, ArrayList<Cell> temp, int row, int col, int len){
        int[] moveRow = {1, 0};
        int[] moveCol = {0, 1};

        if (temp.size() == 2 * len - 1){
            result.add(new ArrayList<Cell>(temp));
        }

        if (accessible(maze, row, col)){
            temp.add(new Cell(row, col));
            for (int i = 0; i < 2; i++){
                helper(maze, result, temp, row + moveRow[i], col + moveCol[i], len);
            }
        }
        return;


    }

    private boolean accessible (int[][] maze, int row, int col){
        int len = maze.length;
        if (row >= 0 && row < len && col >= 0 && col < len && maze[row][col] == 1) {
            return true;
        }
        return false;
    }
}



class Cell{
    int x;
    int y;
    Cell (int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "["+this.x + "," + this.y + "]";
    }
}