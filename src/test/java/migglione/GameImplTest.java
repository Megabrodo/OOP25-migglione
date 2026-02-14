package migglione;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import migglione.model.api.Game;
import migglione.model.impl.Card;
import migglione.model.impl.GameImpl;

/**
 * Test used to question the validity of GameImpl.
 * 
 * <p>
 * It is responsible for testing: if the GameImpl's constructor
 * is working correctly (so the first attribute is always the same
 * and the scores are not null), if the playturn method return
 * something and not null and if the attributes changes with the
 * lead but not with the second player.
 * 
 * <p>
 * It may seems odd to separate the last two tests, but we wanted to
 * make sure that various method could be tested separately, in case
 * a precise errror accurs.
 */
public class GameImplTest {
    
    private final Game game = new GameImpl("player");

    @Test
    void testConstructor() {
        assertEquals("Attk", game.getCurrAttr());
        assertFalse(game.getCPUScore().isEmpty());
        assertFalse(game.getPlayerScore().isEmpty());
    }

    @Test
    void testPlayTurn() {
        final Card playerCard = game.getPlayers().getFirst().getHand().getFirst();
        final Card cpuCard = game.getPlayers().getLast().getHand().getFirst();
        assertNotNull(playerCard);
        assertNotNull(cpuCard);

        game.playTurnLead("Deff", playerCard);
        game.playTurnTail(cpuCard);
        assertNotNull(game.playTurn());
    }

    @Test
    void testChangingAttribute() {
        final Card playerCard = game.getPlayers().getFirst().getHand().getFirst();
        
        game.playTurnLead("Strength", playerCard);
        assertEquals("Strength", game.getCurrAttr());

        final Card cpuCard = game.getPlayers().getLast().getHand().getFirst();
        game.playTurnTail(cpuCard);

        assertEquals("Strength", game.getCurrAttr());
    }
}
