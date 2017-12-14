package Project2;

public class Hand extends GroupOfCards {
    public final int NUM;
    private int shortest = 0;

    public Hand(int playerNum, int numberOfCards){
        super(numberOfCards);
        NUM = playerNum;
    }

    public void sort(){
        for (int unsorted = getCurrentSize(); unsorted >= 1; unsorted--){
            int max = -1;
            int maxIndex = -1;
            int i = unsorted - 1;
            while (i >= 0){
                int expression = 12 * getCard(i).getSuit() +getCard(i).getNum();
                if (expression > max){
                    max = expression;
                    maxIndex = i;
                }
                i--;
            }
            Card maxCard = removeCard(maxIndex);
            addCard(maxCard);
        }
    }

    public void setShortest(){
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < 2; i++){
            int count = 0;
            for (int j = 0; j < getCurrentSize(); j++){
                if (getCard(j).getSuit() == i) count++;
            }
            if (count <= smallest){
                smallest = count;
                shortest = i;
            }
        }

        int spadeCount = 0;
        for (int i = 0; i < getCurrentSize(); i++){
            if (getCard(i).getSuit() == 3) spadeCount++;
        }
        if (spadeCount <= smallest && find(12, 3) == -1 && find(13, 3) == -1
                                   && find(14, 3) == -1){
            shortest = 3;
        }
    }

    public int getShortest() {
        return shortest;
    }

    public Card playACard(Game game, Trick trick) {
        int index = -1;
        if (trick.getCurrentSize() == 0){
            if (findCount(shortest) > 0){
                index = findHighest(shortest);
            } else {
                index = findLowest(game);
                if (index == -1) index = findLowest(2);
            }
        }else if (trick.getCurrentSize() == game.PLAYERS - 1){
            if (findLastHigh(trick.getWinningCard().getSuit()) < 0) {
                index = findLastHigh(trick.getWinningCard().getSuit());
            } else if (!trick.getHearts() && !trick.getQueen()){
                index = findLastHigh(trick.getWinningCard().getSuit());
            } else {
                index = findLowest(trick.getWinningCard().getSuit());
            }
        }else if (findHighestBelow(trick.getWinningCard()) >= 0){
            index = findHighestBelow(trick.getWinningCard());
        }else if (findMiddleHigh(game, trick.getWinningCard().getSuit()) >= 0) {
            index = findMiddleHigh(game, trick.getWinningCard().getSuit());
        }

        if (index == -1){
            if (find(12, 3) >= 0){
                index = find(12, 3);
            } else if (find(14, 3) >= 0){
                index = find(14, 3);
            } else if (find(13, 3) >= 0){
                index = find(13, 3);
            } else if (findHighest(2) >= 0){
                index = findHighest(2);
            } else {
                index = findHighest();
            }
        }

        Card pick = removeCard(index);
        trick.update(NUM, pick);
        game.updateHeartsAndQueen(pick);
        return pick;
    }

    public int findLowest(int suit){
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < getCurrentSize(); i++){
            if (getCard(i).getSuit() == suit) {
                if (getCard(i).getNum() < min) {
                    min = getCard(i).getNum();
                    index = i;
                }
            }
        }
        return index;
    }

    private int findLowest(Game game){
        int index = -1;
        int min = Integer.MAX_VALUE;
        if (game.getHearts()){
            for (int i = 0; i < getCurrentSize(); i++){
                if (getCard(i).getNum() < min) {
                    min = getCard(i).getNum();
                    index = i;
                }
            }
        } else {
            for (int i = 0; i < getCurrentSize(); i++){
                if (getCard(i).getSuit() != 2) {
                    if (getCard(i).getNum() < min) {
                        min = getCard(i).getNum();
                        index = i;
                    }
                }
            }
        }
        return index;
    }

    private int findHighest(int suit){
        int index = -1;
        int max = -1;
        for (int i = 0; i < getCurrentSize(); i++){
            if (getCard(i).getSuit() == suit) {
                if (getCard(i).getNum() > max) {
                    max = getCard(i).getNum();
                    index = i;
                }
            }
        }
        return index;
    }

    private int findCount(int suit){
        int count = 0;
        for (int i = 0; i < getCurrentSize(); i++){
            if (getCard(i).getSuit() == suit) count++;
        }
        return count;
    }


    private int find(int suit, int num){
        for (int i = 0; i < getCurrentSize(); i++){
            if (getCard(i).getSuit() == suit && getCard(i).getNum() == num)
                return i;
        }
        return -1;
    }

    private int findLastHigh(int suit){
        int index = findHighest(suit);
        if (index != -1 && getCard(index).getNum() != 12){
            return index;
        } else {
            int lastHigh = -1;
            int max = -1;
            for (int i = 0; i < getCurrentSize(); i++){
                if (getCard(i).getSuit() == suit && i != index) {
                    if (getCard(i).getNum() > max) {
                        max = getCard(i).getNum();
                        lastHigh = i;
                    }
                }
            }
            return lastHigh;
        }
    }

    private int findHighestBelow(Card winningCard){
        int index = findHighest(winningCard.getSuit());
        while (index >= 0 && index < getCurrentSize()){
            if (getCard(index).getSuit() != winningCard.getSuit()) break;
            if (getCard(index).getNum() < winningCard.getNum()){
                return index;
            }
            index++;
        }
        return -1;
    }

    private int findMiddleHigh(Game game, int suit){
        int index = findHighest(suit);

        if (!game.getQueenOfSpades() && suit == 3){
            while (index >= 0 && index < getCurrentSize()){
                if (getCard(index).getSuit() != suit) break;
                if (getCard(index).getNum() < 11){
                    return index;
                }
                index++;
            }
        }

        return findHighest(suit);
    }

    private int findHighest(){
        int index = -1;
        int max = -1;
        for (int i = 0; i < getCurrentSize(); i++) {
                if (getCard(i).getNum() > max) {
                    max = getCard(i).getNum();
                    index = i;
                }
            }

        return index;
    }
}
