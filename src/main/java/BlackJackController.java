import model.BlackJackModel;
import model.Dealer;
import model.Outcome;
import model.Player;
import view.BlackJackView;

import java.util.*;


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
        int numberOfPlayers = keyboard.nextInt();
        while (numberOfPlayers < 1 || numberOfPlayers > 5) {
            System.out.println("Please enter a number between 1 and 5.");
            numberOfPlayers = keyboard.nextInt();
        }
        this.keyboard.nextLine();
        System.out.println("Assigned " + numberOfPlayers + " players.");
        this.numPlayers = numberOfPlayers;
        this.view.printConfirmationOfNumberPlayers(this.numPlayers);
    }

    private void createPlayers() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < this.numPlayers; i++) {
            this.view.getPlayerName(i + 1);
            String playerName = getUserInputAsString();
            while (names.contains(playerName)) {
                System.out.println("nonduplicate " + playerName);
                playerName = getNonDuplicateName(i+1);
            }
            System.out.println("About to add "+ playerName + " to list");
//            playerName = playerName.substring(0,1).toUpperCase() + playerName.substring(1);
            names.add(playerName);
        }
        System.out.println("Creating new model");
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
        // ADD if statement if the player has blackjack, go to the next turn
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
        this.view.printNewCard(model.getDeck().getCard(0), true);
        this.model.playerHit(player);
        this.view.displayHandValue(player);
        processPlayerTurn(player);
    }

    private void playerStands(Player player) {
        this.view.printStandInformation();
        this.view.displayPlayerHand(player);
    }

    private void processDealerTurn() {
        while (dealer.canHit()) {
            this.view.displayDealerHand(dealer);
            dealerHits(dealer);
        }
        this.view.printDealerTurnOverDialog();
    }

    private void dealerHits(Dealer dealer) {
        this.model.playerHit(dealer);
    }

    private void playGame() {
        ArrayList<Player> players = this.model.getPlayers();
        for (Player player : players) {
            processPlayerTurn(player);
        }
        processDealerTurn();
        processOutcomes();
        quitGame();
    }

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

    private void quitGame() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new BlackJackController();
    }

}
