import java.util.ArrayList;

/**
 * This class creates the view object for a BlackJack game.
 */

public class BlackJackView {

    ArrayList<Player> players;

    public BlackJackView() {
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
     * This method sets the number of players with a valid number based on user input.
     */
    void getNumberOfPlayers() {
        System.out.println("How many players will be playing (between 1 and 5)?");
    }

    void printConfirmationOfNumberPlayers(int numPlayers) {
        System.out.println("A BlackJack game has been created for " + numPlayers + "of players.");
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

//    /**
//     * This method prints the hand information to the console.
//     * @param player the player whose hand is getting printed.
//     */
//    public void displayHand(AbstractPlayer player) {
//        createVisualHand(player);
//        if player.getClass() == Dealer.class) {
//            displayDealerHand(player);
//        }
//        else if (player.getClass() == Player.class) {
//            displayPlayerHand(player);
//        }
//    }
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

    public void printDealerTurnOverDialog() {
        System.out.println("The Dealer's turn is over.");
    }

    void printInvalidInput() {
        System.out.println("That input was invalid. Valid inputs are 'H', 'S', or 'Q'.");
    }

    void printStandInformation() {
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

    public void printBust(Player player) {
        String bust =  """
            ____  _    _  _____ _______ 
           |  _ \\| |  | |/ ____|__   __|
           | |_) | |  | | (___    | |   
           |  _ <| |  | |\\___ \\   | |   
           | |_) | |__| |____) |  | |   
           |____/ \\____/|_____/   |_| 
    """;
        System.out.println(bust);
        System.out.println("Whoops! " + player.getName() + " bust.");
    }

    private String getASCIIARTOutcome(Outcome outcome) {
        String bust =  """
            ____  _    _  _____ _______ 
           |  _ \\| |  | |/ ____|__   __|
           | |_) | |  | | (___    | |   
           |  _ <| |  | |\\___ \\   | |   
           | |_) | |__| |____) |  | |   
           |____/ \\____/|_____/   |_| 
        """;
        String tie = """
             _______ _      
            |__   __(_)     
               | |   _  ___ 
               | |  | |/ _ \\
               | |  | |  __/
               |_|  |_|\\___|
             }
        """;
        String win = """
            _____                            _       _ 
          / ____|                          | |     | |
         | |     ___  _ __   __ _ _ __ __ _| |_ ___| |
         | |    / _ \\| '_ \\ / _` | '__/ _` | __/ __| |
         | |___| (_) | | | | (_| | | | (_| | |_\\__ \\_|
          \\_____\\___/|_| |_|\\__, |_|  \\__,_|\\__|___(_)
                             __/ |                    
                            |___/    
         """;
        return switch (outcome) {
            case outcome.BUST -> bust;
            case outcome.WIN -> win;
            case outcome.TIE -> tie;
            default -> "No ASCII ART FOR OUTCOME";
        };
    }


    /**
     * This method prints the winner information to the dialog.
     * @param player the winner of the game.
     */
    public void printWinners(AbstractPlayer player) {
        String gameOver = """
                  _____                                                                               
                 / ____|                                           
                | |  __  __ _ _ __ ___   ___    _____   _____ _ __ 
                | | |_ |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|
                | |__| | (_| | | | | | |  __/ | (_) \\ V /  __/ |   
                 \\_____|\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|             
                          """;
        if (player.getClass().equals(Dealer.class)) {
            System.out.println(gameOver);
        }
        else {
//            System.out.println(congrats);
//            System.out.println(player.name() + "Beat the dealer!");
        }
    }
}
