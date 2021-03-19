import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
     * deck object from.
     */
    public Deck(ArrayList<Card> originalDeck) {
//        this.deck = new ArrayList<Card>();
//        for (int i = 0; i < originalDeck.size(); i++) {
//            this.deck.add(originalDeck.get(i));
//        }
        // modified the method to use the add all method from the
        // arrayList method glossary
        this.deck = new ArrayList<Card>();
        this.deck.addAll(originalDeck);
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
     * @return an ArrayList of the cards in the deck.
     */
    ArrayList<Card> getDeck() {
        ArrayList<Card> deckCopy = new ArrayList<>(deck);
        return deckCopy;
    }

    /**
     * This method is used to print the cards of the deck
     * and prints it out to the user
     */
    public void printDeck() {
        for (Card card : this.deck) { System.out.println(card); }
    }

    /**
     * This method is used to view the first card stored in the deck
     * @return value of the first card in the deck
     */
    public Card getCard(){
        if (!deck.isEmpty()) {
            return this.deck.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * This method is used to shuffle the cards in the deck
     */
    public void shuffle() {
        ArrayList<Card> temp = new ArrayList<Card>();
        Random randomise = new Random();
        while(!deck.isEmpty()) {
            int random = randomise.nextInt(deck.size());
            temp.add(deck.remove(random));
        }
        this.deck = temp;
    }

    /**
     * This method is used to add a card onto the deck
     * @param cardToAdd an instance of the card class
     */
    public void add(Card cardToAdd) {
        this.deck.add(cardToAdd);
    }

    /**
     * This method is used to remove the first card stored in the deck
     * @return value of the first card in the deck
     */
    public Card remove() {
        if (!deck.isEmpty()){
            return this.deck.remove(0);
        }
        else{
            return null;
        }
    }

    /**
     * Boolean methode to determine if the deck is full or not
     * @return true if the deck is full
     */
    private boolean isFull() {
        return this.deck.size() >= MAX_CARDS_IN_DECK;
    }

    /**
     * Boolean methode to determine if the deck is empty or not
     * @return true if the deck is empty
     */
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
        Comparator<Card> compareByNameAndSuit = Comparator
                .comparing(Card::getName)
                .thenComparing(Card::getSuit);
        deck.sort(compareByNameAndSuit);
        //Collections.sort(deck, compareByNameAndSuit);
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
