package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the model for a blackjack game.
 */


public class BlackJackModel {

    private Deck deck;
    private final Dealer dealer;
    private final ArrayList<Player> players;
    private HashMap<String,Enum> outcomes;


    /**
     * The constructor for BlackJack takes a list of player names(as Strings) and
     * sets up the required components of the game.
     */
    public BlackJackModel(ArrayList<String> playerNames) {
        this.prepareDeck();
        this.players = new ArrayList<>();
        this.addPlayers(playerNames);
        this.dealer = new Dealer(dealInitialHand());
    }

    /**
     * This method creates and shuffles a deck for use in the game.
     */
    private void prepareDeck() {
        deck = new Deck();
        deck.shuffle();
    }

    /**
     * This helper method deals an initial hand to a player.
     * @return the hand of the player
     */
    private Hand dealInitialHand() {
        return new Hand(deck, 2);
    }


    /**
     * This method creates player objects from the names that were passed in to the
     * constructor and adds them to the list of players.
     * @param playerNames a list of Strings representing the player names.
     */
    private void addPlayers(ArrayList<String> playerNames) {
        for (String name : playerNames) {
            this.players.add(new Player(name, this.dealInitialHand()));
        }
    }

    /**
     * This method returns the players and their hands.
     */
    public ArrayList<Player> getPlayers() {
        ArrayList<Player> playerListCopy = new ArrayList<>();
        playerListCopy.addAll(this.players);
        return playerListCopy;
    }

    /**
     * This method returns the actual list of players in the game
     * versus a copy so that we can modify the hands for testing purposes.
     * @return an arraylist of Player objects.
     */
    ArrayList<Player> getPlayersForTesting() {
        return this.players;
    }

    /**
     * This is the getter method for the deck class and is used
     * primarily for testing.
     * @return the Deck that is being used in the game.
     */
    Deck getDeck() {
        return this.deck;
    }

    /**
     * This is the getter method for the Dealer object.
     * @return the Dealer object for the game.
     */
    public Dealer getDealer() {
        return this.dealer;
    }

    /**
     * This method accepts a Player or Dealer object. If the
     * player object can hit, it does so. If not, it catches
     * the exception which gets printed to the screen.
     * @param player an instance of an AbstractPlayer (player or dealer).
     */
    public void playerHit (AbstractPlayer player) {
        try {
            player.hit(this.deck);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * This function checks the criteria for winning / losing
     * for each player and calls the updateOutcomes method
     * to assign an outcome for each player.
     */
    public void getGameResults() {
        setUpOutcomes();
        for (Player player : players) {
            if (hasPlayerBusted(player)) {
                updateOutcomes(player, Outcome.LOSE);
            }
            else if (hasPlayerBusted(dealer)) {
                updateOutcomes(player, Outcome.WIN);
            }
            else if (hasPlayerWon(player)) {
                updateOutcomes(player, Outcome.WIN);
            }
            else if (hasPlayerTied(player)) {
                updateOutcomes(player, Outcome.TIE);
            }
            else {
                updateOutcomes(player, Outcome.LOSE);
            }
        }
    }

    /**
     * Getter function for the outcomes hashmap
     * @return the outcomes hashmap with results of the
     * game.
     */
    public HashMap<String,Enum> getOutcomes() {
        return outcomes;
    }

    /**
     * This function helps sets up the outcomes HashMap with
     * the name of the players and initializes the result to null.
     */
    private void setUpOutcomes() {
        outcomes = new HashMap<String,Enum>();
        for (Player player : this.players) {
            outcomes.put(player.getName(), null);
        }
    }

    /**
     * This private helper function assesses whether an instance of player
     * (Dealer or player) busted.
     * @return true if the player busted, else false.
     */
    private boolean hasPlayerBusted(AbstractPlayer player) {
        return player.getCurrentHandValue() > 21;
    }

    /**
     * This private helper function determines whether a player won the game.
     * @return true if the player won, else false.
     */
    private boolean hasPlayerWon(Player player) {
        return player.getCurrentHandValue() > dealer.getCurrentHandValue()
                || hasPlayerBusted(dealer);
    }

    /**
     * This private helper function determines whether a player tied with the dealer.
     * @return true if the player tied, else false.
     */
    private boolean hasPlayerTied(Player player) {
        return player.getCurrentHandValue() == dealer.getCurrentHandValue();
    }

    /**
     * This function is used to update the values of the hashmaps with
     * the results of the game.
     * @param player the current player
     * @param status the players status
     */
    private void updateOutcomes(Player player, Outcome status) {
        outcomes.put(player.getName(),status);
    }

}


