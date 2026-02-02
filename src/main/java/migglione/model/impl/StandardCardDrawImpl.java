package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Card;
import migglione.model.api.CardDraw;
import migglione.model.api.Cards;
import migglione.model.api.Deck;

public class StandardCardDrawImpl implements CardDraw {

    private final List<Integer> cards;
    private final Deck deck;
    private final Cards database;

    public StandardCardDrawImpl() {
        this.deck = new DeckImpl();
        this.database = new Cards();
        this.cards = new ArrayList<>(deck.getCards());
    }

    @Override
    public Card getCard() {
        return this.database.getCards().get(cards.removeFirst());
    }

    @Override
    public boolean isDeckEmpty() {
        return this.cards.isEmpty();
    }
}
