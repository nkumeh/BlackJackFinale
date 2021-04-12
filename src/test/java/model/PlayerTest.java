package model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A JUnit4 test class for Player class.
 */

public class PlayerTest {
    private Player player1;

    @Before
    public void setUp() {
        player1 = new Player("Maria Konnikova");
    }

    @Test
    public void testGetName() {
        assertEquals("Maria Konnikova", player1.getName());
    }

    @Test
    public void testHasBlackjack() {
        assertFalse(player1.hasBlackjack());
        player1.getHand().add(new Card(Suit.HEARTS, Name.ACE));
        assertFalse(player1.hasBlackjack());
        player1.getHand().add(new Card(Suit.HEARTS, Name.TEN));
        assertTrue(player1.hasBlackjack());
    }

    @Test
    public void testHasBlackjackMultipleCards() {
        assertFalse(player1.hasBlackjack());
        player1.getHand().add(new Card(Suit.HEARTS, Name.TWO));
        player1.getHand().add(new Card(Suit.HEARTS, Name.TEN));
        player1.getHand().add(new Card(Suit.HEARTS, Name.THREE));
        player1.getHand().add(new Card(Suit.HEARTS, Name.FIVE));
        player1.getHand().add(new Card(Suit.HEARTS, Name.ACE));
        assertTrue(player1.hasBlackjack());
    }
}
