package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * A JUnit4 test class for AbstractPlayer class.
 */
public class AbstractPlayerTest {

    private Player player1;
    private Player player2;
    private Dealer dealer;

    @Before
    public void setUp() {
        player1 = new Player("Maria Konnikova");
        Hand startingHand = new Hand();
        startingHand.add(new Card(Suit.CLUBS, Name.EIGHT));
        startingHand.add(new Card(Suit.CLUBS, Name.ACE));
        player2 = new Player("Justin Bonomo", startingHand);
        dealer = new Dealer();
    }

    @Test
    public void testGetCurrentHandValueStartingHandValue() {
        assertEquals(0, player1.getCurrentHandValue());
        assertEquals(19, player2.getCurrentHandValue());
        assertEquals(0, dealer.getCurrentHandValue());
    }

    @Test
    public void testGetCurrentHandAddedCard() {
        Card aceOfSpades = new Card(Suit.SPADES, Name.ACE);
        player1.getHand().add(aceOfSpades);
        assertEquals(11, player1.getCurrentHandValue());
        player2.getHand().add(aceOfSpades);
        assertEquals(20, player2.getCurrentHandValue());
        dealer.getHand().add(aceOfSpades);
        assertEquals(11, dealer.getCurrentHandValue());
    }

    @Test
    public void testGetHand() {
        Hand startingHand = new Hand();
        startingHand.add(new Card(Suit.CLUBS, Name.EIGHT));
        startingHand.add(new Card(Suit.CLUBS, Name.ACE));
        Player tempPlayer = new Player("Temp", startingHand);
        assertEquals(startingHand, tempPlayer.getHand());
    }

    @Test
    public void testIsOver21() {
        assertFalse(player2.isOver21());
        player2.getHand().add(new Card(Suit.HEARTS, Name.TWO));
        // value is 21
        assertFalse(player2.isOver21());
        player2.getHand().add(new Card(Suit.HEARTS, Name.TEN));
        assertFalse(player2.isOver21());
        player2.getHand().add(new Card(Suit.HEARTS, Name.FOUR));
        assertTrue(player2.isOver21());
    }

    @Test
    public void testCalculateHandValue() {
        assertEquals(0, player1.getCurrentHandValue());
        player1.getHand().add(new Card(Suit.HEARTS, Name.ACE));
        assertEquals(11, player1.getCurrentHandValue());
        player1.getHand().add(new Card(Suit.HEARTS, Name.TWO));
        assertEquals(13, player1.getCurrentHandValue());
        player1.getHand().add(new Card(Suit.HEARTS, Name.FIVE));
        assertEquals(18, player1.getCurrentHandValue());
        player1.getHand().add(new Card(Suit.CLUBS, Name.ACE));
        assertEquals(19, player1.getCurrentHandValue());
        player1.getHand().add(new Card(Suit.CLUBS, Name.FIVE));
        assertEquals(14, player1.getCurrentHandValue());
        player1.getHand().add(new Card(Suit.CLUBS, Name.EIGHT));
        assertEquals(22, player1.getCurrentHandValue());
    }

    @Test
    public void testHandValueWithTwoAces() {
        assertFalse(player1.hasBlackjack());
        player1.getHand().add(new Card(Suit.HEARTS, Name.ACE));
        assertEquals(11, player1.getCurrentHandValue());
        player1.getHand().add(new Card(Suit.DIAMONDS, Name.ACE));
        assertEquals(12, player1.getCurrentHandValue());
        player1.getHand().add(new Card(Suit.HEARTS, Name.NINE));
        assertEquals(21, player1.getCurrentHandValue());
        assertTrue(player1.hasBlackjack());
    }

    @Test
    public void testHit() {
        ArrayList<Card> emptyCardList = new ArrayList<>();
        Deck partialDeck = new Deck(emptyCardList);
        // Initialize a deck of four cards
        partialDeck.add(new Card(Suit.DIAMONDS, Name.TEN));
        partialDeck.add(new Card(Suit.CLUBS, Name.THREE));
        partialDeck.add(new Card(Suit.HEARTS, Name.JACK));
        partialDeck.add(new Card(Suit.SPADES, Name.ACE));

        assertEquals(0, player1.getCurrentHandValue());
        player1.hit(partialDeck);
        assertEquals(10, player1.getCurrentHandValue());
        player1.hit(partialDeck);
        assertEquals(13, player1.getCurrentHandValue());
    }

    @Test(expected = IllegalStateException.class)
    public void testHitOver21() {
        ArrayList<Card> emptyCardList = new ArrayList<>();
        Deck partialDeck = new Deck(emptyCardList);
        // Initialize a deck of four cards
        partialDeck.add(new Card(Suit.DIAMONDS, Name.TEN));
        partialDeck.add(new Card(Suit.CLUBS, Name.TEN));
        partialDeck.add(new Card(Suit.HEARTS, Name.THREE));
        partialDeck.add(new Card(Suit.SPADES, Name.ACE));

        player1.hit(partialDeck);
        player1.hit(partialDeck);
        player1.hit(partialDeck);
        player1.hit(partialDeck);
    }
}
