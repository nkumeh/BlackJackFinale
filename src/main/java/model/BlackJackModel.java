package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the model for a blackjack game.
 */


public class BlackJackModel {

    private Deck deck;
    private Dealer dealer;
    private ArrayList<Player> players;
    private HashMap<String, ArrayList> outcomes;

    /**
     * The constructor for BlackJack takes a list of player names(as Strings) and
     * sets up the required components of the game.
     */
    public BlackJackModel(ArrayList<String> playerNames) {
        this.prepareDeck();
        this.addPlayers(playerNames);
        this.dealer = new Dealer();
    }

    /**
     * This method creates and shuffles a deck for use in the game.
     */
    private void prepareDeck() {
        deck = new Deck();
        deck.shuffle();
    }

    /**
     * This method creates player objects from the names that were passed in to the
     * constructor and adds them to the list of players.
     * @param playerNames a list of Strings representing the player names.
     */
    private void addPlayers(ArrayList<String> playerNames) {
        for (String name : playerNames) {
            players.add(new Player(name, this.dealInitialHand()));
        }
        // add player names to hashmap
    }

    /**
     * This method returns the players and their hands.
     */
    public ArrayList<Player> getPlayers() {
        ArrayList<Player> playerListCopy = new ArrayList<>();
        for (Player player : this.players) {
            playerListCopy.add(player);
        }
        return playerListCopy;
    }

    public Dealer getDealer() {
        return this.dealer;
    }


    /**
     * This helper method deals an initial hand to a player.
     * @return
     */
    private Hand dealInitialHand() {
        return new Hand(deck, 2);
    }

    public void playerHit (AbstractPlayer player) {
         if (player.getCurrentHandValue() < 21) {
             player.hit(this.deck);
         }
    }
/*
    public void findWinners() {
         setUpOutcomes();
         iterate through players list to get current hand value
         if hasPlayerBusted()
             updateOutcomes(player, LOSE)
         else if hasDealerBusted()
             updateOutcomes(dealer, LOSE)
         else hasPlayerWon()
             updateOutcomes(player, Won)
         else hasPlayerTied()
             updateOutcomes(player, TIE)
         else
             updateOutcomes(player, LOSE)

    }

    // if we decide to have the keys be the String for Winners, Losers, or Ties
    private void setUpOutcomes() {
         outcomes = new HashMap<String, ArrayList<String>();
         outcomes.put("Winners");
         outcomes.put("Losers";
         outcome.put("Ties");
    }

    private boolean hasDealerBusted() {
        dealer.getCurrentHandValue() > 21;
        return true;
    }

    private boolean hasPlayerBusted(Player player) {
        currentPlayer.getCurrentHandValue() > 21;
        return true;
    }

    private boolean hasPlayerWon(Player player) {
        // currentPlayer.getCurrentHandValue() > dealer.getCurrentHandValue()
        return true;
    }

    private boolean hasPlayerTied(Player player) {
        // currentPlayer.getCurrentHandValue() == dealer.getCurrentHandValue()
        return true;
    }

    private void updateOutcomes(Player player, Outcome outcome) {
        // set Player value to outcome (Win, Lose, Tie)
    }

    public HashMap getOutcomes() {
//        return the Hashmap;
    }
    */

}
