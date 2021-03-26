import model.BlackJackModel;
import model.Dealer;
import model.Player;
import view.BlackJackView;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * This is the controller for the BlackJack Game. It handles the communication between the model
 * and the
 */

public class BlackJackController {

    private final BlackJackView view;
    private BlackJackModel model;
    private final Scanner keyboard;
    private Dealer dealer;
    private int numPlayers;

    public BlackJackController() {
        this.view = new BlackJackView();
        this.keyboard = new Scanner(System.in);
        startGame();
    }

    public void startGame() {
        this.view.printInstructions();
        String userInput = getUserInputAsString();
        if (!userInput.equalsIgnoreCase("Q")) {
            setUpPlayers();
            createPlayers();
            setDealer();
            playGame();
        }
        else {
            quitGame();
        }
    }

    private void setUpPlayers() {
        this.view.getNumberOfPlayers();
        this.numPlayers = keyboard.nextInt();
        while (this.numPlayers < 1 || this.numPlayers > 5) {
            System.out.println("Please enter a number between 1 and 5.");
            this.numPlayers = keyboard.nextInt();
        }
        this.view.printConfirmationOfNumberPlayers(this.numPlayers);
    }

    private void createPlayers() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < this.numPlayers; i++) {
            this.view.getPlayerName(i + 1);
            String playerName = getUserInputAsString();
            while (names.contains(playerName)) {
                getNonDuplicateName(i);
            }
            playerName = playerName.substring(0,1).toUpperCase() + playerName.substring(1);
            names.add(playerName);
        }
        this.model = new BlackJackModel(names);
        this.view.printPlayerNameConfirmation(names);
    }

    private void setDealer() {
        this.dealer = this.model.getDealer();
    }

    private String getNonDuplicateName(int playerNumber) {
        this.view.getUniqueNameAfterDuplicate(playerNumber);
        return getUserInputAsString();
    }

    private String getUserInputAsString() {
        return keyboard.nextLine().toLowerCase();
    }

    private void processPlayerTurn(Player player) {
        this.view.displayPlayerHand(player);
        this.view.printHitOrStandDialog();
        String userInput = getUserInputAsString();
        if (userInput.equalsIgnoreCase("Q")) {
            quitGame();
        }
        else if (userInput.equalsIgnoreCase("H")) {
            playerHits(player);
        }
        else if (userInput.equalsIgnoreCase("S")) {
            playerStands(player);
        }
        else {
            this.view.printInvalidInput();
            processPlayerTurn(player);
        }
    }

    private void playerHits(Player player) {
        this.model.playerHit(player);
        this.view.printNewCard(player.getHand().getCard(-1), true);
        this.view.displayHandValue(player);
        processPlayerTurn(player);
    }

    private void playerStands(Player player) {
        this.view.printStandInformation();
        this.view.displayPlayerHand(player);
    }

    private void processDealerTurn() {
        this.view.displayDealerHand(dealer);
        dealerHits(dealer);
        this.view.printDealerTurnOverDialog();
    }

    private void dealerHits(Dealer dealer) {
        this.model.playerHit(dealer);
        processDealerTurn();
    }

    private void playGame() {
        ArrayList<Player> players = this.model.getPlayers();
        for (Player player : players) {
            processPlayerTurn(player);
        }
        processDealerTurn();
//        this.view.printWinner(player);
    }

    private void sendOutcomesToView() {
//        HashMap outcomes = this.model.getOutcomes();
    }

    private void quitGame() {
        System.exit(0);
    }

    public static void main(String[] args) {
        BlackJackController newGame = new BlackJackController();
//        newGame.startGame();
    }

}
