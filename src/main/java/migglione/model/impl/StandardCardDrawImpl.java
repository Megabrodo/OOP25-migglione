package migglione.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import migglione.model.api.CardDraw;
import migglione.model.api.Deck;

/**
 * Standard implementation of CardDraw.
 * 
 * <p>
 * This rapresent the main method to draw cards from the deck,
 * ad is used in the main mode of the game.
 * 
 * <p>
 * When a player draws a card, it is removed from the set of
 * cards that can be extracted, so that the game can end
 * when there are no more cards in the deck and every player
 * has no cards in their hand.
 */
public final class StandardCardDrawImpl implements CardDraw {

    private final List<Card> cards;

    /**
     * Constructor of the class.
     * 
     * <p>
     * By initializing the deck, the constructor
     * makes that the cards that are able to be drawn
     * are the one shuffled in the chosen Deck implementation
     */
    public StandardCardDrawImpl(Deck deck) {
        this.cards = new ArrayList<>(deck.shuffle());
    }

    @Override
    public Card getCard() {
        return this.cards.removeFirst();
    }

    @Override
    public boolean isDeckEmpty() {
        return this.cards.isEmpty();
    }

    @Override
    public List<Card> getRemainingCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
