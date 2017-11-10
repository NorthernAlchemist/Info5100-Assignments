package Assignment7;

import java.util.Arrays;

public class MaxValue extends Thread {
    private int[] arr;
    private int low;
    private int high;

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 100);
        }
        int max = findMax(arr);
//        System.out.println(Arrays.toString(arr));
        System.out.println("max value is : " + max);
    }

    public MaxValue(int[] arr, int low, int high){
        this.arr = arr;
        this.low = low;
        this.high = high;
    }

    private static int findMax(int[] arr) throws InterruptedException{
        int len = arr.length;
        Thread[] threads = new Thread[4];

        for (int i = 0; i < 4; i++){
            threads[i] = new MaxValue(arr, (i * len) / 4, ((i + 1) * len / 4));
            threads[i].start();
        }

        for (int i = 0; i < 4; i++){
            threads[i].join();
        }
        int max1 = Math.max(arr[0], arr[25]);
        int max2 = Math.max(arr[50], arr[75]);
        return Math.max(max1, max2);
    }

    @Override
    public void run(){
        for (int i = low; i < high; i++){
            if (arr[i] >= arr[low]){
                arr[low] = arr[i];
            }
        }
    }

}
