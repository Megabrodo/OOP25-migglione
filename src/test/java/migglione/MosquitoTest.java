package migglione;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import migglione.model.impl.Card;
import migglione.model.impl.Mosquito;

/**
 * Responsible for testing the Mosquito class.
 * 
 * <p>
 * This test check the validity for: the cards
 * played by mosquito while synchronizing with the
 * game and not.
 */
class MosquitoTest {
    private final Mosquito mosquitoPlayer = new Mosquito(new ArrayList<>(), true);

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
        final Card bestCard = new Card("best", 9, 9, 9, 10, 10);
        final Card okCard = new Card("ok", 4, 1, 5, 3, 2);
        final Card worstCard = new Card("worst", 1, 0, 0, 0, 0);
        mosquitoPlayer.drawCard(bestCard);
        mosquitoPlayer.drawCard(okCard);
        mosquitoPlayer.drawCard(worstCard);
        mosquitoPlayer.setMyTurn(false);
        int bestAttr = mosquitoPlayer.playCard("Attk", okCard);
        assertEquals(bestCard.getAttk(), bestAttr);
        bestAttr = mosquitoPlayer.playCard("Deff", okCard);
        assertEquals(okCard.getDeff(), bestAttr);
        bestAttr = mosquitoPlayer.playCard("Stealth", okCard);
        assertEquals(worstCard.getStealth(), bestAttr);
    }

    @Test
    void playCardSynchronized() {
        final Card bestCard = new Card("best", 9, 9, 9, 10, 10);
        final Card okCard = new Card("ok", 4, 1, 5, 3, 2);
        final Card worstCard = new Card("worst", 1, 0, 0, 0, 0);
        mosquitoPlayer.drawCard(bestCard);
        mosquitoPlayer.drawCard(okCard);
        mosquitoPlayer.drawCard(worstCard);
        //mosquito goes first
        int bestAttr = mosquitoPlayer.playCard("Attk", okCard);
        assertEquals(bestCard.getStealth(), bestAttr);
        mosquitoPlayer.getPile(Collections.emptyList());
        assertEquals(false, mosquitoPlayer.getMyTurn());
        //mosquito goes second
        bestAttr = mosquitoPlayer.playCard("Deff", okCard);
        assertEquals(okCard.getDeff(), bestAttr);
        mosquitoPlayer.getPile(List.of(okCard, worstCard));
        assertEquals(true, mosquitoPlayer.getMyTurn());
        //goes first again
        bestAttr = mosquitoPlayer.playCard("Stealth", okCard);
        assertEquals(worstCard.getAttk(), bestAttr);
        mosquitoPlayer.getPile(List.of(worstCard, worstCard));
        assertEquals(true, mosquitoPlayer.getMyTurn());
    }
}
