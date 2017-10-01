package class1;

import java.util.Arrays;
import java.util.Scanner;

public class Assignment3 {

    public static void main(String[] args) {
        //Q3
	    System.out.println("The Word you want to clean is?");
	    Scanner input1 = new Scanner(System.in);
        String word = input1.nextLine();
        String cleanedWord = removeVowelsFromString(word);
        System.out.println(cleanedWord);

        //Q4
        System.out.println("The first string you want to compare is?");
        Scanner input2 = new Scanner(System.in);
        String string1 = input2.nextLine();
        System.out.println("The second string you want to compare is?");
        Scanner input3 = new Scanner(System.in);
        String string2 = input3.nextLine();

        boolean ifAnagram = checkIfTwoStringsAreAnagrams(string1, string2);
        if (ifAnagram){
            System.out.println(string1 + " and " + string2+ " are anagram.");
        } else {
            System.out.println(string1 + " and " + string2 + " are not anagram.");
        }

    }

    /*
    Question 1:
    In line 11, the Book class isn't a subclass of any superclass. Thus, the super() here doesn't have any effect.

    In line 17, we have the same constructor as in line 15 even they seems to have a different input name. We can
    rewrite the constructor into follows:
        public book(int size, int price){
            this.size = size;
            this.price = price;
            }


    In line 21, if we still want to realize the function of set a new name to the book. Then we won't need to return
    anything, we only need to set the new name as input. As follows,
        public void setName(String name){
            this.name = name;
            }


     */

    /*
    Question 2:
    In line 3, if we want to return time in line 4. We need to replace the void type to String type since
    the variable time is a String type variable.
     */

    
    public static String removeVowelsFromString(String input){
        String results = input.replaceAll("[aeiouAEIOU]", "");
        return results;
    }


    public static boolean checkIfTwoStringsAreAnagrams(String s1, String s2){
        if (s1 == s2){
            throw new IllegalArgumentException("You can't input two same strings.");
        }

        if (s1.length() != s2.length()){
            return false;
        }else{
            char[] arrayString1 = s1.toCharArray();
            char[] arrayString2 = s2.toCharArray();

            Arrays.sort(arrayString1);
            Arrays.sort(arrayString2);

            return Arrays.equals(arrayString1, arrayString2);
        }
    }

}

//Q 5
class Calculator{

    private static double result;
    private static double[] results;

    //Q 5.1
    static double addition(double number1, double number2){
        result = number1 + number2;
        return result;
    }

    static double subtraction(double minuend, double subtrahend){
        result = minuend - subtrahend;
        return result;
    }

    static double multiplication(double number1, double number2){
        result = number1 * number2;
        return result;
    }

    static double division(double dividend, double divisor){
        if (divisor == 0){
            throw new IllegalArgumentException("The input divisor can't be zero!");
        } else {
            result = dividend / divisor;
        }
        return result;
    }

    //Q 5.2
    static double[] squareRoot(double number1){
        if (number1 < 0){
            throw new IllegalArgumentException("The input number must greater than or equal to zero!");
        } else if (number1 == 0){
          results = new double[1];
          results[0] = Math.sqrt(number1);
        } else {
            results = new double[2];
            results[0] = Math.sqrt(number1);
            results[1] = 0 - Math.sqrt(number1);
        }
        return results;
    }

    static double square(double number1){
        result = number1 * number1;
        return result;
    }

    static double cube(double number1){
        result = number1 * number1 * number1;
        return result;
    }

    //Q 5.3
    static double fahrenheitCelsiusConvert(double number1){
        result = (number1 - 32) * 5 / 9;
        return result;
    }

    static double feetInchesConvert(double number1){
        result = number1 * 12;
        return result;
    }

    //Q 5.Extra
    static double[] quadraticEquationSolution(double a, double b, double c){
        double temp = b * b - 4 * a * c;
        if (temp < 0){
            System.out.println("This function has no solution");
        } else if (temp == 0){
            results = new double[1];
            results[0] = - b / (2 * a);
        } else {
            results = new double[2];
            results[0] = (- b + Math.sqrt(temp)) / (2 * a);
            results[1] = (- b - Math.sqrt(temp)) / (2 * a);
        }
        return results;
    }
}
