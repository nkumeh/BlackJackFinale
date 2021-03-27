package model;

/**
 * A Dealer class that extends from an Abstract player. A Dealer can only hit
 * while they have less than 17.
 */
public class Dealer extends AbstractPlayer {
    private static final int DEALER_MAX = 17;

    /**
     * Dealer constructor calls Abstract constructor. Sets empty Hand and a
     * currentHandValue value of 0.
     */
    public Dealer() {
        super();
    }

    /**
     * Dealer constructor calls Abstract constructor. Sets hand to the provided
     * hand and calculates the currentHandValue.
     * @param dealtHand Hand object
     */
    public Dealer(Hand dealtHand) {
        super(dealtHand);
    }

    /**
     * Returns true if the Dealer can hit, meaning if their currentHandValue is
     * under 17.
     * @return true if under 17, false otherwise
     */
    public boolean canHit() {
        return this.getCurrentHandValue() < DEALER_MAX;
    }

    /**
     * Hits for the dealer until the dealer busts or has a currentHandValue >= 17
     * @param deck the Deck of cards being played with
     * @throws IllegalStateException if the currentHandValue is over 21, in super.hit
     */
    @Override
    public void hit(Deck deck) throws IllegalStateException {
        if (canHit()) {
            super.hit(deck);
        }
    }
}
