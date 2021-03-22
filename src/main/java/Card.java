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
        this.setSuit(suit);
        this.setName(name);
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
        return this.suit;
    }

    /**
     * Getter for Name.
     * @return enum Name - TWO-TEN, face cards JACK, QUEEN, KING, and ACE
     */
    public Name getName() {
        return this.name;
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
     * Method to return the card value. Returns a 1 for ACE
     * @return int - card value
     */
    public int getValue() {
        return this.name.getBlackjackValue();
    }

    /**
     * toString method for Card.
     * @return String e.g. TWO of HEARTS, ACE of CLUBS
     */
    @Override
    public String toString() {
        return this.name.getLabel() + " of " + this.suit.getLabel();
    }

    /**
     * Equals method for Card.
     * @param o another object
     * @return True if suit and name are both equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        return this.suit == card.suit && this.name == card.name;
    }
}
