package migglione;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import migglione.model.api.CardDraw;
import migglione.model.impl.Card;
import migglione.model.impl.DeckImpl;
import migglione.model.impl.Mosquito;
import migglione.model.impl.StandardCardDrawImpl;

/**
 * Responsible for testing the Mosquito class.
 * 
 * <p>
 * This test check the validity for: 
 */
class MosquitoTest {
    private final Mosquito mosquitoPlayer = new Mosquito(new ArrayList<>(), true);
    private final CardDraw standardDraw = new StandardCardDrawImpl(new DeckImpl());

    @Test
    void drawToFull() {
        for (int i = 0; i < 5; i++) {
            mosquitoPlayer.drawCard(new Card("EmptyCard", 0, 0, 0, 0, 0));
        }
        assertEquals(3, mosquitoPlayer.getHand().size());
    }

    @Test
    void saveRightCards() {
        Card testCard = standardDraw.getCard();
        mosquitoPlayer.drawCard(testCard);
        assertEquals(mosquitoPlayer.getHand().get(0), testCard);
        testCard = standardDraw.getCard();
        mosquitoPlayer.drawCard(testCard);
        assertEquals(mosquitoPlayer.getHand().get(1), testCard);
        testCard = standardDraw.getCard();
        mosquitoPlayer.drawCard(testCard);
        assertEquals(mosquitoPlayer.getHand().get(2), testCard);
        mosquitoPlayer.drawCard(standardDraw.getCard());
        assertEquals(mosquitoPlayer.getHand().get(2), testCard);
    }

    @Test
    void playCardFirst() {
        Card bestCard = new Card("best", 10, 10, 10, 10, 10);
        Card okCard = new Card("ok", 4, 5, 5, 3, 5);
        Card worstCard = new Card("worst", 1, 0, 0, 0, 0);
        mosquitoPlayer.drawCard(bestCard);
        mosquitoPlayer.drawCard(okCard);
        mosquitoPlayer.drawCard(worstCard);
        mosquitoPlayer.setMyTurn(true);
        int bestAttr = mosquitoPlayer.playCard("placeholder", okCard);
        assertEquals(2, mosquitoPlayer.getHand().size());
        assertEquals(10, bestAttr);
        bestAttr = mosquitoPlayer.playCard("placeholder", okCard);
        assertEquals(5, bestAttr);
        bestAttr = mosquitoPlayer.playCard("placeholder", okCard);
        assertEquals(1, bestAttr);
    }

    @Test
    void playCardSecond() {
        mosquitoPlayer.drawCard(new Card("Card1", 1, 1, 1, 1, 1));
        mosquitoPlayer.drawCard(new Card("Card2", 2, 2, 2, 2, 2));
        mosquitoPlayer.drawCard(new Card("Card3", 3, 3, 3, 3, 3));
        mosquitoPlayer.setMyTurn(true);
        //assertEquals();
    }
}
