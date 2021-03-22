import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {

    private Deck completeDeck;
    private Hand player1Hand;
    private Hand player2Hand;

    @Before
    public void setUp() {
        completeDeck = new Deck();
        player1Hand = new Hand();
        player2Hand = new Hand(5);
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, player1Hand.size());
        assertEquals(0, player1Hand.getHandSize());
    }

    @Test
    public void testHandSizeConstructor() {
        assertEquals(0, player2Hand.size());
        assertEquals(5, player2Hand.getHandSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandSizeConstructorThrowsExceptionForInvalidSize() {
        new Hand(-2);
    }

    /**
     * This test checks the getter method for the maxHandSize.
     */
    @Test
    public void testGetMaxHandSize() {
        assertEquals(0, player1Hand.getHandSize());
        assertEquals(5, player2Hand.getHandSize());
    }

    /**
     * This test checks that the setHandSize method accurately
     * sets the hand size.
     */
    @Test
    public void testSetMaxHandSize() {
        assertEquals(0, player1Hand.getHandSize());
        assertEquals(5, player2Hand.getHandSize());
        player1Hand.setHandSize(10);
        player2Hand.setHandSize(52);
        assertEquals(10, player1Hand.getHandSize());
        assertEquals(52, player2Hand.getHandSize());
    }

    /**
     * This test checks that you cannot set the maxHandSize to a
     * negative size.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetMaxHandSizeThrowsExceptionForNegativeSize() {
        player2Hand.setHandSize(-2);
    }

    /**
     * This test checks that you cannot set the maxHandSize to an
     * invalid positive size.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetMaxHandSizeThrowsExceptionForInvalidSize() {
        player1Hand.setHandSize(100);
    }

    /**
     * This test checks that you cannot add more cards to a hand
     * than the maxHandSize.
     */
    @Test(expected = IllegalStateException.class)
    public void testAddCardThrowsExceptionWhenDeckIsFull() {
        player1Hand.setHandSize(4);
        for (int i = 0; i < 5; i++) {
            player1Hand.add(completeDeck.takeTopCard());
        }
    }

    /**
     * This test checks that a card that is added to a hand
     * is properly removed from the deck.
     */
    @Test
    public void testAddToHandRemovesFromDeck() {
        for (int i = 0; i < player1Hand.maxHandSize; i++) {
            Card topCard = completeDeck.takeTopCard();
            player2Hand.add(topCard);
            assertTrue(player1Hand.hasCard(topCard));
            assertFalse(completeDeck.hasCard(topCard));
        }
    }

    /**
     * Technically this test shouldn't be here because the logic is identical to the parent class.
     * However, the assignment directs us to test that the sorting works for a hand, so I've included
     * it here as well.
     */
    @Test
    public void testSortingOnHand() {
        player1Hand.setHandSize(4);
        player1Hand.add(new Card(Suit.DIAMONDS, Name.TWO));
        player1Hand.add(new Card(Suit.CLUBS, Name.THREE));
        player1Hand.add(new Card(Suit.HEARTS, Name.TWO));
        player1Hand.add(new Card(Suit.SPADES, Name.THREE));

        // Cards added in sorted order
        player2Hand.add(new Card(Suit.DIAMONDS, Name.TWO));
        player2Hand.add(new Card(Suit.HEARTS, Name.TWO));
        player2Hand.add(new Card(Suit.CLUBS, Name.THREE));
        player2Hand.add(new Card(Suit.SPADES, Name.THREE));
        assertNotEquals(player2Hand.getDeck(), player1Hand.getDeck());
        player1Hand.sort();
        assertEquals(player2Hand.getDeck(), player1Hand.getDeck());
    }



}
