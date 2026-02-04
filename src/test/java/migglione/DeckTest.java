package migglione;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import migglione.model.api.Deck;
import migglione.model.impl.Card;
import migglione.model.impl.DeckImpl;

public class DeckTest {
    
    private final Deck deck = new DeckImpl();
    private final Deck deck2 = new DeckImpl();

    @Test
    void checkDeckNotEmpty() {
        assertFalse(deck.getDeck().isEmpty());
    }

    @Test
    void checkInitializedDecksWithSameCards() {
        List<Card> firstCardsList = new ArrayList<>(deck.getDeck());
        List<Card> secondCardsList = new ArrayList<>(deck2.getDeck());

        assertEquals(
            firstCardsList.stream().map(Card::getName).collect(Collectors.toSet()),
            secondCardsList.stream().map(Card::getName).collect(Collectors.toSet())
        );

        firstCardsList = new ArrayList<>(deck.shuffle());
        secondCardsList = new ArrayList<>(deck2.shuffle());

        assertEquals(
            firstCardsList.stream().map(Card::getName).collect(Collectors.toSet()),
            secondCardsList.stream().map(Card::getName).collect(Collectors.toSet())
        );
        assertEquals(firstCardsList.size(), secondCardsList.size());
    }

    @Test
    void randomizedShuffle() {
        List<Card> cards = new ArrayList<>(deck.getDeck());
        deck.shuffle();
        List<Card> cardsAfter = new ArrayList<>(deck.getDeck());

        assertNotEquals(cards, cardsAfter);
    }
}
