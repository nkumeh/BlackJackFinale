package view;

import model.*;

import java.util.ArrayList;
import java.util.Set;

/**
 * This class creates the view object for a BlackJack game.
 */

public class BlackJackView {

    public BlackJackView() {
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
     * This method sets the number of players with a valid number based on user input.
     */
    public void printGetNumberOfPlayers() {
        System.out.println("How many players will be playing (between 1 and 5)?");
    }

    /**
     * This method sets the number of players with a valid number based on user input.
     */
    public void printGetNonDuplicateName(String playerName) {
        System.out.println(playerName +" has already been entered. Please enter a unique name.");
    }

    public void printConfirmationOfNumberPlayers(int numPlayers) {
        System.out.println("A BlackJack game has been created for " + numPlayers + " player(s).");
    }


    public void getPlayerName(int playerNumber) {
        System.out.println("What is Player " + playerNumber + "'s Name? (Please make it unique).");
    }

    public void printPlayerNameConfirmation(ArrayList<String> playerNames) {
        System.out.println("The following players will be playing BlackJack:");
        for (String playerName : playerNames) {
            System.out.println(playerName);
        }
    }


    public void getUniqueNameAfterDuplicate(int playerNumber) {
        System.out.println("I'm sorry, that name has already been entered. Please enter a unique name for Player "
                + playerNumber + " .");
    }

    private VisualHand createVisualHand(AbstractPlayer player) {
        VisualHand currentHand;
        if (player.getClass().equals(Dealer.class)) {
            currentHand = new VisualHand(player.getHand(), false);
        }
        else {
            currentHand = new VisualHand(player.getHand(), true);
        }
        return currentHand;
    }

    private VisualHand createDealerTurnHand(Dealer dealer) {
        return new VisualHand(dealer.getHand(), true);
    }

    public void displayDealerLastHand(Dealer dealer) {
        System.out.println("The Dealer's Hand\n");
        System.out.println(createDealerTurnHand(dealer));
    }

    public void displayDealerHand(Dealer dealer) {
        System.out.println("The Dealer's Hand\n");
        VisualHand dealersHand = createVisualHand(dealer);
        System.out.println(dealersHand);
    }

    public void displayPlayerHand(Player player) {
        System.out.println(player.getName() + "'s Hand\n");
        VisualHand playerHand = createVisualHand(player);
        System.out.println(playerHand);
    }

    public void displayHandValue(Player player) {
        System.out.println("Hand Value: " + player.getCurrentHandValue());
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

    public void printPlayerBlackjackDialog() {
        System.out.println("Congrats, you have Blackjack (or 21)!");
    }

    public void printBust() {
        System.out.println("Whoops! You bust! ");
    }

    public void printDealerTurnOverDialog(Dealer dealer) {
        displayDealerLastHand(dealer);
        System.out.println("The Dealer's turn is over.");
    }

    public void printInvalidInput() {
        System.out.println("That input was invalid. Valid inputs are 'H', 'S', or 'Q'.");
    }

    public void printStandInformation() {
        System.out.println("Your turn is over. Here's your current hand:");
    }

    /**
     * This method prints the new card that was drawn. This may be unnecessary.
     */
    public void printNewCard(Card card, boolean isVisible) {
        VisualCard newCard = new VisualCard(card, isVisible);
        System.out.println("The new card is:");
        System.out.println(newCard.toString());
    }

    private String getASCIIARTOutcome(String outcome) {
        String loser =  """
            _                     
           | |                    
           | | ___  ___  ___ _ __ 
           | |/ _ \\/ __|/ _ \\ '__|
           | | (_) \\__ \\  __/ |   
           |_|\\___/|___/\\___|_| 
        """;
        String tie = """
            _   _      
           | | (_)     
           | |_ _  ___ 
           | __| |/ _ \\
           | |_| |  __/
            \\__|_|\\___|
        """;
        String winner = """
                     _                       
                    (_)                      
           __      ___ _ __  _ __   ___ _ __ 
           \\ \\ /\\ / / | '_ \\| '_ \\ / _ \\ '__|
            \\ V  V /| | | | | | | |  __/ |   
             \\_/\\_/ |_|_| |_|_| |_|\\___|_|   
                                                            
              
         """;
        return switch (outcome) {
            case "loser" -> loser;
            case "winner" -> winner;
            case "tie" -> tie;
            default -> "No ASCII ART FOR OUTCOME";
        };
    }


    public void printResults(Set<String> winners, Set<String> losers, Set<String> ties) {
        String gameOver = """
                  _____                                                                               
                 / ____|                                           
                | |  __  __ _ _ __ ___   ___    _____   _____ _ __ 
                | | |_ |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|
                | |__| | (_| | | | | | |  __/ | (_) \\ V /  __/ |   
                 \\_____|\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|             
                          """;
        System.out.println(gameOver);
        System.out.println("\n");
        displayPlayerResults(winners, "winner");
        displayPlayerResults(ties, "tie");
        displayPlayerResults(losers, "loser");
    }

    private void displayPlayerResults(Set<String> playerNames, String outcome) {
        if (playerNames.size() > 0) {
            System.out.println(getASCIIARTOutcome(outcome));
            System.out.println("The " + outcome + "(s): ");
            for (String playerName: playerNames) {
                System.out.print(playerName + "\t");
            }
        }
    }
}
