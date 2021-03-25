package view;

import model.Hand;

/**
 * This class creates a visual representation of a Hand.
 */

public class VisualHand {

    private final Hand hand;
    private final int handSize;
    private final VisualCard[] visualCards;
    private String formattedHand;
    private final StringBuilder border;
    private StringBuilder row1;
    private StringBuilder row2;
    private StringBuilder row3;


    /**
     * This constructor creates a VisualHand object out of a Hand object.
     * It can be a hand of visible cards (that shows the card information) or
     * cards that do not show the card information (false).
     * @param hand A Hand object that contains cards. 
     * @param isVisible boolean true if the card information should be displayed, else false.
     */
    public VisualHand(Hand hand, Boolean isVisible) {
        this.hand = hand;
        this.handSize = hand.getHandSize();
        visualCards = new VisualCard[handSize];
        border = new StringBuilder();
        row1 = new StringBuilder();
        row2 = new StringBuilder();
        row3 = new StringBuilder();
        createVisualCardsFromHand(isVisible);
        addRowsToHand();
        formatRows();
        createFinalHandView();
    }

    /**
     * This method returns the complete, formatted String for a hand.
     *
     * @return the formatted string for a hand (that can be printed using System.out).
     */
    @Override
    public String toString() {
        return this.formattedHand;
    }

    /**
     * This method creates visual card objects for each card in a hand.
     *
     * @param visible a boolean True if the card information is visible, else false.
     */
    private void createVisualCardsFromHand(boolean visible) {
        for (int i = 0; i < this.handSize; i++) {
            visualCards[i] = new VisualCard(hand.getCard(i), visible);
        }
    }

    /**
     * This method adds the information for each card into each row.
     * It adds separation between the card objects.
     */
    private void addRowsToHand() {
        String cardSeparator = "     ";
        String border = "+-----+";
        for (int i = 0; i < this.handSize - 1; i++) {
            this.border.append(border);
            this.border.append(cardSeparator);
            this.row1.append(visualCards[i].formatSuit(1));
            this.row1.append(cardSeparator);
            this.row2.append(visualCards[i].formatCardValue());
            this.row2.append(cardSeparator);
            this.row3.append(visualCards[i].formatSuit(3));
            this.row3.append(cardSeparator);
        }
        this.border.append(border);
        this.row1.append(visualCards[this.handSize - 1].formatSuit(1));
        this.row2.append(visualCards[this.handSize - 1].formatCardValue());
        this.row3.append(visualCards[this.handSize - 1].formatSuit(3));
        this.border.append("\n");
    }

    /**
     * This method calls the helper function to format the newline characters
     * in each row with card specific information (rows 1, 2 and 3).
     */
    private void formatRows() {
        this.row1 = formatNewlinesInRow(this.row1);
        this.row2 = formatNewlinesInRow(this.row2);
        this.row3 = formatNewlinesInRow(this.row3);
    }

    /**
     * This helper method removes all of the existing newline characters from the
     * original VisualCard objects. It adds a newline at the end of the row.
     *
     * @param row the row to format.
     * @return a new StringBuilder row object.
     */
    private StringBuilder formatNewlinesInRow(StringBuilder row) {
        String newRow = row.toString().replace("\n", "");
        StringBuilder newSBRow = new StringBuilder(newRow);
        newSBRow.append("\n");
        return newSBRow;
    }

    /**
     * This method puts all of the StringBuilder objects together into a final
     * formatted String that can be called using the toString method.
     */
    private void createFinalHandView() {
        this.formattedHand = this.border.toString() + this.row1.toString() +
                this.row2.toString() +
                this.row3.toString() +
                this.border.toString();
    }

}