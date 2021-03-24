package model;

/**
 * An enum that represents the name of a card. A card can be any value in the
 * range 2-10, as well as face cards Jack, Queen, King, and Ace. Ace takes
 * default value as 11. However, it can also be a 1.
 */
public enum Name {
    TWO(2, "Two"),
    THREE(3, "Three"),
    FOUR(4, "Four"),
    FIVE(5, "Five"),
    SIX(6, "Six"),
    SEVEN(7, "Seven"),
    EIGHT(8, "Eight"),
    NINE(9, "Nine"),
    TEN(10, "Ten"),
    JACK(10, "Jack"),
    QUEEN(10, "Queen"),
    KING(10, "King"),
    ACE(1, "Ace");

    private final int blackjackValue;
    private final String label;

    /**
     * Constructor for Name enum. Takes blackjack card values as constants.
     * @param value int representing card value
     * @param label String representing label
     */
    Name(int value, String label) {
        this.blackjackValue = value;
        this.label = label;
    }

    /**
     * Getter for value
     * @return int representing card value
     */
    public int getBlackjackValue() {
        return this.blackjackValue;
    }

    /**
     * Getter for label
     * @return string representing label
     */
    public String getLabel() {
        return this.label;
    }
}
