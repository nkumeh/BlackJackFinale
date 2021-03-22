public class Player extends AbstractPlayer{

    public Player() {
        super();
    }

    public Player(Hand dealtHand) {
        super(dealtHand);
    }

    @Override
    public void hit(Deck deck) {
        if (this.isOver21())
            throw new IllegalStateException("Cannot hit if over 21.");
        Card topCard = deck.takeTopCard();
        this.getHand().add(topCard);
        calculateHandValue();
    }

    @Override
    public void stand() {
        calculateHandValue();
    }

}
