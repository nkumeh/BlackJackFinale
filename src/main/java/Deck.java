import java.util.ArrayList;
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
        this.deck = new ArrayList<>();
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
     * @param originalDeckList an ArrayList of Cards to create a
     * deck object from.
     */
    public Deck(ArrayList<Card> originalDeckList) {
        this.deck = new ArrayList<>(originalDeckList);
    }

    /**
     * This is the copy constructor which is primarily used for testing.
     * @param originalDeck an instance of a Deck class.
     */
    public Deck(Deck originalDeck) {
        this.deck = new ArrayList<>(originalDeck.getDeck());
    }

    /**
     * This method is used for testing. Currently package private
     * because I'm not sure it needs greater visibility than that.
     * @return an ArrayList of the cards in the deck.
     */
    ArrayList<Card> getDeck() {
        return new ArrayList<>(deck);
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
     * @param index the index to return the card from
     * @return the card at the requested index
     * @throws IllegalStateException if the deck is empty.
     */
    public Card getCard(int index) throws IllegalStateException {
        if (!deck.isEmpty()) {
            return this.deck.get(index);
        }
        throw new IllegalStateException("The deck is empty.");
    }

    /**
     * This method is used to shuffle the cards in the deck
     */
    public void shuffle() {
        ArrayList<Card> shuffledDeck = new ArrayList<>();
        Random randomise = new Random();
        while(!deck.isEmpty()) {
            int random = randomise.nextInt(deck.size());
            shuffledDeck.add(deck.remove(random));
        }
        this.deck = shuffledDeck;
    }

    /**
     * This method is used to add a card onto the deck
     * @param newCard an instance of the card class
     * @throws IllegalStateException if the deck is full.
     * @throws IllegalArgumentException if card is already in the deck.
     */
    public void add(Card newCard) throws IllegalStateException, IllegalArgumentException {
        if (isFull()) {
            throw new IllegalStateException("This deck is already full");
        }
        else if (hasCard(newCard)) {
            throw new IllegalArgumentException("This card is already in the Deck");
        }
        this.deck.add(newCard);
    }

    /**
     * This method is used to remove the first card stored in the deck
     * @return the first card in the deck
     */
    public Card takeTopCard() {
        Card firstCard = getCard(0);
        if (firstCard.equals(this.deck.get(0))) {
            this.deck.remove(0);
        }
        return firstCard;
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
     * This method checks to see if a card is already in the Deck.
     * It's package private for testing purposes.
     * @param otherCard an instance of a Card.
     * @return true if the card is already present in the deck, else false.
     */
    boolean hasCard(Card otherCard) {
        return this.deck.contains(otherCard);
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
    }
}
