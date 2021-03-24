import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the model for a blackjack game.
 */


public class BlackJackModel {

    private Deck deck;
    private Dealer dealer;
    private ArrayList<Player> players;
    private HashMap outcomes;

    /**
     * The constructor for BlackJack takes a list of player names(as Strings) and
     * sets up the required components of the game.
     */
    public BlackJackModel(ArrayList<String> playerNames) {
        this.prepareDeck();
        this.addPlayers(playerNames);
        this.dealer = new Dealer();
        currentPlayer = this.players.get(0);
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
     * This helper method deals an initial hand to a player.
     * @return
     */
    private Hand dealInitialHand() {
        return new Hand(2);
    }

    public playerHit(Player player) {
        // if player currenthandvalue < 21
        // player.hit
        // else turn is over. change current player.
    }

    public void findWinners() {
        // Need to create Enum: Win, Lose, Tie
        // iterate through players list to get current hand value
        // if hasPlayerBusted()
            // updateOutcomes(player, LOSE)
        // else if hasDealerBusted()
            // updateOutcomes(dealer, LOSE)
        // else hasPlayerWon()
            // updateOutcomes(player, Won)
        // else hasPlayerTied()
            // updateOutcomes(player, TIE)
        // else
            // updateOutcomes(player, LOSE)

    }

    private boolean hasDealerBusted() {
//        dealer.getCurrentHandValue() > 21;
    }

    private boolean hasPlayerBusted(Player player) {
//        currentPlayer.getCurrentHandValue() > 21;
    }

    private boolean hasPlayerWon(Player player) {
        // currentPlayer.getCurrentHandValue() > dealer.getCurrentHandValue()
    }

    private boolean hasPlayerTied(Player player) {
        // currentPlayer.getCurrentHandValue() == dealer.getCurrentHandValue()
    }

    private void updateOutcomes(Player player, Outcome outcome) {
        // set Player value to outcome (Win, Lose, Tie)
    }

    public HashMap getOutcomes() {
//        return the Hashmap;
    }

}
