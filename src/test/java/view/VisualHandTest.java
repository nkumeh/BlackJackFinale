package view;

import model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VisualHandTest {

    private VisualHand blackjackHand;
    private VisualHand pokerHand;
    private VisualHand dealerHand;
    private VisualHand invisiblePokerHand;

    @Before
    public void setUp() {
        Hand smallHand = new Hand();
        smallHand.add(new Card(Suit.DIAMONDS, Name.TWO));
        smallHand.add(new Card(Suit.CLUBS, Name.ACE));
        blackjackHand = new VisualHand(smallHand, true);

        Hand hand2 = new Hand();
        hand2.add(new Card(Suit.HEARTS, Name.QUEEN));
        hand2.add(new Card(Suit.SPADES, Name.KING));
        hand2.add(new Card(Suit.CLUBS, Name.TWO));
        hand2.add(new Card(Suit.SPADES, Name.ACE));
        hand2.add(new Card(Suit.DIAMONDS, Name.TEN));
        pokerHand = new VisualHand(hand2, true);

        dealerHand = new VisualHand(smallHand, false);
        invisiblePokerHand = new VisualHand(hand2, false);
    }

    @Test
    public void testToStringForTwoVisibleCards() {
        String expected = """
                +-----+     +-----+     
                |♢    |     |♣    |     
                |  2  |     | Ace |     
                |    ♢|     |    ♣|     
                +-----+     +-----+     
                """;
        assertEquals(expected, blackjackHand.toString());
    }

    @Test
    public void testToStringForFiveVisibleCards() {
        String expected = """
                +-----+     +-----+     +-----+     +-----+     +-----+
                |♡    |     |♠    |     |♣    |     |♠    |     |♢    |
                |Queen|     |King |     |  2  |     | Ace |     |  10 |
                |    ♡|     |    ♠|     |    ♣|     |    ♠|     |    ♢|
                +-----+     +-----+     +-----+     +-----+     +-----+
                """;
        assertEquals(expected, pokerHand.toString());
    }

    @Test
    public void testToStringForInvisibleCards() {
        String expected = """
                +-----+     +-----+     
                |XXXXX|     |♣    |     
                |XXXXX|     | Ace |     
                |XXXXX|     |    ♣|     
                +-----+     +-----+     
                """;
        assertEquals(expected, dealerHand.toString());
    }

    @Test
    public void testToStringForFiveInvisibleCards() {
        String expected = """
                +-----+     +-----+     +-----+     +-----+     +-----+
                |XXXXX|     |♠    |     |♣    |     |♠    |     |♢    |
                |XXXXX|     |King |     |  2  |     | Ace |     |  10 |
                |XXXXX|     |    ♠|     |    ♣|     |    ♠|     |    ♢|
                +-----+     +-----+     +-----+     +-----+     +-----+
                """;
        assertEquals(expected, invisiblePokerHand.toString());
    }
}