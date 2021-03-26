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
    private HashMap<String,Enum> outcomes;


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

    /**
     * This function helps set up the outcome hashtable
     */
    private void setUpOutcomes() {
        outcomes = new HashMap<String,Enum>();
        for (Player player : this.players) {
            outcomes.put(player.getName(), null);
        }
    }

    /**
     * This function checks the criteria for winning / losing for each player
     */
    public void findWinners() {
        setUpOutcomes();
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

    /**
     * Boolean function to check if the dealer busted
     * @return false otherwise
     */
    private boolean hasDealerBusted() {
        return dealer.getCurrentHandValue() > 21;
    }

    /**
     * Boolean function to check if the player busted
     * @return false otherwise
     */
    private boolean hasPlayerBusted(Player player) {
        return player.getCurrentHandValue() > 21;
    }

    /**
     * Boolean function to check if the player won
     * @return false otherwise
     */
    private boolean hasPlayerWon(Player player) {
        return player.getCurrentHandValue() > dealer.getCurrentHandValue()
                || hasDealerBusted();
    }

    /**
     * Boolean function to check if the game ended in a tie
     * @return false otherwise
     */
    private boolean hasPlayerTied(Player player) {
        return player.getCurrentHandValue() == dealer.getCurrentHandValue();
    }

    /**
     * This function is used to update the values of the hashmaps with their appropriate status
     * @param player the current player
     * @param status the players status
     */
    private void updateOutcomes(Player player, Outcome status) {
        outcomes.put(player.getName(),status);
    }

    /**
     * Getter function for the hashmap
     * @return the outcomes hashmaps
     */
    public HashMap<String,Enum> getOutcomes() {
        return outcomes;
    }

    /**
     * Enum function for the possible outcomes
     */
    enum Outcome {
        WIN, LOSE, TIE
    }
    
}


