import java.util.Scanner;

/**
 * This is the controller for the BlackJack Game. It handles the communication between the model
 * and the
 */

public class BlackJackController {

    // will need to create a blackjack game (instance of the model).
    // that will have the players for the game.
    BlackJackView view;
    Scanner keyboard;

    public BlackJackController() {
        // create a new blackjack model
        BlackJackView view = new BlackJackView();
        Scanner keyboard = new Scanner(System.in);
    }

    public void startGame() {
        view.printInstructions();
        String userInput = keyboard.next();
        if (!userInput.equalsIgnoreCase("Q")) {
            setUpPlayer();
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

        }
    }


    private void createPlayer() {

    }

    private void processPlayerTurn() {
        // get current player from model
//        view.printHand(get player hand, get current value, player);
        view.printHitOrStandDialog();
        String userInput = keyboard.next();
        if (userInput.equalsIgnoreCase("Q")) {
            quitGame();
        }
        else if (userInput.equalsIgnoreCase("H")) {
            // code to get a new card
        else if (userInput.equalsIgnoreCase("S"))
        }

    }

    private void quitGame() {
        System.exit(0);
    }

}
