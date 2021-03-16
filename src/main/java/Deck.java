import java.util.ArrayList;

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

    public void sort() {
        // code
    }

    public static void main(String[] args) {
        Deck testDeck = new Deck();
        testDeck.printDeck();
    }
}
