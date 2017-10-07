package class1;


public class Assignment4 {
    //Q.1
    String stringFormat(String string, int K){
        string = string.replaceAll("-","").toUpperCase();
        StringBuilder S = new StringBuilder();
        int index = 0;

        for (int i = string.length() - 1; i >= 0; i --){
            if (index % K == 0 && index != 0){
                S.append("-");
            }
            char temp = string.charAt(i);
            S.append(temp);
            index++;
        }
        return S.reverse().toString();
    }

    //Q.5
    String intToRoman(int number) {
        String[] symbolRoman = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
        int[] valueRoman = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        StringBuilder result = new StringBuilder();

        for (int i = 12; i >= 0; i--) {
            int j = number / valueRoman[i];

            if (j != 0){
                for (int k = 1; k <= j; k++){
                    result.append(symbolRoman[i]);
                }
            }

            number = number % valueRoman[i];
        }
        return result.toString();
    }

    //Q.Extra
    static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums3 = mergeTwoArrays(nums1, nums2);
        int n = nums3.length;
        double median;
        if (n % 2 == 0){
            median = (nums3[n / 2 - 1] + nums3[n / 2 ]) / (double)2;
        } else {
            median = nums3[(n - 1)] / (double)2;
        }

        return median;
    }

    static int[] mergeTwoArrays(int[] nums1, int[] nums2){
        int m = nums1.length;
        int n = nums2.length;
        int[] nums3 = new int[m + n];
        int index1 = 0;
        int index2 = 0;


        for (int k = 0; k < m + n; k++){
            if (index1 < m && index2 < n){
                if (nums1[index1] <= nums2[index2]){
                    nums3[k] = nums1[index1];
                    index1++;
                } else {
                    nums3[k] = nums2[index2];
                    index2++;
                }
            } else if (index1 >= m){
                nums3[k] = nums2[index2];
                index2++;
            } else {
                nums3[k] = nums1[index1];
                index1++;
            }
        }
        return nums3;
    }
}

class Tool{
    private int strength;
    private char type;

    Tool(int strength, char type){
        this.strength = strength;
        this.type = type;
    }

    void setStrength(int strength){
        this.strength = strength;
    }

    int getStrength(){
        return strength;
    }

    char getType(){
        return type;
    }
}

class Rock extends Tool{

    Rock(int strength){
        super(strength, 'r');
    }

    public boolean fight(Tool tool){
        if (tool.getType() == 's'){
            return 2 * Rock.super.getStrength() > tool.getStrength();
        } else if (tool.getType() == 'p'){
            return Rock.super.getStrength() > 2 * tool.getStrength();
        } else {
            return Rock.super.getStrength() > tool.getStrength();
        }
    }

}

class Paper extends Tool{

    Paper(int strength){
        super(strength, 'p');
    }

    public boolean fight(Tool tool){
        if (tool.getType() == 'r'){
            return 2 * Paper.super.getStrength() > tool.getStrength();
        } else if (tool.getType() == 's'){
            return Paper.super.getStrength() > 2 * tool.getStrength();
        } else{
            return Paper.super.getStrength() > tool.getStrength();
        }
    }
}

class Scissors extends Tool{

    Scissors(int strength){
        super(strength, 's');
    }

    public boolean fight  (Tool tool){
        if (tool.getType() == 'p'){
            return 2 * Scissors.super.getStrength() > tool.getStrength();
        } else if (tool.getType() == 'r'){
            return Scissors.super.getStrength() > 2 * tool.getStrength();
        } else {
            return Scissors.super.getStrength() > tool.getStrength();
        }
    }
}

class RockPaperScissorsGame{
    public static void main(String args[]){
        Scissors s = new Scissors(5);
        Paper p = new Paper(7);
        Rock r = new Rock(15);
        System.out.println(s.fight(p) + " , "+ p.fight(s) );
        System.out.println(p.fight(r) + " , "+ r.fight(p) );
        System.out.println(r.fight(s) + " , "+ s.fight(r) );
    }
}

class IpAddress{
    private String dottedDecimal;
    private int firstOctet;
    private int secondOctet;
    private int thirdOctet;
    private int fourthOctet;

    IpAddress(String dottedDecimal){
        this.dottedDecimal = dottedDecimal;
    }

    String getDottedDecimal(){
        return dottedDecimal;
    }

    int getOctet(int j){
        String[] octet = dottedDecimal.split("\\.");
        return Integer.parseInt(octet[j - 1]);
    }

}

class Driver{

    public static void main(String args[]){
        IpAddress ip = new IpAddress("216.27.6.136");
        System.out.println(ip.getDottedDecimal());
        System.out.println(ip.getOctet(4));
        System.out.println(ip.getOctet(1));
        System.out.println(ip.getOctet(3));
        System.out.println(ip.getOctet(2));
    }
}

class Student{
    private String name;
    private int id;

    Student(String name, int id){
        this.name = name;
        this.id = id;
    }

    String getName(){
        return name;
    }

    int getId(){
        return id;
    }
}

class Course{
    private String title;
    private int numberOfStudents = 0;
    private Student[] students = new Student[10];

    Course(String title){
        this.title = title;
    }

    void registerStudents(Student student){
        if (isFull() == true){
            System.out.println("The class is full");
        }else {
            this.numberOfStudents = numberOfStudents + 1;
            students[numberOfStudents - 1] = student;
        }

    }

    boolean isFull(){
        return this.numberOfStudents >= 10;
    }


    String getTitle(){
        return title;
    }

    int getNumberOfStudents(){
        return numberOfStudents;
    }
}

class Test{
    public static void main (String arg[]){
        Student L = new Student("L",1);
        Student Q = new Student("Q",2);
        Course java = new Course("java");
        java.registerStudents(L);
        java.registerStudents(Q);
        System.out.println(java.getNumberOfStudents());
    }
}


