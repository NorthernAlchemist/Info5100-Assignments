package class1;

import java.util.Arrays;
import java.util.Scanner;


public class Assignment2Solution{

    public static void main (String[] args){
    //Task 1
        System.out.println("How many hours have you work in last month?");
        Scanner input1 = new Scanner(System.in);
        double hours = input1.nextDouble();
        double salary = employeeSalary(hours);
        System.out.println("The salary you should get is " + salary);
    //Task 2
        System.out.println("The integer you want to calculate is  ");
        Scanner input2 = new Scanner(System.in);
        int digits = input2.nextInt();
        int sumDigits = addDigits(digits);
        System.out.println("Its sum of digits is " + sumDigits);
    //Task 3
        System.out.println("The n you want to input is ");
        Scanner input3 = new Scanner(System.in);
        int range = input3.nextInt();
        printPerfectNumbers(range);

    //Task 5
        System.out.println("Hi, Welcome to Domino, what's your name?");
        Scanner input51 = new Scanner(System.in);
        String name = input51.next();
        System.out.println("Nice to meet you, " + name);

        Customer customer = new Customer();
        //store the name into Customer
        customer.name(name);

        PizzaWith domino = new PizzaWith();
        System.out.print("The types of pizza we offer are ");
        System.out.println(Arrays.toString(domino.pizzaType));
        System.out.println("Type the index of pizza you want to order.");

        Scanner input52 = new Scanner(System.in);
        int pizzaIndex = input52.nextInt();

        while (pizzaIndex < 1 || pizzaIndex > 3){
            System.out.println("please type a valid number!");
            Scanner input53 = new Scanner(System.in);
            pizzaIndex = input53.nextInt();
        }

        System.out.println("How many this type of pizza you want?");
        Scanner input54 = new Scanner(System.in);
        int numberOrdered = input54.nextInt();

        customer.pizzaNumber(pizzaIndex, numberOrdered);
        double totalPrice = 0;
        //I know there should have a while loop to ask if the customer want anything else. Sorry for my lazy.
        for (int i = 0; i < 3; i++){
            double pizzaPrice = PizzaWith.unitPrice[i] * Customer.pizzaNumber[i];
            totalPrice = totalPrice + pizzaPrice;
        }
        System.out.println("Your total cost is " + totalPrice);
        //assume we earn 5 points per dollar spend.
        double points = totalPrice * 5;
        System.out.println("You will earn " + points +" points after this purchase.");

    //Task 6
        System.out.println("What's the size of isosceles right angled triangle you want?");
        Scanner input6 = new Scanner(System.in);
        int size = input6.nextInt();
        printIsoscelesTriangle(size);

    }

    public static double employeeSalary(double hours){
        double salary = 0;
        if (hours <= 36){
            salary = hours * 15;
        } else if (hours <= 41){
            salary = 36 * 15 + (hours - 36) * 15 * 1.5;
        } else if (hours <= 48){
            salary = 36 * 15 + 5 * 15 * 1.5 + (hours -41) * 30;
        } else {
            salary = 36 * 15 + 5 * 15 * 1.5 + 7 * 30;
        }
        return salary;
    }

    public static int addDigits(int input){
        int digitNumber = 0;
        int index = input;
        int sum = 0;

        while (index != 0){
            index = index / 10;
            digitNumber ++;
        }

        for (int i = 0; i < digitNumber; i ++){
            sum = sum + input % 10;
            input = input / 10;
        }


        if ((sum / 10) != 0){
            return addDigits(sum);
        } else{
            return sum;
        }
    }

    public static void printPerfectNumbers(int n){
        System.out.println("The perfect numbers between 1 and n are: ");
        for (int i = 1; i <= n; i++){
            int sum = 0;

            for (int j = 1; j < i; j ++){
                if (i % j == 0){
                    sum = sum + j;
                }
            }

            if (sum == i){
                System.out.print(i + " ");
            }
        }
        System.out.println("");
    }

    public static void printIsoscelesTriangle(int n){
        System.out.println("*");
        for (int i = 2; i < n; i++){
            for (int j = 1; j <= i; j++){
                if (j == 1 || j == i){
                    System.out.print("*");
                } else{
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        for (int k = 1; k <= n; k++){
            System.out.print("*");
        }
    }

}

class PizzaWith{

     String[] pizzaType = new String[3];
     static double[] unitPrice = new double[3];
     double loyatlyPoints;

     PizzaWith(){
        pizzaType[0] = "1.Neapolitan";
        pizzaType[1] = "2.Chicago";
        pizzaType[2] = "3.New York Style";
        unitPrice[0] = 7.99;
        unitPrice[1] = 10.99;
        unitPrice[2] = 15.99;
        loyatlyPoints = 0;
    }

    static double[] unitPrice(){
         return unitPrice;
    }

    double loyatlyPoints(double points){
        loyatlyPoints = points;
        return loyatlyPoints;
    }
}



class Customer{
    String customerName;
    static int[]  pizzaNumber;
    /*Using two arrays to record the order, first one is used to record the type of pizza, the second one is used to
    record the number of each type of pizza that ordered. If the elements in these two arrays have same index
    number, then they are correspond to each other.
    */

     Customer(){
        customerName = "No Name";
        pizzaNumber = new int[3];
    }

    String name(String name){
        customerName = name;
        return customerName;
    }

    static int[] pizzaNumber(int i, int number){
        //i is the index of pizza that customer chose, e.g 1.Neapolitan, it's correspond location in array is 0, which is i - 1.
        pizzaNumber[i - 1] = pizzaNumber[i - 1] + number;
        //the customer may order more than once
        return pizzaNumber;
    }


}


