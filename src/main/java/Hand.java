import java.util.ArrayList;

/**
 * This class represents a card hand. It inherits from the deck class.
 *
 * This class utilizes the methods in the parent class.
 *
 * This class includes two constructors:
 *     The no-argument constructor creates an empty hand with a handSize initialized to zero.
 *     The constructor that allows you to set the handSize.
 */

public class Hand extends Deck {

    int handSize;

    /**
     * This constructor creates an empty hand with 0 cards.
     */
    public Hand() {
        super(new ArrayList<>());
        handSize = 0;
    }

    /**
     * This constructor creates an empty hand with 0 cards.
     */
    public Hand(Deck deck, int handSize) {
        super(new ArrayList<>());
        if (isValidHandSize(handSize) && handSize <= deck.size()) {
            for (int i = 0; i < handSize; i++) {
                this.add(deck.takeTopCard());
            }
        }
    }

    /**
     * Getter method for the number of cards in the hand.
     * @return current handSize (an int).
     */
    public int getHandSize() {
        return this.getDeck().size();
    }

    /**
     * This method checks whether the handSize is valid
     * @param handSize an integer
     * @throws IllegalArgumentException if the maximum number of cards is less than 1 or greater than
     *                                  the maximum number of cards in a deck.
     */
    private boolean isValidHandSize(int handSize) throws IllegalArgumentException {
        if (handSize >= 0 && handSize <= super.MAX_CARDS_IN_DECK) {
            return true;
        }
        throw new IllegalArgumentException("Invalid Hand Size: " + handSize);
    }

    public static void main(String[] args) {
        // Create and shuffle a new deck.
        Deck myLuckyDeck = new Deck();
        myLuckyDeck.shuffle();

        // Create two hands with 5 cards each. The assert statements
        // check that the card has been removed from the deck.
        int handSize = 5;
        Hand myHand = new Hand(myLuckyDeck, handSize);
        Hand yourHand = new Hand(myLuckyDeck, handSize);
        for (int i = 0; i < handSize; i++) {
            assert(!myLuckyDeck.hasCard(myHand.getCard(i)));
            assert(myHand.size() == i + 1);

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

        // Sort is being tested in the HandTest.java file.
    }
}