package view;

import model.Card;
import model.Name;
import model.Suit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VisualCardTest {

    private VisualCard invisibleCard;
    private VisualCard twoOfClubs;
    private VisualCard aceOfSpades;
    private VisualCard jackOfHearts;
    private VisualCard tenOfDiamonds;
    private VisualCard queenOfHearts;

    @Before
    public void setUp() {
        invisibleCard = new VisualCard(new Card(Suit.HEARTS, Name.KING),false);
        twoOfClubs = new VisualCard(new Card(Suit.CLUBS, Name.TWO));
        aceOfSpades = new VisualCard(new Card(Suit.SPADES, Name.ACE));
        jackOfHearts = new VisualCard(new Card(Suit.HEARTS, Name.JACK));
        tenOfDiamonds = new VisualCard(new Card(Suit.DIAMONDS, Name.TEN));
        queenOfHearts = new VisualCard(new Card(Suit.HEARTS, Name.QUEEN), true);
    }

    @Test
    public void testOneArgumentConstructorCreatesVisibleCard() {
        assertTrue(twoOfClubs.getVisible());
        assertTrue(aceOfSpades.getVisible());
        assertTrue(jackOfHearts.getVisible());
        assertTrue(tenOfDiamonds.getVisible());
    }

    @Test
    public void testTwoArgumentConstructorCreatesVisibleCard() {
        assertTrue(queenOfHearts.getVisible());
    }

    @Test
    public void testTwoArgumentConstructorCreatesInvisibleCard() {
        assertFalse(invisibleCard.getVisible());
    }

    @Test
    public void testSetIsVisible() {
        invisibleCard.setVisible(true);
        assertTrue(invisibleCard.getVisible());
        tenOfDiamonds.setVisible(false);
        assertFalse(tenOfDiamonds.getVisible());
    }

    @Test
    public void testFormatsInvisibleCard() {
        String expected = """
            +-----+
            |XXXXX|
            |XXXXX|
            |XXXXX|
            +-----+
                """;
        assertEquals(expected, invisibleCard.toString());
    }

    @Test
    public void testFormatsPreviouslyVisibleCard() {
        queenOfHearts.setVisible(false);
        String expected = """
            +-----+
            |XXXXX|
            |XXXXX|
            |XXXXX|
            +-----+
                """;
        assertEquals(expected, queenOfHearts.toString());
    }

    @Test
    public void testFormatsSingleDigitCard() {
        String expected = """
             +-----+
             |♣    |
             |  2  |
             |    ♣|
             +-----+
                        """;
        assertEquals(expected, twoOfClubs.toString());
    }

    @Test
    public void testFormatsTwoDigitCard() {
        String expected = """
            +-----+
            |♢    |
            |  10 |
            |    ♢|
            +-----+
                        """;
        assertEquals(expected, tenOfDiamonds.toString());
    }

    @Test
    public void testFormatsAceCard() {
        String expected = """
            +-----+
            |♠    |
            | Ace |
            |    ♠|
            +-----+
                        """;
        assertEquals(expected, aceOfSpades.toString());
    }

    @Test
    public void testFormatsJackCard() {
        String expected = """
            +-----+
            |♡    |
            |Jack |
            |    ♡|
            +-----+
                            """;
        assertEquals(expected, jackOfHearts.toString());
    }

    @Test
    public void testFormatsQueenCard() {
        String expected = """
            +-----+
            |♡    |
            |Queen|
            |    ♡|
            +-----+
                            """;
        assertEquals(expected, queenOfHearts.toString());
    }
}