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

    private void setUpOutcomes() {
        // i think intellij allows to declare like ths because outcomes is
        // declared at the beginning of the program
        outcomes = new HashMap<>();
        for (Player player : this.players) {
            outcomes.put(player.getName(), null);
        }
    }

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

    private boolean hasDealerBusted() {
        return dealer.getCurrentHandValue() > 21;
    }

    private boolean hasPlayerBusted(Player player) {
        return player.getCurrentHandValue() > 21;
    }

    private boolean hasPlayerWon(Player player) {
        return player.getCurrentHandValue() > dealer.getCurrentHandValue()
                || hasDealerBusted();
    }

    private boolean hasPlayerTied(Player player) {
        return player.getCurrentHandValue() == dealer.getCurrentHandValue();
    }

    private void updateOutcomes(Player player, Outcome status) {
        outcomes.put(player.getName(),status);
    }

    public HashMap<String,Enum> getOutcomes() {
        return outcomes;
    }

    enum Outcome {
        WIN, LOSE, TIE
    }

}


