import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * A JUnit4 test class for Player class.
 */
public class PlayerTest {
    private Player player1;

    @Before
    public void setUp() {
        player1 = new Player("Maria Konnikova");
    }

    @Test
    public void testPlayer() {
        assertEquals(0, player1.getCurrentHandValue());
        assertEquals("Maria Konnikova", player1.getName());
    }

}
