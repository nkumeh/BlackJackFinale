import java.util.ArrayList;

/**
 * This class represents a card hand. It inherits from the deck class.
 */

public class Hand extends Deck {

    int maxHandSize;

    /**
     * This constructor creates an empty hand with 0 cards.
     */
    public Hand() {
        super(new ArrayList<>());
        maxHandSize = 0;
    }

    /**
     * This constructor creates an empty hand with 0 cards.
     */
    public Hand(int maxCards) {
        super(new ArrayList<>());
    }

    public void setHandSize(int newHandSize) {
        if (isValidHandSize(newHandSize)) {
            this.maxHandSize = newHandSize;
        }
    }

    public static void main(String[] args) {
        Hand myHand = new Hand();
        System.out.println(myHand.size());
    }

    private boolean isValidHandSize(int handSize) {
        return handSize > 0 && handSize <= super.MAX_CARDS_IN_DECK;
    }
}
