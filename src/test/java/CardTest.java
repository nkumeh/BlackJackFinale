import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A JUnit4 test class for Card.
 */
public class CardTest {

    private Card aceOfClubs;
    private Card twoOfHearts;
    private Card aceOfSpades;
    private Card jackOfDiamonds;

    @Before
    public void setUp() {
        aceOfClubs = new Card(Suit.CLUBS, Name.ACE);
        twoOfHearts = new Card(Suit.HEARTS, Name.TWO);
        aceOfSpades = new Card(Suit.SPADES, Name.ACE);
        jackOfDiamonds = new Card(Suit.DIAMONDS, Name.JACK);
    }

    @Test
    public void getSuit() {
        assertEquals(Suit.CLUBS, aceOfClubs.getSuit());
        assertEquals(Suit.HEARTS, twoOfHearts.getSuit());
        assertEquals(Suit.SPADES, aceOfSpades.getSuit());
        assertEquals(Suit.DIAMONDS, jackOfDiamonds.getSuit());
    }

    @Test
    public void getName() {
        assertEquals(Name.ACE, aceOfClubs.getName());
        assertEquals(Name.TWO, twoOfHearts.getName());
        assertEquals(Name.ACE, aceOfSpades.getName());
        assertEquals(Name.JACK, jackOfDiamonds.getName());
    }

    @Test
    public void getValue() {
        assertEquals(1, aceOfClubs.getValue());
        assertEquals(2, twoOfHearts.getValue());
        assertEquals(1, aceOfSpades.getValue());
        assertEquals(10, jackOfDiamonds.getValue());
    }

    @Test
    public void isAce() {
        assertTrue(aceOfClubs.isAce());
        assertFalse(twoOfHearts.isAce());
    }

    @Test
    public void testToString() {
        assertEquals("Ace of Clubs", aceOfClubs.toString());
        assertEquals("Two of Hearts", twoOfHearts.toString());
        assertEquals("Ace of Spades", aceOfSpades.toString());
        assertEquals("Jack of Diamonds", jackOfDiamonds.toString());
    }

    @Test
    public void testEquals() {
        // testing reflexivity
        assertEquals(aceOfClubs, aceOfClubs);
        assertEquals(twoOfHearts, twoOfHearts);
        assertEquals(aceOfSpades, aceOfSpades);

        // testing symmetry
        assertNotEquals(aceOfSpades, aceOfClubs);
        assertNotEquals(aceOfClubs, aceOfSpades);

        // testing transitivity
        Card aceOfSpadesCopy = new Card(Suit.SPADES, Name.ACE);
        Card secondAceOfSpadesCopy = new Card(Suit.SPADES, Name.ACE);
        assertEquals(aceOfSpades, aceOfSpadesCopy);
        assertEquals(aceOfSpadesCopy, secondAceOfSpadesCopy);
        assertEquals(aceOfSpades, secondAceOfSpadesCopy);
    }
}