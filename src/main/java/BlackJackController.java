import model.BlackJackModel;
import model.Dealer;
import model.Outcome;
import model.Player;
import view.BlackJackView;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * This is the controller for the BlackJack Game. It handles the communication between the models
 * and the views in a game of BlackJack.
 */

public class BlackJackController {

    private final BlackJackView view;
    private BlackJackModel model;
    private final Scanner keyboard;
    private Dealer dealer;
    private int numPlayers;

    /**
     * This no-argument constructor initializes a new game.
     * @throws InterruptedException when the thread is interrupted due to sleeping.
     */
    public BlackJackController() throws InterruptedException {
        this.view = new BlackJackView();
        this.keyboard = new Scanner(System.in);
        startGame();
    }

    /**
     * The startGame method communicates with the view to print the instructions
     * and is responsible for calling the other initialization methods:
     * setUpPlayers(), createPlayers(), setDealer().
     * Once those are initialized, it calls the playGame() method.
     * If a player chooses to quit, it quits the game.
     */
    private void startGame() {
        try {
            this.view.printInstructions();
            String userInput = getUserInputAsString();
            if (!userInput.equalsIgnoreCase("Q")) {
                setUpPlayers();
                createPlayers();
                setDealer();
                playGame();
            } else {
                quitGame();
            }
        }
        catch (Exception e) {
            view.print("Something went wrong in startGame.");
        }
    }

    /**
     * This private method communicates with the view to get a valid number of players for the game.
     * The game currently supports 1-5 players playing against a dealer.
     */
    private void setUpPlayers() {
        view.printGetNumberOfPlayers();
        int numberOfPlayers = keyboard.nextInt();
        while (numberOfPlayers < 1 || numberOfPlayers > 5) {
            view.printGetNumberOfPlayers();
            numberOfPlayers = keyboard.nextInt();
        }
        this.keyboard.nextLine();
        this.numPlayers = numberOfPlayers;
        this.view.printConfirmationOfNumberPlayers(this.numPlayers);
    }

    /**
     * This private method communicates with the view to print requests for the player names.
     * It then collects that information and adds it to an ArrayList. Upon getting all player
     * names, it will initialize the BlackJackModel with the names from the user.
     */
    private void createPlayers() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < this.numPlayers; i++) {
            this.view.getPlayerName(i + 1);
            String playerName = getUserInputAsString();
            while (names.contains(playerName)) {
                view.printGetNonDuplicateName(playerName);
                playerName = getUserInputAsString();
            }
            playerName = playerName.substring(0,1).toUpperCase() + playerName.substring(1);
            names.add(playerName);
        }
        this.model = new BlackJackModel(names);
        this.view.printPlayerNameConfirmation(names);
    }

    /**
     * This private helper method collects user input and returns it to the calling method.
     * @return the user's input (as a string).
     */
    private String getUserInputAsString() {
        return keyboard.next().toLowerCase();
    }

    /**
     * This private method sets the dealer object from the BlackJackGame model
     * to make the code more readable.
     */
    private void setDealer() {
        this.dealer = this.model.getDealer();
    }

    /**
     * This private method processes a players turn based on the value of their hand.
     * If a player has a blackjack or has busted, their turn immediately ends.
     * Otherwise, the view is called to print the menu, and the processPlayerInput()
     * method is called to determine what the user decides.
     * @param player the player whose turn it is.
     */
    private void processPlayerTurn(Player player) {
        try {
            this.view.displayPlayerHand(player);
            if (player.hasBlackjack()) {
                this.view.printPlayerBlackjackDialog();
                TimeUnit.SECONDS.sleep(2);
                return;
            }
            if (player.isOver21()) {
                this.view.printPlayerBustDialog(player);
                return;
            }
            this.view.printHitOrStandDialog(player.getName());
            processPlayerInput(player);
        }
        catch (Exception e) {
            view.print("Something went wrong in processPlayerTurn().");
        }
    }

    /**
     * This private method processes a player's input when they decide whether
     * they would like to hit, stand, or quit the game.
     * @param player the player who is playing.
     */
    private void processPlayerInput(Player player) {
        try {
            String userInput = getUserInputAsString();
            if (userInput.equalsIgnoreCase("Q")) {
                quitGame();
            } else if (userInput.equalsIgnoreCase("H")) {
                playerHits(player);
            } else if (userInput.equalsIgnoreCase("S")) {
                playerStands(player);
            } else {
                this.view.printInvalidInput();
                processPlayerTurn(player);
            }
        }
        catch (Exception e) {
            view.print("Something went wrong in processPlayerInput.");
        }
    }

    /**
     * This private method handles a player 'hitting' to get a new card.
     * @param player who is hitting.
     */
    private void playerHits(Player player) {
        try {
            this.view.printNewCard(model.getDeck().getCard(0), true);
            this.model.playerHit(player);
            processPlayerTurn(player);
        }
        catch (Exception e) {
            view.print("Something went wrong in playerHits.");
        }
    }

    /**
     * This private method calls the view to inform the player of their
     * current Hand.
     * @param player who is standing.
     */
    private void playerStands(Player player) {
        this.view.printStandDialog(player.getName());
        this.view.displayPlayerHand(player);
    }

    /**
     * This private method processes the dealer's turn which goes after all other
     * players.
     */
    private void processDealerTurn()  {
        try {
            while (dealer.canHit()) {
                this.view.displayDealerLastHand(dealer);
                dealerHits();
            }
            TimeUnit.SECONDS.sleep(2);
            this.view.printDealerTurnOverDialog(dealer);
        }
        catch (Exception e) {
            view.print("Something went wrong in processDealerTurnHits.");
        }
    }

    /**
     * This private method processes the dealer's 'hitting' to get a new card.
     */
    private void dealerHits() {
        this.model.playerHit(this.dealer);
    }

    /**
     * This method handles the playing of the game and calls the methods in
     * the model and the view to communicate the status of the game as desired.
     */
    private void playGame() {
        try {
            view.printStartGameDialog();
            this.view.displayDealerHand(dealer);
            for (Player player : this.model.getPlayers()) {
                view.printPlayerTurn(player.getName());
                processPlayerTurn(player);
            }
            processDealerTurn();
            view.printGameOverDialog();
            TimeUnit.SECONDS.sleep(2);
            processOutcomes();
            quitGame();
        }
        catch (Exception e) {
            view.print("Something went wrong in playGame().");
            quitGame();
        }
    }

    /**
     * This method processes the outcome of the game and sends the results to the view.
     */
    private void processOutcomes() {
        HashMap<String, Enum> outcomes = this.model.getOutcomes();
        Set<String> winnerNames = new HashSet<>();
        Set<String> loserNames = new HashSet<>();
        Set<String> tieNames = new HashSet<>();
        for (Map.Entry<String, Enum> entry : outcomes.entrySet()) {
            if(entry.getValue().equals(Outcome.WIN)) {
                winnerNames.add(entry.getKey());
            }
            else if(entry.getValue().equals((Outcome.LOSE))) {
                loserNames.add(entry.getKey());
            }
            else if(entry.getValue().equals((Outcome.TIE))) {
                tieNames.add(entry.getKey());
            }
        }
        view.printResults(winnerNames, loserNames, tieNames);
    }

    /**
     * This method handles the game quitting.
     */
    private void quitGame() {
        view.printUponQuit();
        System.exit(0);
    }

    public static void main(String[] args) throws InterruptedException {
        new BlackJackController();
    }

}
