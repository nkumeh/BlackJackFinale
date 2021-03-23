/**
 * A Player class that extends from an Abstract player.
 */
public class Player extends AbstractPlayer{

    /**
     * Player constructor calls Abstract constructor. Sets empty Hand and a
     * currentHandValue value of 0.
     */
    public Player() {
        super();
    }

    /**
     * Player constructor calls Abstract constructor. Sets hand to the provided
     * hand and calculates the currentHandValue.
     * @param dealtHand Hand object
     */
    public Player(Hand dealtHand) {
        super(dealtHand);
    }

    /**
     * A player can hit if their total is under 21. Takes the top card from the
     * deck and adds it to the player's hand. Recalculates the currentHandValue.
     * @param deck the Deck of cards being played with
     * @throws IllegalStateException if the currentHandValue is over 21
     */
    @Override
    public void hit(Deck deck) {
        if (this.isOver21())
            throw new IllegalStateException("Cannot hit if over 21.");
        Card topCard = deck.takeTopCard();
        this.getHand().add(topCard);
        calculateHandValue();
    }
}
