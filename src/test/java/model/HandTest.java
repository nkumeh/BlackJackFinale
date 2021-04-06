package model;

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
        player2Hand = new Hand(completeDeck, 5);
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, player1Hand.size());
        assertEquals(0, player1Hand.getHandSize());
    }

    @Test
    public void testHandSizeConstructor() {
        assertEquals(5, player2Hand.getHandSize());
        assertEquals(47, completeDeck.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandConstructorThrowsExceptionForNegativeSize() {
        new Hand(completeDeck, -2);
    }

    /**
     * This test checks that you cannot set the maxHandSize to an
     * invalid positive size.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testHandConstructorThrowsExceptionForInvalidSize() {
        new Hand(completeDeck, 100);
    }

    /**
     * This test checks the getter method for the maxHandSize.
     */
    @Test
    public void testGetHandSize() {
        assertEquals(0, player1Hand.getHandSize());
        assertEquals(5, player2Hand.getHandSize());
    }

    /**
     * This test checks that a card that is added to a hand
     * is properly removed from the deck.
     */
    @Test
    public void testAddToHandRemovesFromDeck() {
        for (int i = 0; i < player1Hand.getHandSize(); i++) {
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
        Hand newUnsortedHand = new Hand();
        newUnsortedHand.add(new Card(Suit.DIAMONDS, Name.TWO));
        newUnsortedHand.add(new Card(Suit.CLUBS, Name.THREE));
        newUnsortedHand.add(new Card(Suit.HEARTS, Name.TWO));
        newUnsortedHand.add(new Card(Suit.SPADES, Name.THREE));

        // Cards added in sorted order
        Hand newSortedHand = new Hand();
        newSortedHand.add(new Card(Suit.DIAMONDS, Name.TWO));
        newSortedHand.add(new Card(Suit.HEARTS, Name.TWO));
        newSortedHand.add(new Card(Suit.CLUBS, Name.THREE));
        newSortedHand.add(new Card(Suit.SPADES, Name.THREE));
        assertNotEquals(newSortedHand.getDeck(), newUnsortedHand.getDeck());
        newUnsortedHand.sort();
        assertEquals(newSortedHand.getDeck(), newUnsortedHand.getDeck());
    }
}
