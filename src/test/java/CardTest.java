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

    @Before
    public void setUp() {
        aceOfClubs = new Card(Suit.CLUBS, Name.ACE);
        twoOfHearts = new Card(Suit.HEARTS, Name.TWO);
        aceOfSpades = new Card(Suit.SPADES, Name.ACE);
    }

    @Test
    public void getSuit() {
        assertEquals(Suit.CLUBS, aceOfClubs.getSuit());
        assertEquals(Suit.HEARTS, twoOfHearts.getSuit());
        assertEquals(Suit.SPADES, aceOfSpades.getSuit());
    }

    @Test
    public void getName() {
        assertEquals(Name.ACE, aceOfClubs.getName());
        assertEquals(Name.TWO, twoOfHearts.getName());
        assertEquals(Name.ACE, aceOfSpades.getName());
    }

    @Test
    public void isAce() {
        assertTrue(aceOfClubs.isAce());
        assertFalse(twoOfHearts.isAce());
    }

    @Test
    public void testToString() {
        assertEquals("ACE of CLUBS", aceOfClubs.toString());
        assertEquals("TWO of HEARTS", twoOfHearts.toString());
        assertEquals("ACE of SPADES", aceOfSpades.toString());
    }

    @Test
    public void testEquals() {
        // testing reflexivity
        assertTrue(aceOfClubs.equals(aceOfClubs));
        assertTrue(twoOfHearts.equals(twoOfHearts));
        assertTrue(aceOfSpades.equals(aceOfSpades));

        // testing symmetry
        assertFalse(aceOfSpades.equals(aceOfClubs));
        assertFalse(aceOfClubs.equals(aceOfSpades));

        // testing transitivity
        Card aceOfSpadesCopy = new Card(Suit.SPADES, Name.ACE);
        Card secondAceOfSpadesCopy = new Card(Suit.SPADES, Name.ACE);
        assertTrue(aceOfSpades.equals(aceOfSpadesCopy));
        assertTrue(aceOfSpadesCopy.equals(secondAceOfSpadesCopy));
        assertTrue(aceOfSpades.equals(secondAceOfSpadesCopy));
    }
}