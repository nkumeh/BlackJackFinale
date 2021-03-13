import java.util.Objects;

/**
 * A Card class. Every card has a suit (e.g., Clubs, Diamonds, Hearts, Spades)
 * and name (e.g., Ace, 2, 10, Jack).
 */

public class Card {
    private Suit suit;
    private Name name;

    /**
     * Constructor for a Card. A card is immutable as only one should exist in
     * a deck of cards. Uses private setters. As both parameters are
     * @param suit enum Suit - Clubs, Diamonds, Hearts, Spades
     * @param name enum Name - TWO-TEN, face cards JACK, QUEEN, KING, and ACE
     */
    public Card(Suit suit, Name name) {
        setSuit(suit);
        setName(name);
    }

    /**
     * Private setter for Suits used in constructor. Private accessor to preserve
     * immutability.
     * @param suit enum Suit - Clubs, Diamonds, Hearts, Spades
     */
    private void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     * Private setter for Suits used in constructor. Private accessor to preserve
     * immutability.
     * @param name enum Name - TWO-TEN, face cards JACK, QUEEN, KING, and ACE
     */
    private void setName(Name name) {
        this.name = name;
    }

    /**
     * Getter for Suit.
     * @return enum Suit - Clubs, Diamonds, Hearts, Spades
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Getter for Name.
     * @return enum Name - TWO-TEN, face cards JACK, QUEEN, KING, and ACE
     */
    public Name getName() {
        return name;
    }

    public boolean isAce() {
        return this.name == Name.ACE;
    }

    @Override
    public String toString() {
        return name + " of " + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        return suit == card.suit && name == card.name;
    }


    public static void main(String[] args) {
        Card myCard = new Card(Suit.CLUBS, Name.ACE);
        System.out.println(myCard.isAce());
        System.out.println(myCard.name.getBlackjackValue());
    }

}
