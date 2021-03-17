import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A Deck class that stores an ArrayList of Card objects. The default constructor
 * creates objects that represent the standard 52 cards.
 */
public class Deck {
    private final int MAX_CARDS_IN_DECK = 52;
    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
        for (Suit suit : Suit.values()) {
            for (Name name : Name.values()) {
                this.deck.add(new Card(suit, name));
            }
        }
    }

    /**
     * This constructor takes an ArrayList of Cards and
     * Creates an instance of a Deck with those cards.
     * This is primarily used for testing.
     * @param originalDeck an ArrayList of Cards to create a
     *        deck object from.
     */
    public Deck(ArrayList<Card> originalDeck) {
        this.deck = new ArrayList<Card>();
        for (int i = 0; i < originalDeck.size(); i++) {
            this.deck.add(originalDeck.get(i));
        }
    }

    /**
     * This is the copy constructor which is primarily used for testing.
     * @param originalDeck an instance of a Deck class.
     */
    public Deck(Deck originalDeck) {
        this.deck = new ArrayList<Card>(originalDeck.getDeck());
    }

    /**
     * This method is used for testing. Currently package private
     * because I'm not sure it needs greater visibility than that.
     * @return an array of the
     */
    ArrayList<Card> getDeck() {
        ArrayList<Card> deckCopy = new ArrayList<>(deck);
        return deckCopy;
    }

    public void printDeck() {
        for (Card card : this.deck) { System.out.println(card); }
    }

    public void shuffle() {
        // code
    }

    public void add(Card cardToAdd) {
        // code
    }

    private boolean isFull() {
        return this.deck.size() >= MAX_CARDS_IN_DECK;
    }

    public Card remove() {
        // code goes here
        // ignore code below, java wouldnt compile if i didnt add it
        return new Card(Suit.DIAMONDS, Name.JACK);
    }

    private boolean isEmpty() {
        return this.deck.size() == 0;
    }

    /**
     * This method sorts cards first by the name and then the suit.
     * It sorts suits alphabetically: clubs, diamonds, hearts, and spades.
     * This is a destructive method. It alters the actual deck object.
     * @throws IllegalStateException if the deck is empty.
     */
    public void sort() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot sort an empty Deck");
        }
        Comparator<Card> compareByValueAndSuit = Comparator
                .comparing(Card::getName)
                .thenComparing(Card::getSuit);
        Collections.sort(deck, compareByValueAndSuit);
    }

    /**
     * This method counts the size of a current deck of cards.
     * @return the number of cards in the deck as an int.
     */
    public int size() {
        return this.deck.size();
    }

    public static void main(String[] args) {
        Deck testDeck = new Deck();
        testDeck.printDeck();
        testDeck.sort();
        System.out.println(testDeck.getDeck().toString());
    }
}
