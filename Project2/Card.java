package Project2;

public class Card {
    private int num;
    private int suit;

    public Card(int num, int suit){
        this.num = num;
        this.suit = suit;
    }

    public int getNum() {
        return num;
    }

    public int getSuit() {
        return suit;
    }

    public void display(){
        switch (num){
            case 14:
                System.out.print("Ace");
                break;
            case 13:
                System.out.print("King");
                break;
            case 12:
                System.out.print("Queen");
                break;
            case 11:
                System.out.print("Jack");
                break;
            default:
                System.out.print(num);
                break;
        }

        System.out.print(" of ");

        switch (suit){
            case 0:
                System.out.print("clubs");
                break;
            case 1:
                System.out.print("diamonds");
                break;
            case 2:
                System.out.print("hearts");
                break;
            case 3:
                System.out.print("spades");
                break;
        }
        System.out.println("");
    }
}
