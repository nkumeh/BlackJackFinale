import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A JUnit4 test class for enum Name.
 */
public class NameTest {

    @Test
    public void getBlackjackValue() {
        assertEquals(2, Name.TWO.getBlackjackValue());
        assertEquals(3, Name.THREE.getBlackjackValue());
        assertEquals(4, Name.FOUR.getBlackjackValue());
        assertEquals(5, Name.FIVE.getBlackjackValue());
        assertEquals(6, Name.SIX.getBlackjackValue());
        assertEquals(7, Name.SEVEN.getBlackjackValue());
        assertEquals(8, Name.EIGHT.getBlackjackValue());
        assertEquals(9, Name.NINE.getBlackjackValue());
        assertEquals(10, Name.TEN.getBlackjackValue());
        assertEquals(10, Name.JACK.getBlackjackValue());
        assertEquals(10, Name.QUEEN.getBlackjackValue());
        assertEquals(10, Name.KING.getBlackjackValue());
        assertEquals(11, Name.ACE.getBlackjackValue());
    }

}