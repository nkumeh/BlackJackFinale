package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BlackJackModelTest {
    ArrayList<String> playerNames = new ArrayList<>();
    private BlackJackModel game1;
    private Player player1;
    private Dealer dealer1;
    private Hand hand1;

    @Before
    public void setUp() throws Exception {
        game1 = new BlackJackModel(playerNames);
        hand1 = new Hand();
        player1 = new Player("Elsa Anna", hand1);
        dealer1 = new Dealer();
        hand1.add(new Card(Suit.CLUBS, Name.EIGHT));
        hand1.add(new Card(Suit.CLUBS, Name.ACE));

    }

    @Test
    public void getPlayers() {
        playerNames.add(player1.getName());
        assertEquals("Elsa Anna", playerNames.get(0));
    }


    @Test
    public void findWinners() {

        assertTrue("TIE", player1.getCurrentHandValue() == dealer1.getCurrentHandValue());
        assertTrue("WIN", player1.getCurrentHandValue() < 21);

    }

    @Test
    public void getOutcomes() {
        assertTrue("Elsa Anna", player1.getCurrentHandValue() < 21);
    }
}