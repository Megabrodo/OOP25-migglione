package migglione;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import migglione.model.api.Deck;
import migglione.model.api.CardDraw;
import migglione.model.impl.Card;
import migglione.model.impl.Cards;
import migglione.model.impl.DeckImpl;
import migglione.model.impl.StandardCardDrawImpl;

public class StandardCardDrawTest {

    private final Deck deck = new DeckImpl();
    private final CardDraw standardDraw = new StandardCardDrawImpl(deck);
    private final Cards database = new Cards();

    @Test
    void drawSameCardsInDeck() {
        List<Card> cardsInDeck = new ArrayList<>(deck.getDeck()); 
        List<Card> cardsDraw = new ArrayList<>(standardDraw.getRemainingCards());
    
        assertEquals(
            cardsInDeck.stream().map(c -> c.getName()).sorted().toList(),
            cardsDraw.stream().map(c -> c.getName()).sorted().toList());
    }

    @Test
    void checkDrawRemovesCard() {
        HashSet<Card> beforeDraw = new HashSet<>(standardDraw.getRemainingCards());
        standardDraw.getCard();
        HashSet<Card> afterDraw = new HashSet<>(standardDraw.getRemainingCards());

        assertNotEquals(beforeDraw, afterDraw);
        assertNotEquals(beforeDraw.size(), afterDraw.size());
    }

    @Test
    void removedObjectIsCard() {
        Card removedCard = standardDraw.getCard();
        Set<String> cardsNames = database.getCards().values()
            .stream()
            .map(c -> c.getName())
            .collect(Collectors.toSet());

        assertTrue(cardsNames.contains(removedCard.getName()));
    }
}
