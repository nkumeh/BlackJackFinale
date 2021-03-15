import org.junit.Test;

import static org.junit.Assert.*;

public class SuitTest {

    @Test
    public void getLabel() {
        assertEquals("Spades", Suit.SPADES.getLabel());
        assertEquals("Hearts", Suit.HEARTS.getLabel());
        assertEquals("Diamonds", Suit.DIAMONDS.getLabel());
        assertEquals("Clubs", Suit.CLUBS.getLabel());
    }

}