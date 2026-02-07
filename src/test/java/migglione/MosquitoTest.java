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
        int moreThanEnoughCards = 5;
        for (int i = 0; i < moreThanEnoughCards; i++) {
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
        final Card bestCard = new Card("best", 9, 9, 9, 10, 10);
        final Card okCard = new Card("ok", 4, 1, 5, 3, 2);
        final Card worstCard = new Card("worst", 1, 0, 0, 0, 0);
        mosquitoPlayer.drawCard(bestCard);
        mosquitoPlayer.drawCard(okCard);
        mosquitoPlayer.drawCard(worstCard);
        mosquitoPlayer.setMyTurn(true);
        int bestAttr = mosquitoPlayer.playCard("placeholderBest", okCard);
        assertEquals(2, mosquitoPlayer.getHand().size());
        assertEquals(bestCard.getStealth(), bestAttr);
        bestAttr = mosquitoPlayer.playCard("placeholderOk", okCard);
        assertEquals(okCard.getStrength(), bestAttr);
        bestAttr = mosquitoPlayer.playCard("placeholderWorst", okCard);
        assertEquals(worstCard.getAttk(), bestAttr);
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
