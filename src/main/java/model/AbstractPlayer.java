package model;

/**
 * Abstract class for a Player. Every Player has a Hand and a currentHandValue.
 */
public abstract class AbstractPlayer {
    private Hand hand;
    private int currentHandValue;
    public static final int BLACKJACK = 21;


    /**
     * Constructor for an Abstract Player.
     */
    public AbstractPlayer() {
        this.hand = new Hand();
        calculateHandValue();
    }

    /**
     * Constructor for an Abstract Player with a set hand. Used in Testing
     * @param dealtHand Hand to start with
     */
    public AbstractPlayer(Hand dealtHand) {
        this.hand = dealtHand;
        calculateHandValue();
    }

    /**
     * Returns the currentHandValue.
     * @return int - currentHandValue
     */
    public int getCurrentHandValue() {
        return this.currentHandValue;
    }

    /**
     * Returns the Hand object. Note that this returns a mutable Hand object,
     * because child classes should be able to change the Hand.
     * @return Hand object
     */
    public Hand getHand() {
        return this.hand;
    }

    /**
     * Returns if the current hand has an Ace. Helper method for calculations of
     * the currentHandValue.
     * @return true if there is an Ace, false otherwise
     */
    private boolean hasAce() {
        for (Card card : this.hand.getDeck()) {
            if (card.isAce())
                return true;
        }
        return false;
    }

    /**
     * Returns if the currentHandValue is over 21. Protected so child classes
     * can utilize the method in hit methods.
     * @return true if the currentHandValue is over 21, false otherwise
     */
    protected boolean isOver21() {
        return this.currentHandValue > BLACKJACK;
    }


    /**
     * Calculates the "Hard" hand value, meaning if there is an Ace in the Hand,
     * treat one of the Aces as an 11 rather than 1. Only 1 Ace in a Hand can
     * have the value of 11. However, all Aces in a hand can have the value of
     * 1.
     * @return integer of the Hard hand value
     */
    private int calculateHardHandValue() {
        if (this.hasAce()) {
            return calculateSoftHandValue() + 10;
        } else {
            return calculateSoftHandValue();
        }
    }

    /**
     * Calculates the "Soft" hand value. Treats all Aces as 1.
     * @return int of Soft hand value
     */
    private int calculateSoftHandValue() {
        int softValue = 0;
        for (Card card : this.hand.getDeck())
            softValue += card.getValue();
        return softValue;
    }

    /**
     * Calculates the hand value. If the Hard hand value is more than 21, use
     * the soft hand value (which could also be over 21). Otherwise, use the
     * hard hand value that treats one Ace in the Hand as 11 (if it exists in
     * the Hand). Updates the currentHandValue instance.
     */
    public void calculateHandValue() {
        if (isOver21())
            this.currentHandValue = this.calculateSoftHandValue();
        else
            this.currentHandValue = this.calculateHardHandValue();
    }

    /**
     * Abstract hit method that all children should have.
     * @param deck the Deck of cards being played with
     */
    public abstract void hit(Deck deck);

    /**
     * Method to stand. Does nothing.
     */
    public void stand() {
        // do nothing
    }
}
