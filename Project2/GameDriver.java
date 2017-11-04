package Project2;

import java.util.Scanner;

public class GameDriver {
    public static void main (String[] args){
        Game round1 = new Game(5);
        round1.playAGame();
        System.out.println("Play another game (y/n)?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while (input.equals("y")){
            System.out.println("How many playes in a game?");
            int playedNumber = scanner.nextInt();
            Game newRound = new Game(playedNumber);
            newRound.playAGame();
            System.out.println("Play another game (y/n)?");
            input = scanner.next();
        }
    }
}
