import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck completeDeck;
    private Deck partialDeck;
    private Deck emptyDeck;


    @Before
    public void setUp() {
        ArrayList<Card> emptyCardList = new ArrayList<>();
        completeDeck = new Deck();
        partialDeck = new Deck(emptyCardList);
        emptyDeck = new Deck(emptyCardList);

        // Initialize a deck of four cards
        partialDeck.add(new Card(Suit.DIAMONDS, Name.TEN));
        partialDeck.add(new Card(Suit.CLUBS, Name.THREE));
        partialDeck.add(new Card(Suit.HEARTS, Name.JACK));
        partialDeck.add(new Card(Suit.SPADES, Name.ACE));
    }

    /**
     * This test checks the value of the first card in the deck.
     */
    @Test
    public void testGetCard() {
        Card expectedCard1 = new Card(Suit.CLUBS, Name.TWO);
        assertEquals(expectedCard1, completeDeck.getCard(0));
        assertEquals(new Card(Suit.DIAMONDS, Name.TEN), partialDeck.getCard(0));
        assertEquals(new Card(Suit.CLUBS, Name.THREE), partialDeck.getCard(1));
        assertEquals(new Card(Suit.HEARTS, Name.JACK), partialDeck.getCard(2));
        assertEquals(new Card(Suit.SPADES, Name.ACE), partialDeck.getCard(3));
    }

    /**
     * This test checks that getCard throws an error on an empty deck.
     */
    @Test(expected = IllegalStateException.class)
    public void testGetCardThrowsExceptionForEmptyDeck() {
        emptyDeck.getCard(0);
    }

    /**
     * This tests checks that the shuffle method returns a different list
     * of cards than were there previously.
     */
    @Test
    public void testShuffle() {
        Deck originalDeck = new Deck(completeDeck);
        completeDeck.shuffle();
        assertNotEquals(Arrays.asList(originalDeck.getDeck()), Arrays.asList(completeDeck.getDeck()));

        Deck originalDeck2 = new Deck(partialDeck);
        partialDeck.shuffle();
        assertNotEquals(Arrays.asList(originalDeck2.getDeck()), Arrays.asList(partialDeck.getDeck()));
    }

    /**
     * This test checks that you can add a card to the deck.
     */
    @Test
    public void testAddCardToDeck() {
        assertEquals(4, partialDeck.size());
        Card newCard = new Card(Suit.HEARTS, Name.ACE);
        assertFalse(partialDeck.hasCard(newCard));
        partialDeck.add(newCard);
        assertTrue(partialDeck.hasCard(newCard));
        assertEquals(5, partialDeck.size());
    }

    /**
     * This test checks to ensure an exception is thrown when trying to
     * add a duplicate card to the deck.
     */
    @Test(expected = IllegalStateException.class)
    public void testCannotAddDuplicateCardToDeck() {
        completeDeck.add(new Card(Suit.SPADES, Name.ACE));
    }

    /**
     * This test checks to ensure an exception is thrown when trying to
     * add a duplicate card to the deck.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddCardToFullDeck() {
        partialDeck.add(new Card(Suit.SPADES, Name.ACE));
    }

    /**
     * This test checks that a card can be added to the deck.
     */
    @Test
    public void testRemoveCardFromDeck() {
        assertEquals(4, partialDeck.size());
        Card firstCard = partialDeck.getCard(0);
        partialDeck.takeTopCard();
        assertFalse(partialDeck.hasCard(firstCard));
        assertEquals(3, partialDeck.size());

        assertEquals(52, completeDeck.size());
        firstCard = completeDeck.getCard(0);
        completeDeck.takeTopCard();
        assertFalse(completeDeck.hasCard(firstCard));
        assertEquals(51, completeDeck.size());
    }

    /**
     * This test checks to ensure an exception is thrown when trying to
     * add a duplicate card to the deck.
     */
    @Test(expected = IllegalStateException.class)
    public void testCannotRemoveCardFromEmptyDeck() {
        emptyDeck.takeTopCard();
    }

    /**
     * This method checks that the hasCard method returns true when a
     * card is already present in the Deck.
     */
    @Test
    public void testHasCard() {
        Card searchCard = new Card(Suit.HEARTS, Name.FIVE);
        assertTrue(completeDeck.hasCard(searchCard));
        assertFalse(partialDeck.hasCard(searchCard));
    }

    /**
     * This test checks that the sort function ordering of the
     * Deck is as expected.
     */
    @Test
    public void testSortOnFullDeck() {
        ArrayList<Card> emptyList = new ArrayList<>();
        Deck sortedDeck = new Deck(emptyList);
        for (Name name : Name.values()) {
            sortedDeck.add(new Card(Suit.CLUBS, name));
            sortedDeck.add(new Card(Suit.DIAMONDS, name));
            sortedDeck.add(new Card(Suit.HEARTS, name));
            sortedDeck.add(new Card(Suit.SPADES, name));
        }
        assertNotEquals(sortedDeck.getDeck(), completeDeck.getDeck());
        completeDeck.sort();
        assertEquals(sortedDeck.getDeck(), completeDeck.getDeck());
    }

    /**
     * This test compares whether a small deck sorts as expected and
     * uses one card of each suit.
     */
    @Test
    public void testSortOnPartialDeck() {
        ArrayList<Card> emptyList = new ArrayList<>();
        Deck sortedDeck = new Deck(emptyList);
        sortedDeck.add(new Card(Suit.CLUBS, Name.THREE));
        sortedDeck.add(new Card(Suit.DIAMONDS, Name.TEN));
        sortedDeck.add(new Card(Suit.HEARTS, Name.JACK));
        sortedDeck.add(new Card(Suit.SPADES, Name.ACE));
        assertNotEquals(sortedDeck.getDeck(), partialDeck.getDeck());
        partialDeck.sort();
        assertEquals(sortedDeck.getDeck(), partialDeck.getDeck());
    }

    /**
     * This test checks whether cards of different suits with the same
     * value sort correctly.
     */
    @Test
    public void testSortOnDeckWithSameValues() {
        ArrayList<Card> emptyList = new ArrayList<>();
        Deck unsortedDeck = new Deck(emptyList);
        unsortedDeck.add(new Card(Suit.SPADES, Name.TWO));
        unsortedDeck.add(new Card(Suit.DIAMONDS, Name.TWO));
        unsortedDeck.add(new Card(Suit.HEARTS, Name.TWO));
        unsortedDeck.add(new Card(Suit.CLUBS, Name.TWO));

        Deck sortedDeck = new Deck(emptyList);
        sortedDeck.add(new Card(Suit.CLUBS, Name.TWO));
        sortedDeck.add(new Card(Suit.DIAMONDS, Name.TWO));
        sortedDeck.add(new Card(Suit.HEARTS, Name.TWO));
        sortedDeck.add(new Card(Suit.SPADES, Name.TWO));
        assertNotEquals(sortedDeck.getDeck(), unsortedDeck.getDeck());
        unsortedDeck.sort();
        assertEquals(sortedDeck.getDeck(), unsortedDeck.getDeck());

    }

    /**
     * This test checks that an IllegalStateException is thrown
     * when trying to sort an empty Deck.
     */
    @Test(expected = IllegalStateException.class)
    public void testSortThrowsIllegalStateExceptionOnEmptyDeck() {
        emptyDeck.sort();
    }

    @Test
    public void testSize() {
        assertEquals(52, completeDeck.size());
        assertEquals(4, partialDeck.size());
        assertEquals(0, emptyDeck.size());

    }
}
