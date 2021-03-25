package model;

/**
 * A Player class that extends from an Abstract player.
 */
public class Player extends AbstractPlayer{

    private String name;

    /**
     * Player constructor calls Abstract constructor. Sets empty Hand and a
     * currentHandValue value of 0.
     * @param name the name of the Player
     */
    public Player(String name) {
        super();
        this.name = name;
    }

    /**
     * Returns the name of the player.
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Player constructor calls Abstract constructor. Sets hand to the provided
     * hand and calculates the currentHandValue.
     * @param name the name of the Player
     * @param dealtHand Hand object
     */
    public Player(String name, Hand dealtHand) {
        super(dealtHand);
        this.name = name;
    }

}
