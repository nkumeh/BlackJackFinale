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
    private HashMap<String,ArrayList<Player>> outcomes;


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
     * @return the hand of the player
     */
    private Hand dealInitialHand() {
        return new Hand(deck, 2);
    }

    public void playerHit (AbstractPlayer player) {
         if (player.getCurrentHandValue() < 21) {
             player.hit(this.deck);
         }
    }

    public void findWinners() {
        setUpOutcomes();
//         iterate through players list to get current hand value
        for (Player player : this.players) {

            if (hasPlayerBusted(player)) {
                updateOutcomes(player, Outcome.LOSE);

            } else if (hasDealerBusted()) {
                if (hasPlayerBusted(player)){
                    updateOutcomes(player, Outcome.LOSE);
                }
                else {
                    updateOutcomes(player, Outcome.WIN);
                }
            }

            else if (hasPlayerWon(player)) {
                updateOutcomes(player, Outcome.WIN);
            }
            
            else if (hasPlayerTied(player)) {
                updateOutcomes(player, Outcome.TIE);
            }
        }
    }


    // if we decide to have the keys be the String for Winners, Losers, or Ties
    private void setUpOutcomes() {
            outcomes = new HashMap<>();

            outcomes.put("Winner", players);
            outcomes.put("Losers", players);
            outcomes.put("Ties", players);

    }


    private boolean hasDealerBusted() {
        return dealer.getCurrentHandValue() > 21;
    }

    private boolean hasPlayerBusted(Player player) {
        return player.getCurrentHandValue() > 21;
    }

    private boolean hasPlayerWon(Player player) {
        return player.getCurrentHandValue() > dealer.getCurrentHandValue();
    }

    private boolean hasPlayerTied(Player player) {
        return player.getCurrentHandValue() == dealer.getCurrentHandValue();
    }

    private void updateOutcomes(Player player, Outcome status) {
        // set Player value to outcome (Win, Lose, Tie)
        if (hasPlayerWon(player)) {
            status = Outcome.WIN;
        }
        else if (hasDealerBusted()) {
            status = Outcome.LOSE;
        }
        else if (hasPlayerTied(player)) {
            status = Outcome.TIE;
        }
        else if (hasPlayerBusted(player)) {
            status = Outcome.LOSE;
        }
    }

//    public HashMap getOutcomes() {
////        return the Hashmap;
//    }

    enum Outcome {
        WIN, LOSE, TIE
    }

}


