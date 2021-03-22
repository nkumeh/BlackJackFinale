public class Dealer extends AbstractPlayer {
    private static final int DEALER_MAX = 17;

    public Dealer() {
        super();
    }

    public Dealer(Hand dealtHand) {
        super(dealtHand);
    }

    public boolean canHit() {
        return this.getCurrentHandValue() < DEALER_MAX;
    }

    @Override
    public void hit(Deck deck) {
        while (canHit()) {
            if (this.isOver21())
                throw new IllegalStateException("Cannot hit if over 21.");
            Card topCard = deck.takeTopCard();
            this.getHand().add(topCard);
            calculateHandValue();
        }
    }

    @Override
    public void stand() {
        calculateHandValue();
    }

}
