/**
 * An enum that represents the name of a card. A card can be any value in the
 * range 2-10, as well as face cards Jack, Queen, King, and Ace. Ace takes
 * default value as 11. However, it can also be a 1.
 */
public enum Name {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);

    private final int blackjackValue;

    /**
     * Constructor for Name enum. Takes blackjack card values as constants.
     * @param value int representing card value
     */
    Name(int value) {
        this.blackjackValue = value;
    }

    /**
     * Getter for value
     * @return int representing card value
     */
    public int getBlackjackValue() {
        return this.blackjackValue;
    }
}
