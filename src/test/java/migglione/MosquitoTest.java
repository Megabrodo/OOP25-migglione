package migglione;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import migglione.model.impl.Card;
import migglione.model.impl.Mosquito;

/**
 * Responsible for testing the Mosquito class.
 * 
 * <p>
 * This test check the validity for: 
 */
class MosquitoTest {
    private final Mosquito mosquitoPlayer = new Mosquito(new ArrayList<>(), true);

    @Test
    void drawToFull() {
        for (int i = 0; i < 5; i++) {
            mosquitoPlayer.drawCard(new Card("EmptyCard", 0, 0, 0, 0, 0));
        }
        assertEquals(3, mosquitoPlayer.getHand().size());
    }

    @Test
    void saveRightCards() {
        mosquitoPlayer.drawCard(new Card("Card1", 1, 1, 1, 1, 1));
        mosquitoPlayer.drawCard(new Card("Card2", 2, 2, 2, 2, 2));
        mosquitoPlayer.drawCard(new Card("Card3", 3, 3, 3, 3, 3));
        assertEquals(new Card("Card1", 1, 1, 1, 1, 1), mosquitoPlayer.getHand().get(0));
        assertEquals(new Card("Card2", 2, 2, 2, 2, 2), mosquitoPlayer.getHand().get(1));
        assertEquals(new Card("Card3", 3, 3, 3, 3, 3), mosquitoPlayer.getHand().get(2));
    }

    @Test
    void playCardFirst() {
        mosquitoPlayer.drawCard(new Card("Card1", 1, 1, 1, 1, 1));
        mosquitoPlayer.drawCard(new Card("Card2", 2, 2, 2, 2, 2));
        mosquitoPlayer.drawCard(new Card("Card3", 3, 3, 3, 3, 3));
        mosquitoPlayer.setMyTurn(true);
        assertEquals(1, mosquitoPlayer.playCard("Attk", new Card("Card4", 4, 4, 4, 4, 4)));
    }
}
