package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals(1, Name.ACE.getBlackjackValue());
    }

    @Test
    public void getLabel() {
        assertEquals("Two", Name.TWO.getLabel());
        assertEquals("Three", Name.THREE.getLabel());
        assertEquals("Four", Name.FOUR.getLabel());
        assertEquals("Five", Name.FIVE.getLabel());
        assertEquals("Six", Name.SIX.getLabel());
        assertEquals("Seven", Name.SEVEN.getLabel());
        assertEquals("Eight", Name.EIGHT.getLabel());
        assertEquals("Nine", Name.NINE.getLabel());
        assertEquals("Ten", Name.TEN.getLabel());
        assertEquals("Jack", Name.JACK.getLabel());
        assertEquals("Queen", Name.QUEEN.getLabel());
        assertEquals("King", Name.KING.getLabel());
        assertEquals("Ace", Name.ACE.getLabel());
    }


}