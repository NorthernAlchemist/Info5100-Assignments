package Project2;

public class Game {
    public final int PLAYERS;
    private Deck deck;
    private Hand[] players;
    private Trick[] tricks;
    private int numberOfTricks = 0;
    private boolean hearts = false;
    private boolean queenOfSpades = false;

    public Game(int numberOfPlayers){
        PLAYERS = numberOfPlayers;
        players = new Hand[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++){
            players[i] = new Hand(i, 52 / numberOfPlayers);
        }
        tricks = new Trick[52 / numberOfPlayers];
    }

    public int getNumberOfTricks() {
        return numberOfTricks;
    }

    public boolean getHearts() {
        return hearts;
    }

    public boolean getQueenOfSpades() {
        return queenOfSpades;
    }

    public void updateHeartsAndQueen(Card card){
        if (hearts == false && card.getSuit() == 2) {
            System.out.println("Hearts is now broken");
            hearts = true;
        }
        if (card.getSuit() == 3 && card.getNum() == 12) queenOfSpades = true;
    }

    public void playAGame(){
        deck = new Deck();
        deck.shuffle();

        //deal card
        for (int i = 1; i <= tricks.length; i++){
            for (int j = 0; j < PLAYERS; j++){
                players[j].addCard(deck.dealCard());
            }
        }
        int cardsLeft = deck.getCurrentSize();

        System.out.println("Deal and sort part.");
        //sort and display
        // playerNum is the index of player who start this trick
        int playerNum = -1;
        for (int i = 0; i < PLAYERS; i++){
            players[i].sort();
            players[i].setShortest();
            System.out.print("      player  " + i);
            System.out.println("  shortest = " + players[i].getShortest());
            players[i].display();

            if (players[i].findLowest(0) >= 0
                && players[i].getCard(players[i].findLowest(0)).getNum() == 2){
                playerNum = i;
            }
        }

        System.out.println("");
        System.out.println("Play stage:");

        for (int i = 0; i < tricks.length; i++){
            Trick trick = new Trick(PLAYERS);
            tricks[i] = trick;
            numberOfTricks++;
            if (i == 0){
                Card pick = players[playerNum].removeCard(tricks.length - 1);
                trick.update(playerNum, new Card(2, 0));
                tricks[i].addCard(pick);
                System.out.print("player " + playerNum + "          ");
                pick.display();

                int cardPlayed = 0;
                int otherPlayer = playerNum + 1;
                while (cardPlayed < playerNum){
                    otherPlayer = otherPlayer % PLAYERS;
                    pick = players[otherPlayer].playACard(this, trick);
                    trick.addCard(pick);
                    trick.update(otherPlayer, pick);
                    updateHeartsAndQueen(pick);
                    System.out.print("player " + otherPlayer + "          ");
                    pick.display();
                    cardPlayed++;
                    otherPlayer++;
                }

                while (deck.getCurrentSize() > 0){
                    Card left = deck.dealCard();
                    updateHeartsAndQueen(left);
                    System.out.print("undealt card      ");
                    left.display();
                }
                System.out.println("");
            } else {
                playerNum = tricks[i - 1].getWinner();
                int cardPlayed = 0;
                while (cardPlayed < PLAYERS){
                    playerNum = playerNum % PLAYERS;
                    Card pick = players[playerNum].playACard(this, trick);
                    tricks[i].addCard(pick);
                    trick.update(playerNum, pick);
                    updateHeartsAndQueen(pick);
                    System.out.print("player " + playerNum + "          ");
                    pick.display();
                    playerNum++;
                    cardPlayed++;
                }
                System.out.println("");
            }
        }

        //points feedback
        for (int i = 0; i < PLAYERS; i++){
            System.out.println("player " + i + "    score = " + computePoints(i));
        }
    }

    private int computePoints(int playerNum){
        int totalPoint = 0;
        for (int i = 0; i < tricks.length; i++){
            if (tricks[i].getWinner() == playerNum){
                while (tricks[i].getCurrentSize() > 0){
                    Card calculate = tricks[i].removeCard(0);
                    if (calculate.getSuit() == 2) totalPoint++;
                    if (calculate.getSuit() == 3 && calculate.getNum() == 12)
                        totalPoint += 12;
                }
            }
        }
        return totalPoint;
    }

}
