import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class VisualCard {

    private String unicodeSuit;
    private String cardValue;
    private boolean isVisible;


    /**
     * This constructor is intended for visible cards that will show the suit
     * and value.
     *
     * @param card the instance of Card to visualize.
     */
    public VisualCard(Card card) {
        setUnicodeSuit(card.getSuit());
        setCardValue(card.getName());
        this.isVisible = true;
    }

    /**
     * This constructor creates a new card that can be hidden and unhidden.
     *
     * @param card      the card object to create a visual representation of.
     * @param isVisible a boolean - true if the card information should be visualized, else False.
     */
    public VisualCard(Card card, Boolean isVisible) {
        setUnicodeSuit(card.getSuit());
        setCardValue(card.getName());
        this.isVisible = isVisible;
    }

    /**
     * The toString method returns a formatted String that represents a visual Card.
     *
     * @return a formatted String for a particular card instance.
     */
    @Override
    public String toString() {
        if (this.isVisible) {
            return formatVisibleCard();
        } else {
            return formatInvisibleCard();
        }
    }

    /**
     * This setter allows for a card to be made invisible or visible once it's created.
     *
     * @param visible true if the card suit and value should be visible, else false.
     */
    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    /**
     * This getter tells you if the card should be visible or invisible.
     *
     * @return true if the card is visible, else false.
     */
    public boolean getVisible() {
        return this.isVisible;
    }

    /**
     * The setUnicodeValue gets the value for a particular card based on its
     * suit and value.
     *
     * @param suit of the card.
     */
    void setUnicodeSuit(Suit suit) {

        switch (suit) {
            case CLUBS -> this.unicodeSuit = "\u2663";
            case DIAMONDS -> this.unicodeSuit = "\u2662";
            case HEARTS -> this.unicodeSuit = "\u2661";
            case SPADES -> this.unicodeSuit = "\u2660";
            default -> this.unicodeSuit = "Invalid suit.";
        }
    }

    /**
     * The setCardValue method sets the value of the card to print.
     *
     * @param name the Card's name
     */
    void setCardValue(Name name) {
        if (isFaceCard(name)) {
            this.cardValue = name.getLabel();
        } else {
            this.cardValue = String.valueOf(name.getBlackjackValue());
        }
    }

    /**
     * This private helper method determines if a card is a "Face" card. (ex. Ace, Jack, Queen, or King).
     *
     * @param name the Card's name
     * @return true if the card is a Face Card, else false.
     */
    private boolean isFaceCard(Name name) {
        return name.equals(Name.ACE) || name.equals(Name.JACK) || name.equals(Name.QUEEN) || name.equals(Name.KING);
    }

    /**
     * This method creates a string representation of a card object.
     *
     * @return a formatted string of a card.
     */
    private String formatVisibleCard() {
        String border = "+-----+\n";
        String formattedCard = border + formatSuit(1) +
                formatCardValue() +
                formatSuit(3) +
                border;
        return formattedCard;
    }

    /**
     * This method creates a representation of a face-down card.
     *
     * @return a formatted string for a face-down card.
     */
    private String formatInvisibleCard() {
        return """
                +-----+
                |XXXXX|
                |XXXXX|
                |XXXXX|
                +-----+
                """;
    }

    /**
     * This private helper method formats the suit depending on the row
     * the suit is to be displayed in
     *
     * @param row the row to display the suit in. Valid rows are 1 and 3.
     * @return a formatted string for the row.
     * @throws IllegalArgumentException if the row is invalid (not 1 or 3).
     */
    String formatSuit(int row) throws IllegalArgumentException {
        StringBuilder formattedSuit = new StringBuilder("|");
        byte[] bytes = this.unicodeSuit.getBytes(StandardCharsets.UTF_8);
        String suitToPrint = new String(bytes, StandardCharsets.UTF_8);
        if (row != 1 && row != 3) {
            throw new IllegalArgumentException("Suit is only printed on rows 1 and 3.");
        } else if (row == 1) {
            formattedSuit.append(suitToPrint);
            formattedSuit.append("    |\n");
        } else {
            formattedSuit.append("    ");
            formattedSuit.append(suitToPrint);
            formattedSuit.append("|\n");
        }
        return formattedSuit.toString();
    }

    /**
     * This private method determines the spacing for the card.
     *
     * @return a formatted string for the value.
     */
    String formatCardValue() {
        StringBuilder formattedValue = new StringBuilder();
        switch (this.cardValue.length()) {
            case 1 -> formattedValue.append("|  ").append(cardValue).append("  |\n");
            case 2 -> formattedValue.append("|  ").append(cardValue).append(" |\n");
            case 3 -> formattedValue.append("| ").append(cardValue).append(" |\n");
            case 4 -> formattedValue.append("|").append(cardValue).append(" |\n");
            default -> formattedValue.append("|").append(cardValue).append("|\n");
        }
        return formattedValue.toString();
    }

}
