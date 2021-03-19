import java.util.ArrayList;

/**
 * This class represents a card hand. It inherits from the deck class.
 *
 * This class utilizes the methods in the parent class.
 *
 * This class includes two constructors:
 *     The no-argument constructor creates an empty hand with a maxHandSize initialized to zero.
 *     The constructor that allows you to set the maxHandSize.
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
        setHandSize(maxCards);
    }

    /**
     * Getter method for the maxHandSize, the most cards one can add to a hand.
     * @return maxHandSize (an int).
     */
    public int getHandSize() {
        return this.maxHandSize;
    }

    /**
     * This method allows you to set the maximum number of cards in a hand.
     * @param newHandSize an integer
     * @throws IllegalArgumentException if the maximum number of cards is less than 1 or greater than
     *                                  the maximum number of cards in a deck.
     */
    public void setHandSize(int newHandSize) throws IllegalArgumentException {
        if (newHandSize > 0 && newHandSize <= super.MAX_CARDS_IN_DECK) {
            this.maxHandSize = newHandSize;
        }
        else {
            throw new IllegalArgumentException("Invalid Hand Size: " + newHandSize);
        }
    }

    @Override
    boolean isFull() {
        return this.size() >= maxHandSize;
    }


    public static void main(String[] args) {
        // Create and shuffle a new deck.
        Deck myLuckyDeck = new Deck();
        myLuckyDeck.shuffle();

        // Create two hands with 5 cards each. The assert statements
        // check that the card has been removed from the deck.
        int handSize = 5;
        Hand myHand = new Hand(handSize);
        Hand yourHand = new Hand(handSize);
        for (int i = 0; i < handSize; i++) {
            myHand.add(myLuckyDeck.takeTopCard());
            assert(!myLuckyDeck.hasCard(myHand.getCard(i)));
            assert(myHand.size() == i + 1);

            yourHand.add(myLuckyDeck.takeTopCard());
            assert(!myLuckyDeck.hasCard(yourHand.getCard(i)));
            assert(yourHand.size() == i + 1);
        }
        assert(myLuckyDeck.size() == 42);

        // Prints the hands and the Decks
        myHand.printDeck();
        yourHand.printDeck();
        myLuckyDeck.printDeck();

        // Returns the cards to the deck. The assert statements
        // check that the cards are no longer a part of the hand
        // and that they are in the deck
        for (int i = 0; i < handSize; i++) {
            Card myTopCard = myHand.takeTopCard();
            assert(!myLuckyDeck.hasCard(myTopCard));
            myLuckyDeck.add(myTopCard);
            assert(myLuckyDeck.hasCard(myTopCard));
            assert(!myHand.hasCard(myTopCard));

            Card yourTopCard = yourHand.takeTopCard();
            assert(!myLuckyDeck.hasCard(yourTopCard));
            myLuckyDeck.add(yourTopCard);
            assert(myLuckyDeck.hasCard(yourTopCard));
            assert(!yourHand.hasCard(yourTopCard));
        }
        assert(myLuckyDeck.size() == 52);
        myHand.printDeck();
        yourHand.printDeck();
        myLuckyDeck.printDeck();

        //Sort is being tested in the HandTest.java file.
    }
}