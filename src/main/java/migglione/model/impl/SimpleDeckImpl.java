package migglione.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import migglione.model.api.Deck;

public class SimpleDeckImpl implements Deck {

    private final Cards cards = new Cards();
    private final List<Card> deck = new ArrayList<>();

    /**
     * The constructor of the class.
     * 
     * <p>
     * Since in this case the shuffling has
     * constraints and is fixed it simply
     * initializes the deck normally
     */
    public SimpleDeckImpl() {
        createDeck();
    }

    private void createDeck() {
        cards.getCards().values().stream().forEach(deck::add);
    }

    @Override
    public void shuffle() {
    }

    @Override
    public List<Card> getDeck() {
        return Collections.unmodifiableList(this.deck);
    }
}
