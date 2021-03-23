

/**
 * This class creates a visual representation of a Hand.
 */

public class VisualHand {

    private Hand hand;
    private int handSize;
    private VisualCard[] visualCards;
    private String formattedHand;

    public VisualHand(Hand hand) {
        this.hand = hand;
        this.handSize = hand.getHandSize();
        visualCards = new VisualCard[handSize];
        createVisualizedCards();
        formatHand();
    }

    @Override
    public String toString() {
        return this.formattedHand;
    }

    private void createVisualizedCards() {
        for (int i = 0; i < this.handSize; i++) {
            visualCards[i] = new VisualCard(hand.getCard(i));
        }
    }

    private void formatHand() {
        String cardSeparator = "     ";
        String border = "+-----+";
        StringBuilder borderBuilder = new StringBuilder();
        StringBuilder row1Builder = new StringBuilder();
        StringBuilder row2Builder = new StringBuilder();
        StringBuilder row3Builder = new StringBuilder();
        for (int i = 0; i < this.handSize; i++) {
            borderBuilder.append(border);
            borderBuilder.append(cardSeparator);
            row1Builder.append(visualCards[i].formatSuit(1));
            row1Builder.append(cardSeparator);
            row2Builder.append(visualCards[i].formatCardValue());
            row2Builder.append(cardSeparator);
            row3Builder.append(visualCards[i].formatSuit(3));
            row3Builder.append(cardSeparator);
        }
        borderBuilder.append("\n");
        row1Builder = formatRow(row1Builder);
        row2Builder = formatRow(row2Builder);
        row3Builder = formatRow(row3Builder);

        StringBuilder finalHandView = new StringBuilder(borderBuilder.toString());
        finalHandView.append(row1Builder.toString());
        finalHandView.append(row2Builder.toString());
        finalHandView.append(row3Builder.toString());
        finalHandView.append(borderBuilder.toString());
        this.formattedHand = finalHandView.toString();
    }

    private StringBuilder formatRow(StringBuilder row) {
        String newRow = row.toString().replace("\n", "");
        StringBuilder newSBRow = new StringBuilder(newRow);
        newSBRow.append("\n");
        return newSBRow;
    }

    public static void main(String[] args) {
        Hand myHand = new Hand(4);
        myHand.add(new Card(Suit.DIAMONDS, Name.TWO));
        myHand.add(new Card(Suit.CLUBS, Name.ACE));
        myHand.add(new Card(Suit.HEARTS, Name.QUEEN));
        myHand.add(new Card(Suit.SPADES, Name.KING));
        VisualHand visualizedHand = new VisualHand(myHand);
        System.out.println(visualizedHand);
    }
}