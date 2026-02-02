package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import migglione.model.api.Cards;
import migglione.model.api.Deck;

public class DeckImpl implements Deck {

    private final Cards cards = new Cards();
    private final List<Integer> deck = new ArrayList<>();
    private final Random random;

    public DeckImpl() {
        createDeck();
        random = new Random();
    }

    private void createDeck() {
        cards.getCards().keySet().stream().forEach(deck::add);
    }

    @Override
    public void shuffle() {
        List<Integer> temp = new ArrayList<>();
        int deckSize = deck.size();
        int n = random.nextInt(deckSize);

        for (int i = deck.size(); i > 0; i--) {
            temp.addLast(deck.get(n));
            deck.remove(n);
            n = random.nextInt(deckSize);
        }

        deck.addAll(temp);
    }

    @Override
    public List<Integer> getCards() {
        return deck;
    }
}
