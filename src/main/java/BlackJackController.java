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
        view.printGetNumberOfPlayers();
        int numberOfPlayers = keyboard.nextInt();
        while (numberOfPlayers < 1 || numberOfPlayers > 5) {
            view.printGetNumberOfPlayers();
            numberOfPlayers = keyboard.nextInt();
        }
        this.numPlayers = numberOfPlayers;
        this.view.printConfirmationOfNumberPlayers(this.numPlayers);
    }

    private void createPlayers() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < this.numPlayers; i++) {
            this.view.getPlayerName(i + 1);
            String playerName = getUserInputAsString();
            while (names.contains(playerName)) {
                view.printGetNonDuplicateName(playerName);
                playerName = getNonDuplicateName(i);
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
        return keyboard.next().toLowerCase();
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
        int lastCard = player.getHand().getHandSize() - 1;
        this.view.printNewCard(player.getHand().getCard(lastCard), true);
        this.view.displayHandValue(player);
        processPlayerTurn(player);
    }

    private void playerStands(Player player) {
        this.view.printStandInformation();
        this.view.displayPlayerHand(player);
    }

    private void processDealerTurn() {
        this.view.displayDealerHand(dealer);
        this.view.printDealerTurnOverDialog();
        dealerHits(dealer);
    }

    private void dealerHits(Dealer dealer) {
        int currentScore = dealer.getCurrentHandValue();
        processDealerTurn();
        if (currentScore < 17) {
            this.model.playerHit(dealer);
            processDealerTurn();
        }
    }

    private void playGame() {
        ArrayList<Player> players = this.model.getPlayers();
        for (Player player : players) {
            processPlayerTurn(player);
        }
        processDealerTurn();
        processOutcomes();
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
        BlackJackController newGame = new BlackJackController();
        newGame.startGame();
    }

}
