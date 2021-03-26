package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class BlackJackModelTest {
    private final ArrayList<String> playerNames = new ArrayList<>();
    private HashMap<String,Enum> endOutcomes = new HashMap<>();
    private BlackJackModel game1;
    private Player player1;
    private Dealer dealer1;
    private Hand hand1, hand2;

    @Before
    public void setUp() throws Exception {
        game1 = new BlackJackModel(playerNames);
        hand1 = new Hand();
        hand2 = new Hand();
        player1 = new Player("Elsa Anna", hand1);
        dealer1 = new Dealer(hand2);


    }

    @Test
    public void getPlayers() {
        playerNames.add(player1.getName());
        assertEquals("Elsa Anna", playerNames.get(0));
    }


    @Test
    public void findWinners() {
        hand1.add(new Card(Suit.CLUBS, Name.EIGHT));
        hand1.add(new Card(Suit.CLUBS, Name.ACE));

        hand2.add(new Card(Suit.HEARTS, Name.EIGHT));
        hand2.add(new Card(Suit.DIAMONDS, Name.TWO));

        assertTrue("WIN", player1.getCurrentHandValue() > dealer1.getCurrentHandValue());

    }

    @Test
    public void findTie() {
        hand1.add(new Card(Suit.CLUBS, Name.EIGHT));
        hand1.add(new Card(Suit.CLUBS, Name.ACE));

        hand2.add(new Card(Suit.SPADES, Name.EIGHT));
        hand2.add(new Card(Suit.SPADES, Name.ACE));

        assertEquals("TIE", player1.getCurrentHandValue(), dealer1.getCurrentHandValue());

    }

    @Test
    public void findLosers() {
        hand2.add(new Card(Suit.CLUBS, Name.EIGHT));
        hand2.add(new Card(Suit.CLUBS, Name.ACE));

        hand1.add(new Card(Suit.HEARTS, Name.EIGHT));
        hand1.add(new Card(Suit.DIAMONDS, Name.TWO));

        assertTrue("LOSE", player1.getCurrentHandValue() < dealer1.getCurrentHandValue());

    }

    @Test
    public void findBusts() {
        hand1.add(new Card(Suit.HEARTS, Name.EIGHT));
        hand1.add(new Card(Suit.DIAMONDS, Name.NINE));
        hand1.add(new Card(Suit.SPADES, Name.SEVEN));

        hand2.add(new Card(Suit.CLUBS, Name.EIGHT));
        hand2.add(new Card(Suit.CLUBS, Name.NINE));
        hand2.add(new Card(Suit.DIAMONDS, Name.TEN));

        assertTrue("WIN", dealer1.getCurrentHandValue() > 21);
        assertTrue("LOSE", player1.getCurrentHandValue() > 21);

    }


    @Test
    public void getOutcomes() {
        endOutcomes.put(player1.getName(), BlackJackModel.Outcome.WIN);
        assertEquals(BlackJackModel.Outcome.WIN,endOutcomes.get(player1.getName()));

    }
}