package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class BlackJackModelTest {
    private ArrayList<String> playerNames1;
    private ArrayList<String> playerNames2;
    private HashMap<String,Enum> endOutcomes = new HashMap<>();
    private BlackJackModel game1;
    private BlackJackModel game2;
    private BlackJackModel game3;
    private Player game1Player1;
    private Player game2Player1;
    private Player game2Player2;
    private Player game2Player3;
    private Player game2Player4;
    private Player game3Player1;
    private Player game3Player2;
    private Player game3Player3;
    private Player game3Player4;
    private Dealer dealer1;
    private Dealer dealer2;
    private Dealer dealer3;

    @Before
    public void setUp() {
        playerNames1 = new ArrayList<>();
        playerNames1.add("Elsa Anna");
        playerNames2 = new ArrayList<>();
        playerNames2.add("Evi");
        playerNames2.add("Hoobie");
        playerNames2.add("Sano");
        playerNames2.add("Biscuit");
        game1 = new BlackJackModel(playerNames1);
        game2 = new BlackJackModel(playerNames2);
        game3 = new BlackJackModel(playerNames2);
        game1Player1 = game1.getPlayersForTesting().get(0);
        game2Player1 = game2.getPlayersForTesting().get(0);
        game2Player2 = game2.getPlayersForTesting().get(1);
        game2Player3 = game2.getPlayersForTesting().get(2);
        game2Player4 = game2.getPlayersForTesting().get(3);
        game3Player1 = game3.getPlayersForTesting().get(0);
        game3Player2 = game3.getPlayersForTesting().get(1);
        game3Player3 = game3.getPlayersForTesting().get(2);
        game3Player4 = game3.getPlayersForTesting().get(3);
        dealer1 = game1.getDealer();
        dealer2 = game2.getDealer();
        dealer3 = game3.getDealer();
        for (int i = 0; i < 2; i++) {
            game1.getPlayersForTesting().get(0).getHand().takeTopCard();
            game2.getPlayersForTesting().get(0).getHand().takeTopCard();
            game2.getPlayersForTesting().get(1).getHand().takeTopCard();
            game2.getPlayersForTesting().get(2).getHand().takeTopCard();
            game2.getPlayersForTesting().get(3).getHand().takeTopCard();
            game3.getPlayersForTesting().get(0).getHand().takeTopCard();
            game3.getPlayersForTesting().get(1).getHand().takeTopCard();
            game3.getPlayersForTesting().get(2).getHand().takeTopCard();
            game3.getPlayersForTesting().get(3).getHand().takeTopCard();
            dealer1.getHand().takeTopCard();
            dealer2.getHand().takeTopCard();
            dealer3.getHand().takeTopCard();
        }
        // Game 1 Setup
        dealer1.getHand().add(new Card(Suit.HEARTS, Name.EIGHT));
        dealer1.getHand().add(new Card(Suit.DIAMONDS, Name.TWO));
        game1Player1.getHand().add(new Card(Suit.CLUBS, Name.EIGHT));
        game1Player1.getHand().add(new Card(Suit.CLUBS, Name.ACE));
        // Game 2: Dealer2 has a hand with 19
        // Player hands Player 1: 20, Player 2: 19, Player 3: 12, Player 4: 23
        dealer2.getHand().add(new Card(Suit.HEARTS, Name.NINE));
        dealer2.getHand().add(new Card(Suit.DIAMONDS, Name.TEN));
        game2Player1.getHand().add(new Card(Suit.HEARTS, Name.QUEEN));
        game2Player1.getHand().add(new Card(Suit.DIAMONDS, Name.KING));
        game2Player2.getHand().add(new Card(Suit.HEARTS, Name.NINE));
        game2Player2.getHand().add(new Card(Suit.DIAMONDS, Name.TEN));
        game2Player3.getHand().add(new Card(Suit.HEARTS, Name.THREE));
        game2Player3.getHand().add(new Card(Suit.DIAMONDS, Name.THREE));
        game2Player4.getHand().add(new Card(Suit.HEARTS, Name.JACK));
        game2Player4.getHand().add(new Card(Suit.DIAMONDS, Name.NINE));
        // Dealer3 busts at 23
        // Game 2 Player hands (Player 1: 21, Player 2: 17, Player 3: 17, Player 4: 16
        dealer3.getHand().add(new Card(Suit.HEARTS, Name.KING));
        dealer3.getHand().add(new Card(Suit.DIAMONDS, Name.SIX));
//        dealer3.getHand().add(new Card(Suit.CLUBS, Name.EIGHT));
        game3Player1.getHand().add(new Card(Suit.CLUBS, Name.JACK));
        game3Player1.getHand().add(new Card(Suit.CLUBS, Name.ACE));
        game3Player2.getHand().add(new Card(Suit.DIAMONDS, Name.SEVEN));
        game3Player2.getHand().add(new Card(Suit.SPADES, Name.QUEEN));
        game3Player3.getHand().add(new Card(Suit.HEARTS, Name.SIX));
        game3Player3.getHand().add(new Card(Suit.CLUBS, Name.SIX));
        game3Player4.getHand().add(new Card(Suit.DIAMONDS, Name.EIGHT));
        game3Player4.getHand().add(new Card(Suit.SPADES, Name.EIGHT));
    }

    @Test
    public void testPlayersNamesAddedToGameInConstructor() {
        assertEquals("Elsa Anna", game1Player1.getName());
        assertEquals("Evi", game2Player1.getName());
        assertEquals("Hoobie", game2Player2.getName());
        assertEquals("Sano", game2Player3.getName());
        assertEquals("Biscuit", game2Player4.getName());
        assertEquals("Evi", game3Player1.getName());
        assertEquals("Hoobie", game3Player2.getName());
        assertEquals("Sano", game3Player3.getName());
        assertEquals("Biscuit", game3Player4.getName());
    }

    @Test
    public void testDeckDealtToPlayersInConstructor() {
        assertEquals(48, game1.getDeck().size());
        assertEquals(42, game2.getDeck().size());
        assertEquals(42, game3.getDeck().size());
        assertEquals(2, dealer1.getHand().getHandSize());
        assertEquals(2, dealer2.getHand().getHandSize());
        assertEquals(2, dealer3.getHand().getHandSize());
        assertEquals(2, game1Player1.getHand().getHandSize());
        assertEquals(2, game2Player1.getHand().getHandSize());
        assertEquals(2, game2Player2.getHand().getHandSize());
        assertEquals(2, game2Player3.getHand().getHandSize());
        assertEquals(2, game2Player4.getHand().getHandSize());
        assertEquals(2, game3Player1.getHand().getHandSize());
        assertEquals(2, game3Player2.getHand().getHandSize());
        assertEquals(2, game3Player3.getHand().getHandSize());
        assertEquals(2, game3Player4.getHand().getHandSize());
    }

    @Test
    public void testCreatedCorrectNumberPlayersInConstructor() {
        assertEquals(1, game1.getPlayersForTesting().size());
        assertEquals(4, game2.getPlayersForTesting().size());
        assertEquals(4, game3.getPlayersForTesting().size());
    }

    @Test
    public void testConstructorCreatesDealers() {
        assertEquals(dealer1, game1.getDealer());
        assertEquals(dealer2, game2.getDealer());
        assertEquals(dealer3, game3.getDealer());
    }

    @Test
    public void testGetPlayersReturnsDifferentObjectWithSameValues() {
        assertNotSame(game1.getPlayers(), game1.getPlayersForTesting());
        assertNotSame(game2.getPlayers(), game2.getPlayersForTesting());
        assertNotSame(game3.getPlayers(), game3.getPlayersForTesting());
        assertEquals(game1.getPlayers(), game1.getPlayersForTesting());
        assertEquals(game2.getPlayers(), game2.getPlayersForTesting());
        assertEquals(game3.getPlayers(), game3.getPlayersForTesting());

    }

    @Test
    public void testPlayersCanHit() {
        game2Player3.getHand().add(new Card(Suit.CLUBS, Name.THREE));
        game2Player3.getHand().add(new Card(Suit.SPADES, Name.THREE));
        game3Player4.getHand().add(new Card(Suit.HEARTS, Name.SEVEN));
    }

    @Test
    public void testDealerCanHitWhenInValidRange() {
        dealer3.getHand().add(new Card(Suit.CLUBS, Name.EIGHT));
    }

    @Test
    public void testPlayerCannotHitWhenAt21() {
        int previousScore = game3Player1.getCurrentHandValue();
        game3.playerHit(game3Player1);
        assertEquals(previousScore, game3Player1.getCurrentHandValue());
    }

    @Test
    public void testDealerCannotHitAndCatchException() {
        dealer3.getHand().add(new Card(Suit.CLUBS, Name.ACE));
        game3.playerHit(dealer3);
        assertEquals(17, dealer3.getCurrentHandValue());
    }

    @Test
    public void testGetGameResultsIdentifiesOutcomes() {
        game2Player3.getHand().add(new Card(Suit.SPADES, Name.THREE)); // total 9
        game2Player3.getHand().add(new Card(Suit.CLUBS, Name.THREE)); // total 12
        game2Player4.getHand().add(new Card(Suit.CLUBS, Name.FOUR)); // total 23
        game3Player3.getHand().add(new Card(Suit.CLUBS, Name.FIVE)); // total 17
        game3Player4.getHand().add(new Card(Suit.HEARTS, Name.SEVEN)); // bust
        dealer3.getHand().add(new Card(Suit.CLUBS, Name.EIGHT)); // Dealer busts
        game1.getGameResults();
        game2.getGameResults();
        game3.getGameResults();
        assertEquals(Outcome.WIN, game1.getOutcomes().get(game1Player1.getName()));
        assertEquals(Outcome.WIN, game2.getOutcomes().get(game2Player1.getName()));
        assertEquals(Outcome.TIE, game2.getOutcomes().get(game2Player2.getName()));
        assertEquals(Outcome.LOSE, game2.getOutcomes().get(game2Player3.getName()));
        assertEquals(Outcome.LOSE, game3.getOutcomes().get(game2Player4.getName()));
        assertEquals(Outcome.WIN, game3.getOutcomes().get(game3Player1.getName()));
        assertEquals(Outcome.WIN, game3.getOutcomes().get(game3Player2.getName()));
        assertEquals(Outcome.WIN, game3.getOutcomes().get(game3Player3.getName()));
        assertEquals(Outcome.LOSE, game3.getOutcomes().get(game3Player4.getName()));
    }

    @Test
    public void testGetOutcomes() {
        game2Player3.getHand().add(new Card(Suit.SPADES, Name.THREE)); // total 9
        game2Player3.getHand().add(new Card(Suit.CLUBS, Name.THREE)); // total 12
        game2Player4.getHand().add(new Card(Suit.CLUBS, Name.FOUR)); // total 23
        game3Player3.getHand().add(new Card(Suit.CLUBS, Name.FIVE)); // total 17
        game3Player4.getHand().add(new Card(Suit.HEARTS, Name.SEVEN)); // bust
        dealer3.getHand().add(new Card(Suit.CLUBS, Name.EIGHT)); // Dealer busts

        game1.getGameResults();
        HashMap<String, Enum> expectedOutcome1 = new HashMap<>();
        expectedOutcome1.put("Elsa Anna", Outcome.WIN);
        assertEquals(expectedOutcome1, game1.getOutcomes());

        game2.getGameResults();
        HashMap<String, Enum> expectedOutcome2 = new HashMap<>();
        expectedOutcome2.put("Evi", Outcome.WIN);
        expectedOutcome2.put("Hoobie", Outcome.TIE);
        expectedOutcome2.put("Sano", Outcome.LOSE);
        expectedOutcome2.put("Biscuit", Outcome.LOSE);
        assertEquals(expectedOutcome2, game2.getOutcomes());

        game3.getGameResults();
        HashMap<String, Enum> expectedOutcome3 = new HashMap<>();
        expectedOutcome3.put("Evi", Outcome.WIN);
        expectedOutcome3.put("Hoobie", Outcome.WIN);
        expectedOutcome3.put("Sano", Outcome.WIN);
        expectedOutcome3.put("Biscuit", Outcome.LOSE);
        assertEquals(expectedOutcome3, game3.getOutcomes());
    }

}