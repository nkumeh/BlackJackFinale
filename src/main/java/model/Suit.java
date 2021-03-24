package model;

/**
 * An enum that represents the suit of a card. Can be Clubs, Diamonds, Hearts,
 * Spades.
 */
public enum Suit {
    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    HEARTS("Hearts"),
    SPADES("Spades");

    private final String label;

    /**
     * Constructor for Suit enum.
     * @param label String representing card suit
     */
    Suit(String label) {
        this.label = label;
    }

    /**
     * Getter for label
     * @return string representing label
     */
    public String getLabel() {
        return this.label;
    }
}
