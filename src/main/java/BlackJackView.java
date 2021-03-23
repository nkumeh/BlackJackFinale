import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class creates the view object for a BlackJack game.
 */

public class BlackJackView {

    //this will be removed when we have the player class built
    public enum Player {
        DEALER,
        PERSON
    }

    Player dealer;
    ArrayList<Player> players;
    Scanner keyboard;

    public BlackJackView() {
        this.dealer = Player.DEALER;
        players = new ArrayList<>();
    }

    /**
     * This method prints the game instructions.
     */
    public void printInstructions() {
        String instructions = """
            Welcome to the Game of BlackJack!
            
            Each player will receive two cards. The goal is to get as close to 21 points without going over. 
            If you go over 21 points, you lose the game. 
            Aces can be worth either 1 point or 11 points. 
            Press these keys + enter:
                - 'H' to hit and get another card from the dealer. 
                - 'S' to stand and finish your turn.
                - 'Q' to quit the game. 
            Are you ready to play? Press any key except 'Q' (+ enter) to get started!
            """;
        System.out.println(instructions);
    }

    /**
     * This method sets up the game with the number of players.
     * @param userInput the input received from the user.
     */
    private void setUpGame(String userInput) {
        if (userInput.equalsIgnoreCase("Q")) {
            System.exit(0);
        }
        else {
            setNumberOfPlayers();
        }
    }


    /**
     * This method sets the number of players with a valid number based on user input.
     */
    void getNumberOfPlayers() {
        System.out.println("How many players will be playing (between 1 and 5)?");
    }

    /** ADDING METHODS FOR CREATING PLAYERS
     *
     */
    public void getPlayerName(int playerNumber) {
        System.out.println("What is Player " + playerNumber + "'s Name?");
    }
//
//    this method will need to get player objects from  the controller
//    public void addPlayer(ArrayList<Players> players) {
//        players = players;
//    }

    /**
     * This method prints the hand information to the console.
     * @param hand a Hand object
     * @param currentValue the current value of the Hand object.
     * @param player the player whose hand is getting printed.
     */
    public void printHand(Hand hand, int currentValue, Player player) {
        VisualHand currentHand;
        if (player.equals(Player.DEALER)) {
            currentHand = new VisualHand(hand, false);
        }
        else {
            currentHand = new VisualHand(hand, true);
        }
        System.out.println(player.name() + "'s Hand\n");
        System.out.println("Hand Value: " + currentValue);
        System.out.println(currentHand);
    }

    /**
     * This method prints the dialog to direct a player to hit or stand.
     */
    public void printHitOrStandDialog() {
        String options = """
                Would you like to hit or stand?
                Press 'H' to hit | 'S' to stand.
                """;
        System.out.println(options);
    }

    /**
     * This method prints the new card that was drawn. This may be unnecessary.
     */
    public void printNewCard(Card card, boolean isVisible) {
        VisualCard newCard = new VisualCard(card, isVisible);
        System.out.println("The new card is:");
        System.out.println(newCard.toString());
    }

    /**
     * This method prints the winner information to the dialog.
     * @param player the winner of the game.
     */
    public void printWinner(Player player) {
        String congrats = """
                   _____                            _       _ 
                 / ____|                          | |     | |
                | |     ___  _ __   __ _ _ __ __ _| |_ ___| |
                | |    / _ \\| '_ \\ / _` | '__/ _` | __/ __| |
                | |___| (_) | | | | (_| | | | (_| | |_\\__ \\_|
                 \\_____\\___/|_| |_|\\__, |_|  \\__,_|\\__|___(_)
                                    __/ |                    
                                   |___/    
                          """;
        String gameOver = """
                  _____                                                                               
                 / ____|                                           
                | |  __  __ _ _ __ ___   ___    _____   _____ _ __ 
                | | |_ |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|
                | |__| | (_| | | | | | |  __/ | (_) \\ V /  __/ |   
                 \\_____|\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|             
                          """;
        if (player.equals(Player.DEALER)) {
            System.out.println(gameOver);
        }
        else {
            System.out.println(congrats);
            System.out.println("You did it! " + player.name() + ".");
        }
    }

    public static void main(String[] args) {
        BlackJackView view = new BlackJackView();
        Hand hand1 = new Hand(2);
        hand1.add(new Card(Suit.HEARTS, Name.TEN));
        hand1.add(new Card(Suit.SPADES, Name.ACE));
        view.printNewCard(new Card(Suit.SPADES, Name.TWO), true);
        view.printNewCard(new Card(Suit.SPADES, Name.FIVE), false);
        view.printHitOrStandDialog();
        view.printHand(hand1, 21, Player.PERSON);
        view.printWinner(Player.DEALER);
        view.printWinner(Player.PERSON);
    }
}
