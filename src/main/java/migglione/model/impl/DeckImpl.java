package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Cards;
import migglione.model.api.Deck;

public class DeckImpl implements Deck {

    private final Cards cards = new Cards();
    private final List<Integer> deck = new ArrayList<>();

    public DeckImpl() {
        createDeck();
    }

    private void createDeck() {
        for (var key : cards.getCards().keySet()) {
            deck.add(key);
        }
    }

    @Override
    public void shuffle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shuffle'");
    }

    @Override
    public List<Integer> getCards() {
        return deck;
    }
    
}
