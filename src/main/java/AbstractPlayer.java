

public abstract class AbstractPlayer {
    private Hand hand;
    private int currentHandValue;
    public static final int BLACKJACK = 21;


    public AbstractPlayer() {
        this.hand = new Hand();
        this.currentHandValue = 0;
    }

    public AbstractPlayer(Hand dealtHand) {
        this.hand = dealtHand;
        calculateHandValue();
    }

    public int getCurrentHandValue() {
        return this.currentHandValue;
    }

    public Hand getHand() {
        return this.hand;
    }

    protected boolean hasAce() {
        for (Card card : this.hand.getDeck()) {
            if (card.isAce())
                return true;
        }
        return false;
    }

    protected boolean isOver21() {
        return isOver21(this.currentHandValue);
    }

    protected boolean isOver21(int handValue) {
        return handValue > BLACKJACK;
    }

    private int calculateHardHandValue() {
        if (this.hasAce()) {
            return calculateSoftHandValue() + 10;
        } else {
            return calculateSoftHandValue();
        }
    }

    private int calculateSoftHandValue() {
        int softValue = 0;
        for (Card card : this.hand.getDeck())
            softValue += card.getValue();
        return softValue;
    }

    public void calculateHandValue() {
        if (isOver21(this.calculateHardHandValue()))
            this.currentHandValue = this.calculateSoftHandValue();
        else
            this.currentHandValue = this.calculateHardHandValue();
    }

    public abstract void hit(Deck deck);

    public abstract void stand();

}
