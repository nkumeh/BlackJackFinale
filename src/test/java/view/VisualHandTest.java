package view;

import model.Card;
import model.Hand;
import model.Name;
import model.Suit;
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
        Hand smallHand = new Hand(2);
        smallHand.add(new Card(Suit.DIAMONDS, Name.TWO));
        smallHand.add(new Card(Suit.CLUBS, Name.ACE));
        blackjackHand = new VisualHand(smallHand, true);

        Hand hand2 = new Hand(5);
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
            |XXXXX|     |XXXXX|     
            |XXXXX|     |XXXXX|     
            |XXXXX|     |XXXXX|     
            +-----+     +-----+     
                """;
        assertEquals(expected, dealerHand.toString());
    }

    @Test
    public void testToStringForFiveInvisibleCards() {
        String expected = """
             +-----+     +-----+     +-----+     +-----+     +-----+
             |XXXXX|     |XXXXX|     |XXXXX|     |XXXXX|     |XXXXX|
             |XXXXX|     |XXXXX|     |XXXXX|     |XXXXX|     |XXXXX|
             |XXXXX|     |XXXXX|     |XXXXX|     |XXXXX|     |XXXXX|
             +-----+     +-----+     +-----+     +-----+     +-----+
                        """;
        assertEquals(expected, invisiblePokerHand.toString());
    }
}