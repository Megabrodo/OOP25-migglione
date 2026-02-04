package migglione;

import org.junit.jupiter.api.Test;

import migglione.model.api.Deck;
import migglione.model.api.CardDraw;
import migglione.model.impl.DeckImpl;
import migglione.model.impl.StandardCardDrawImpl;

public class CardDrawTest {
    
    private final CardDraw standardDraw = new StandardCardDrawImpl();
    private final Deck deck = new DeckImpl();

    @Test
    void drawSameCardsInDeck() {

    }
}
