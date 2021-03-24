import java.util.Scanner;
import view.BlackJackView;


/**
 * This is the controller for the BlackJack Game. It handles the communication between the model
 * and the
 */

public class BlackJackController {

    // will need to create a blackjack game (instance of the model).
    // that will have the players for the game.
    private BlackJackView view;
    private Scanner keyboard;

    public BlackJackController() {
        // create a new blackjack model
        view = new BlackJackView();
        keyboard = new Scanner(System.in);
    }

    public void startGame() {
        view.printInstructions();
        String userInput = keyboard.next();
        if (!userInput.equalsIgnoreCase("Q")) {
            setUpPlayers();
        }
        else {
            quitGame();
        }
    }

    private void setUpPlayers() {
        view.getNumberOfPlayers();
        int numPlayers = keyboard.nextInt();
        while (numPlayers < 1 || numPlayers > 5) {
            System.out.println("Please enter a number between 1 and 5.");
            numPlayers = keyboard.nextInt();
        }
        //send number of players to blackjack model
        for (int i = 0; i < numPlayers; i++) {
            view.getPlayerName(i + 1);
            String playerName = keyboard.nextLine();
            // create player objects through game or directly?
            // add them to the game?
        }
    }


    private void processPlayerTurn() {
        // get current player from model
//        view.printHand(get player hand, get current value, player);
        view.printHitOrStandDialog();
        String userInput = keyboard.next();
        if (userInput.equalsIgnoreCase("Q")) {
            this.quitGame();
        }
        else if (userInput.equalsIgnoreCase("H")) {
            this.hit();
        }
        else if (userInput.equalsIgnoreCase("S")) {
            this.stand();
        }
        else {
            view.printInvalidInput();
            processPlayerTurn();
        }
    }

    private void hit() {
        // send code to model to hit.
        // Maybe print new card.
        // view.printHand(get player hand, get current value, player);
        // if player busts, hit bust
        processPlayerTurn();
    }

    private void stand() {
        view.printStandInformation();
        // view.printHand(get player hand, get current value, player);
        // send info back to the blackjack model.
    }

    private void processDealerTurn() {
        // code to have dealer take a turn
    }

    private void playGame() {
        // iterate through players
        // have players take their turn.
        // have dealer take it's turn.
//        view.printWinner(player);
    }

    private void quitGame() {
        System.exit(0);
    }

}
