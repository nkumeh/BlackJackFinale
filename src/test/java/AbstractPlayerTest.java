import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A JUnit4 test class for AbstractPlayer class.
 */
public class AbstractPlayerTest {

    private Player player1;
    private Player player2;
    private Dealer dealer;

    @Before
    public void setUp() {
        player1 = new Player("Maria Konnikova");
        Hand startingHand = new Hand(2);
        startingHand.add(new Card(Suit.CLUBS, Name.EIGHT));
        startingHand.add(new Card(Suit.CLUBS, Name.ACE));
        player2 = new Player("Justin Bonomo", startingHand);
        dealer = new Dealer("Bellagio Las Vegas");
    }

    @Test
    public void testgetCurrentHandValueStartingHandValue() {
        assertEquals(0, player1.getCurrentHandValue());
        assertEquals(19, player2.getCurrentHandValue());
        assertEquals(0, dealer.getCurrentHandValue());
    }

    @Test
    public void testgetCurrentHandAddedCard() {
        Card
        assertEquals(0, player1.getCurrentHandValue());
        assertEquals(19, player2.getCurrentHandValue());
        assertEquals(0, dealer.getCurrentHandValue());
    }




}
