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
                Are you ready to play?
                Press any key except 'Q' (+ enter) to get started!
                """;
        print(instructions);
    }

    /**
     * This outputs the request for the number of players.
     */
    public void printGetNumberOfPlayers() {
        print("How many players will be playing (between 1 and 5)?");
    }

    /**
     * This method outputs the confirmation of the number of players.
     *
     * @param numPlayers the number of players entered by the user.
     */
    public void printConfirmationOfNumberPlayers(int numPlayers) {
        print("A BlackJack game has been created for " + numPlayers + " player(s).");
    }

    /**
     * This outputs the request for the player's name
     *
     * @param playerNumber The number of player that is being requested.
     */
    public void getPlayerName(int playerNumber) {
        print("What is Player " + playerNumber + "'s Name? (Please make it unique).");
    }

    /**
     * This method outputs the request for a non-duplicate name.
     */
    public void printGetNonDuplicateName(String playerName) {
        print(playerName + " has already been entered. Please enter a unique name.");
    }

    /**
     * This method outputs the confirmation of the player names.
     *
     * @param playerNames the ArrayList of names to be printed
     */
    public void printPlayerNameConfirmation(ArrayList<String> playerNames) {
        print("The following players will be playing BlackJack:");
        for (String playerName : playerNames) {
            print(playerName);
        }
        print("\n");
    }

    /**
     * This method prints a line separator and outputs the players name whose
     * turn it is.
     *
     * @param playerName the name of the player whose turn it is.
     */
    public void printPlayerTurn(String playerName) {
        print("*********************************************************\n");
        print(playerName + "'s Turn!");
    }

    /**
     * This method creates a visual representation of a player's hand of cards.
     *
     * @param player the player whose hand is being represented.
     * @return VisualHand object of the Player's hand.
     */
    private VisualHand createVisualHand(AbstractPlayer player) {
        VisualHand currentHand;
        if (player.getClass().equals(Dealer.class)) {
            currentHand = new VisualHand(player.getHand(), false);
        } else {
            currentHand = new VisualHand(player.getHand(), true);
        }
        return currentHand;
    }

    /**
     * This method creates a visual representation of a dealer's hand of cards.
     *
     * @param dealer the dealer to create a visual representation of cards.
     * @return the VisualHand of the dealer.
     */
    private VisualHand createDealerTurnHand(Dealer dealer) {
        return new VisualHand(dealer.getHand(), true);
    }

    /**
     * This method prints the dealer's cards with the information visible to the screen.
     *
     * @param dealer the dealer whose cards are being printed.
     */
    public void displayDealerLastHand(Dealer dealer) {
        print("The Dealer's Hand\n");
        displayHand(createDealerTurnHand(dealer));
    }

    /**
     * This method creates a new VisualHand that has the first card invisible.
     *
     * @param dealer the dealer whose cards are being printed.
     */
    public void displayDealerHand(Dealer dealer) {
        print("The Dealer's Hand\n");
        displayHand(createVisualHand(dealer));
    }

    /**
     * This method outputs a Player's hand and prints the value of the hand to the screen.
     *
     * @param player the Player whose hand is being printed.
     */
    public void displayPlayerHand(Player player) {
        displayHand(createVisualHand(player));
        print(player.getName() + "'s Hand has a value of " + player.getCurrentHandValue() + ".");
    }

    /**
     * This method prints the dialog to direct a player to hit or stand.
     */
    public void printHitOrStandDialog(String playerName) {
        String options = "Would you like to hit or stand " + playerName + "?\n" +
                "Press 'H' to hit | 'S' to stand.";
        print(options);
    }


    /**
     * This method outputs a message to start the game.
     */
    public void printStartGameDialog() {
        String letsPlay = """
                                                           
               ╦  ┌─┐┌┬┐┌─┐  ╔═╗┬  ┌─┐┬ ┬ ┬
               ║  ├┤  │ └─┐  ╠═╝│  ├─┤└┬┘ │
               ╩═╝└─┘ ┴ └─┘  ╩  ┴─┘┴ ┴ ┴  o
                             
                """;
        print(letsPlay);
    }

    /**
     * This method outputs a message when a player has a blackjack (hand of 21).
     */
    public void printPlayerBlackjackDialog() {
        String blackjack = """  
                   
                ╔╗ ┬  ┌─┐┌─┐┬┌─ ╦┌─┐┌─┐┬┌─ ┬
                ╠╩╗│  ├─┤│  ├┴┐ ║├─┤│  ├┴┐ │
                ╚═╝┴─┘┴ ┴└─┘┴ ┴╚╝┴ ┴└─┘┴ ┴ o
                                                         
                """;
        print(blackjack);
        print("Congrats, you have Blackjack!\n");
    }

    /**
     * This method outputs a message when a player busts (hand value > 21)
     *
     * @param player the player who busted.
     */
    public void printPlayerBustDialog(Player player) {
        String bust = """
                   
               ╔╗ ┬ ┬┌─┐┌┬┐
               ╠╩╗│ │└─┐ │
               ╚═╝└─┘└─┘ ┴ o o o
                    
                """;
        print(bust);
        print("Whoops! " + player.getName() + " busted at " + player.getCurrentHandValue() + ".");
    }

    /**
     * This method outputs a message when a dealer's turn has finished.
     *
     * @param dealer the dealer whose hand is being printed.
     */
    public void printDealerTurnOverDialog(Dealer dealer) {
        displayDealerLastHand(dealer);
        print("The Dealer's turn is over. The dealer's hand has a value of " + dealer.getCurrentHandValue() + ".");
    }

    /**
     * This method is called when a user's input is invalid and outputs
     * valid options to the screen.
     */
    public void printInvalidInput() {
        print("That input was invalid. Valid inputs are 'H', 'S', or 'Q'.");
    }

    /**
     * This method prints a turnover dialog for a player.
     *
     * @param playerName the player's name.
     */
    public void printStandDialog(String playerName) {
        print(playerName + "'s turn is over. The final hand is:");
    }

    /**
     * This method prints the new card that was drawn. This may be unnecessary.
     */
    public void printNewCard(Card card, boolean isVisible) {
        VisualCard newCard = new VisualCard(card, isVisible);
        print("The new card is:");
        print(newCard.toString());
    }

    /**
     * This method outputs the game over dialog.
     */
    public void printGameOverDialog() {
        String gameOver = """
                               
                ╔═╗  ┌─┐  ┌┬┐  ┌─┐  ╔═╗  ┬  ┬  ┌─┐  ┬─┐  ┬
                ║ ╦  ├─┤  │││  ├┤   ║ ║  └┐┌┘  ├┤   ├┬┘  │
                ╚═╝  ┴ ┴  ┴ ┴  └─┘  ╚═╝   └┘   └─┘  ┴└─  o
                                                               
                """;
        print(gameOver);
        print("********** Calculating the results **********\n");
    }

    /**
     * This method outputs the results of the game using the displayPlayerResults() method.
     *
     * @param winners the names of the winners (in a set).
     * @param losers  the names of the losers (in a set).
     * @param ties    the names of the ties (in a set).
     */
    public void printResults(Set<String> winners, Set<String> losers, Set<String> ties) {
        displayPlayerResults(winners, "winner");
        displayPlayerResults(ties, "tie");
        displayPlayerResults(losers, "loser");
    }

    /**
     * This method is called by printResults() and displays the ASCII art
     * via the getASCIIArtForOutcome() method as well as the names for that result.
     *
     * @param playerNames the names to print.
     * @param outcome     the Outcome to represent
     */
    private void displayPlayerResults(Set<String> playerNames, String outcome) {
        if (playerNames.size() > 0) {
            print(getASCIIArtForOutcome(outcome));
            print("The " + outcome + "(s): ");
            for (String playerName : playerNames) {
                print(playerName);
            }
        }
    }

    /**
     * This method returns the ASCII art string that represents a particular
     * outcome.
     *
     * @param outcome A string that accepts ("loser", "winner", and "tie".)
     * @return the ASCII art String for that outcome.
     */
    private String getASCIIArtForOutcome(String outcome) {
        String loser = """
                
                ╦  ┌─┐┌─┐┌─┐┬─┐┌─┐
                ║  │ │└─┐├┤ ├┬┘└─┐
                ╩═╝└─┘└─┘└─┘┴└─└─┘ o o o
                                                 
                """;
        String tie = """
                   
                ╔╦╗┬┌─┐┌─┐
                 ║ │├┤ └─┐
                 ╩ ┴└─┘└─┘
                                        
                """;
        String winner = """
        
                ╦ ╦  ┬  ┌┐┌  ┌┐┌  ┌─┐  ┬─┐  ┌─┐  ┬
                ║║║  │  │││  │││  ├┤   ├┬┘  └─┐  │
                ╚╩╝  ┴  ┘└┘  ┘└┘  └─┘  ┴└─  └─┘  o
                                                                     
                """;
        return switch (outcome) {
            case "loser" -> loser;
            case "winner" -> winner;
            case "tie" -> tie;
            default -> "No ASCII ART FOR OUTCOME";
        };
    }

    /**
     * This method outputs a thank you message to the screen when a game is finished.
     */
    public void printUponQuit() {
        print("\nThank you for Playing BlackJack!");
        print("If you would like to play another game, please restart the program.\n");
    }

    /**
     * This method creates an alias for System.out.println to make the code a
     * bit easier to read.
     *
     * @param message the message to print to the screen.
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * This method creates an alias for System.out.println to make the code a
     * bit easier to read for VisualHand objects.
     *
     * @param hand a VisualHand object to display
     */
    private void displayHand(VisualHand hand) {
        System.out.println(hand);
    }
}
