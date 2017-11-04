package Project2;



public class GroupOfCards {
    private Card[] cards;
    private int currentSize = 0;

    public GroupOfCards(int number){
        this.cards = new Card[number];
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public Card getCard(int i){
        return cards[i];
    }

    public void addCard(Card card){
        cards[currentSize] = card;
        currentSize += 1;
    }

    public Card removeCard(int index){
        Card pick = cards[index];
        for (int i = index; i < cards.length - 1; i++){
            cards[i] = cards[i + 1];
        }
        currentSize -= 1;
        return pick;

    }

    public void display(){
        for (int i = 0; i < cards.length; i++){
            if (cards[i] != null) {
                cards[i].display();
            }
        }
    }
}
