import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck deck1;

    @Before
    public void setUp() {
        deck1 = new Deck();
    }

    /**
     * Tests that getDeck returns a new Array object than the original.
     */
    @Test
    public void testGetDeckReturnsNewObject() {
        ArrayList<Card> returnedDeck = deck1.getDeck();
        assertNotSame(deck1, returnedDeck);
    }

    /**
     * This test checks that the sort function ordering of the Deck is as expected.
     *
     */
    @Test
    public void testSortOnFullDeck() {
        Deck sortedDeck = new Deck();
        String sortedDeckAsString = "[Two of Clubs, Two of Diamonds, Two of Hearts, Two of Spades, " +
                "Three of Clubs, Three of Diamonds, Three of Hearts, Three of Spades, " +
                "Four of Clubs, Four of Diamonds, Four of Hearts, Four of Spades, " +
                "Five of Clubs, Five of Diamonds, Five of Hearts, Five of Spades, " +
                "Six of Clubs, Six of Diamonds, Six of Hearts, Six of Spades, " +
                "Seven of Clubs, Seven of Diamonds, Seven of Hearts, Seven of Spades, " +
                "Eight of Clubs, Eight of Diamonds, Eight of Hearts, Eight of Spades, " +
                "Nine of Clubs, Nine of Diamonds, Nine of Hearts, Nine of Spades, " +
                "Ten of Clubs, Ten of Diamonds, Ten of Hearts, Ten of Spades, " +
                "Jack of Clubs, Jack of Diamonds, Jack of Hearts, Jack of Spades, " +
                "Queen of Clubs, Queen of Diamonds, Queen of Hearts, Queen of Spades, " +
                "King of Clubs, King of Diamonds, King of Hearts, King of Spades, " +
                "Ace of Clubs, Ace of Diamonds, Ace of Hearts, Ace of Spades]";
        deck1.sort();
        assertEquals(sortedDeckAsString, deck1.getDeck().toString());
    }

    /**
     * This test compares whether a small deck sorts as expected and uses one card of each suit.
     */
    @Test
    public void testSortOnPartialDeck() {
        ArrayList<Card> comparisonDeck = new ArrayList<>();
        comparisonDeck.add(new Card(Suit.DIAMONDS, Name.TWO));
        comparisonDeck.add(new Card(Suit.CLUBS, Name.THREE));
        comparisonDeck.add(new Card(Suit.HEARTS, Name.JACK));
        comparisonDeck.add(new Card(Suit.SPADES, Name.ACE));
        
        Deck partialDeck = new Deck(comparisonDeck);
        String expectedSortedResult = "['Two of Diamonds', 'Three of Clubs', 'Jack of Hearts', 'Ace of Spades']";
        partialDeck.sort();
        assertNotEquals(expectedSortedResult, partialDeck.getDeck().toString());
    }

    /**
     * This test checks whether cards of different suits with the same value sort correctly.
     */
    @Test
    public void testSortOnDeckWithSameValues() {
        ArrayList<Card> comparisonDeck = new ArrayList<>();
        comparisonDeck.add(new Card(Suit.SPADES, Name.TWO));
        comparisonDeck.add(new Card(Suit.DIAMONDS, Name.TWO));
        comparisonDeck.add(new Card(Suit.HEARTS, Name.TWO));
        comparisonDeck.add(new Card(Suit.CLUBS, Name.TWO));

        Deck partialDeck = new Deck(comparisonDeck);
        String expectedSortedResult = "['Two of Clubs', 'Two of Diamonds', 'Two of Hearts', 'Two of Spades']";
        partialDeck.sort();
        assertNotEquals(expectedSortedResult, partialDeck.getDeck().toString());
    }


    @Test(expected = IllegalStateException.class)
    public void testSortThrowsIllegalStateExceptionOnEmptyDeck() {
        ArrayList<Card> emptyDeckArray = new ArrayList<>();
        Deck emptyDeck = new Deck(emptyDeckArray);
        emptyDeck.sort();
    }


}
