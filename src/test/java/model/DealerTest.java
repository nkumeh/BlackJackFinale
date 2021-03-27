package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * A JUnit4 test class for Dealer class.
 */
public class DealerTest {

    private Dealer dealer;
    private Deck deck;

    @Before
    public void setUp() {
        dealer = new Dealer();
        ArrayList<Card> emptyCardList = new ArrayList<>();
        deck = new Deck(emptyCardList);
        deck.add(new Card(Suit.DIAMONDS, Name.TEN));
        deck.add(new Card(Suit.CLUBS, Name.SIX));
        deck.add(new Card(Suit.HEARTS, Name.ACE));
        deck.add(new Card(Suit.SPADES, Name.TWO));
    }

    @Test
    public void testDealerCanHit() {
        // Initialize a deck of four cards
        assertEquals(0, dealer.getCurrentHandValue());
        assertEquals(0, dealer.getHand().size());
        // dealer took 1 card
        dealer.hit(deck);
        assertEquals(10, dealer.getCurrentHandValue());
        assertEquals(1, dealer.getHand().size());
        // dealer took a 2nd card
        dealer.hit(deck);
        assertEquals(16, dealer.getCurrentHandValue());
        assertEquals(2, dealer.getHand().size());
        // dealer took 3rd card; at 17, cannot hit anymore
        dealer.hit(deck);
        assertEquals(17, dealer.getCurrentHandValue());
        assertEquals(3, dealer.getHand().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testDealerCantHitWhenAtThreshold() {
        dealer.hit(deck);
        dealer.hit(deck);
        dealer.hit(deck);
        assertEquals(17, dealer.getCurrentHandValue());
        assertEquals(3, dealer.getHand().size());
        dealer.hit(deck);
    }
}
