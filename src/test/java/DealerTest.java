import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * A JUnit4 test class for Dealer class.
 */
public class DealerTest {

    private Dealer dealer;

    @Before
    public void setUp() {
        dealer = new Dealer();
    }

    @Test
    public void testHit() {
        ArrayList<Card> emptyCardList = new ArrayList<>();
        Deck partialDeck = new Deck(emptyCardList);
        // Initialize a deck of four cards
        partialDeck.add(new Card(Suit.DIAMONDS, Name.TEN));
        partialDeck.add(new Card(Suit.CLUBS, Name.SIX));
        partialDeck.add(new Card(Suit.HEARTS, Name.ACE));
        partialDeck.add(new Card(Suit.SPADES, Name.TWO));

        assertEquals(0, dealer.getCurrentHandValue());
        assertEquals(0, dealer.getHand().size());
        // dealer took 1 card
        dealer.hit(partialDeck);
        assertEquals(10, dealer.getCurrentHandValue());
        assertEquals(1, dealer.getHand().size());
        // dealer took a 2nd card
        dealer.hit(partialDeck);
        assertEquals(16, dealer.getCurrentHandValue());
        assertEquals(2, dealer.getHand().size());
        // dealer took 3rd card; at 17, cannot hit anymore
        dealer.hit(partialDeck);
        assertEquals(17, dealer.getCurrentHandValue());
        assertEquals(3, dealer.getHand().size());
        // Nothing should change about dealer hand
        dealer.hit(partialDeck);
        assertEquals(17, dealer.getCurrentHandValue());
        assertEquals(3, dealer.getHand().size());
    }
}
