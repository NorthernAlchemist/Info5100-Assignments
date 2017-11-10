package Assignment7;

public class PascalTriangle {
    public static void main(String[] args){
        printPascalTriangle(7);
    }

    public static void printPascalTriangle(int n){
        if (n == 0) return;

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= i; j++){
                System.out.print(numberTriangle(i, j) + " ");
            }
            System.out.println("");
        }
    }

    private static int numberTriangle(int i, int j){
        if (j == 1) return 1;
        if (i == j) return 1;
        return numberTriangle(i - 1, j - 1) + numberTriangle(i - 1, j);
    }
}
