package model;

/**
 * Abstract class for a Player. Every Player has a Hand and currentHandValue.
 */
public abstract class AbstractPlayer {
    private final Hand hand;
    private int currentHandValue;
    public static final int BLACKJACK = 21;

    /**
     * Constructor for an Abstract Player.
     */
    public AbstractPlayer() {
        this.hand = new Hand();
        this.calculateHandValue();
    }

    /**
     * Constructor for an Abstract Player with a set hand. Used in Testing
     * @param dealtHand Hand to start with
     */
    public AbstractPlayer(Hand dealtHand) {
        this.hand = dealtHand;
        this.calculateHandValue();
    }

    /**
     * Returns the currentHandValue.
     * @return int - currentHandValue
     */
    public int getCurrentHandValue() {
        this.calculateHandValue();
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
    public boolean isOver21() {
        calculateHandValue();
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
            return this.calculateSoftHandValue() + 10;
        } else {
            return this.calculateSoftHandValue();
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
        this.currentHandValue = this.calculateHardHandValue();
        if (this.currentHandValue > 21)
            this.currentHandValue = this.calculateSoftHandValue();
    }

    /**
     * A player can hit if their total is under 21. Takes the top card from the
     * deck and adds it to the player's hand. Recalculates the currentHandValue.
     * @param deck the Deck of cards being played with
     * @throws IllegalStateException if the total is over 21
     */
    public void hit(Deck deck) throws IllegalStateException {
        if (this.currentHandValue >= 21)
            throw new IllegalStateException("Cannot hit if at or over 21.");
        Card topCard = deck.takeTopCard();
        this.getHand().add(topCard);
        this.calculateHandValue();
    }
}
