package class1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private ArrayList<String> wordList = new ArrayList<>();
    private ArrayList<Character> correctList = new ArrayList<>();
    private ArrayList<Character> wrongList = new ArrayList<>();
    private int wrongTimes = 0;

    public static void main(String args[]) {
        ArrayList<String> words = new ArrayList<>();
        words.add("foolish");
        words.add("dependent");
        words.add("water");
        words.add("position");
        words.add("fancy");

        Hangman game = new Hangman(words);
        String secret = game.chooseWord();
        game.playGame();

        while (!game.gameWon(secret) && !game.gameOver()) {
            game.handleGuess(secret);
        }

        if (game.gameWon(secret)){
            System.out.println("Correct! You win!");
        }
        if (game.gameOver()){
            System.out.println("You Died! The correct word is " + secret);
        }

    }
    
    public Hangman(ArrayList<String> words){
        this.wordList = words;
    }

    public String chooseWord(){
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
    }

    public void handleGuess(String word){
        System.out.println("Type the letter which you guess will in the word ");
        Scanner scanner = new Scanner(System.in);
        String guess = scanner.next();
        if (guess.length() == 0){
            System.out.println("Please type a valid letter!");
        } else if (guess.length() >= 2){
            System.out.println("You can only guess one letter each time!");
        } else {
            if (correctList.contains(guess.charAt(0)) || wrongList.contains(guess.charAt(0))){
                System.out.println("You have already guessed this letter");
            } else if (word.contains(guess)){
                correctList.add(guess.charAt(0));
                System.out.println("previously guessed number" + Arrays.toString(correctList.toArray()) + Arrays.toString(wrongList.toArray()));
                displayWord(word);
                printHangman();
            } else {
                wrongList.add(guess.charAt(0));
                System.out.println("previously guessed number" + Arrays.toString(correctList.toArray()) + Arrays.toString(wrongList.toArray()));
                displayWord(word);
                wrongTimes += 1;
                printHangman();
            }
        }
    }

    public boolean gameWon(String word){
        for (int i = 0; i < word.length(); i++) {
            if (!correctList.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean gameOver(){
        if (wrongTimes == 8){
            return true;
        } else {
            return false;
        }
    }


    public void playGame(){
        System.out.println("Welcome to the Hangman game!");
    }

    public void displayWord(String word){
        System.out.print("The remaining guesses are ");
        for (int i = 0; i < word.length(); i++){
            if (correctList.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
    }

    public void printHangman(){
        if (wrongTimes == 0){
            System.out.println(" ___________");
            System.out.println("|         |");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|__________________");
        }
        if (wrongTimes == 1){
            System.out.println(" ___________");
            System.out.println("|         |");
            System.out.println("|         0");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|__________________");
        }
        if (wrongTimes == 2){
            System.out.println(" ___________");
            System.out.println("|         |");
            System.out.println("|         0");
            System.out.println("|         |");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|__________________");
        }
        if (wrongTimes == 3){
            System.out.println(" ___________");
            System.out.println("|         |");
            System.out.println("|         0");
            System.out.println("|         |");
            System.out.println("|      ---");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|__________________");
        }
        if (wrongTimes == 4){
            System.out.println(" ___________");
            System.out.println("|         |");
            System.out.println("|         0");
            System.out.println("|         |");
            System.out.println("|      --- ---");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|__________________");
        }
        if (wrongTimes == 5){
            System.out.println(" ___________");
            System.out.println("|         |");
            System.out.println("|         0");
            System.out.println("|         |");
            System.out.println("|      --- ---");
            System.out.println("|        /");
            System.out.println("|       /");
            System.out.println("|");
            System.out.println("|__________________");
        }
        if (wrongTimes == 6){
            System.out.println(" ___________");
            System.out.println("|         |");
            System.out.println("|         0");
            System.out.println("|         |");
            System.out.println("|      --- ---");
            System.out.println("|        / \\");
            System.out.println("|       /   \\");
            System.out.println("|");
            System.out.println("|__________________");
        }
        if (wrongTimes == 7){
            System.out.println(" ___________");
            System.out.println("|         |");
            System.out.println("|         0");
            System.out.println("|         |");
            System.out.println("|      --- ---");
            System.out.println("|        / \\");
            System.out.println("|       /   \\");
            System.out.println("|     --");
            System.out.println("|__________________");
        }
        if (wrongTimes == 8){
            System.out.println(" ___________");
            System.out.println("|         |");
            System.out.println("|         0");
            System.out.println("|         |");
            System.out.println("|      --- ---");
            System.out.println("|        / \\");
            System.out.println("|       /   \\");
            System.out.println("|     --     --");
            System.out.println("|__________________");
        }
    }
}
