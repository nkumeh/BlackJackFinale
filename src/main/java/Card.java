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

    /**
     * Boolean method that returns if card is an ACE. Can be used in
     * blackjack game for readability purposes to treat ACE as value 1 or 11.
     * @return boolean - true if ACE, false otherwise
     */
    public boolean isAce() {
        return this.name == Name.ACE;
    }

    /**
     * toString method for Card.
     * @return String e.g. TWO of HEARTS, ACE of CLUBS
     */
    @Override
    public String toString() {
        return name + " of " + suit;
    }

    /**
     * Equals method for Card.
     * @param o another object
     * @return True if suit and name are both equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        return suit == card.suit && name == card.name;
    }
}
