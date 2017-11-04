package Project2;

public class Deck extends GroupOfCards {
    public int totalCards = 52;

    public Deck(){
        super(52);
        for (int i = 0; i < 4; i++){
            for (int j = 2; j <= 14; j++){
                addCard(new Card(j, i));
            }
        }
    }

    public void shuffle(){
        for (int unshuffled = getCurrentSize(); unshuffled >= 1; unshuffled--){
            int index = (int)(Math.random() * unshuffled) ;
            Card pick = removeCard(index);
            addCard(pick);
        }
    }

    public Card dealCard(){
        return removeCard(0);
    }
}
