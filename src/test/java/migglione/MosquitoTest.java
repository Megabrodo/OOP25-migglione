package migglione;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import migglione.model.impl.Card;
import migglione.model.impl.Mosquito;

public class MosquitoTest {
    private final Mosquito mosquitoPlayer = new Mosquito(new ArrayList<Card>(), true);
    
    @Test
    void drawToFull() {
        for (int i = 0; i < 5; i++) {
            mosquitoPlayer.drawCard(new Card("EmptyCard", 0, 0, 0, 0, 0));
        }
        assertEquals(3, mosquitoPlayer.getHand().size());
    } 
}
