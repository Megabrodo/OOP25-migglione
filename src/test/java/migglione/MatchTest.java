package migglione;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import migglione.model.api.CardDraw;
import migglione.model.api.Player;
import migglione.model.impl.DeckImpl;
import migglione.model.impl.Match;
import migglione.model.impl.StandardCardDrawImpl;
import migglione.model.impl.User;

/**
 * Test class to ascertain the Match class's integrity.
 * 
 */
public class MatchTest {

    private final User u = new User(new LinkedList<>(), "P1");
    private final User v = new User(new LinkedList<>(), "P2");
    private final CardDraw d = new StandardCardDrawImpl(new DeckImpl());
    private final Match match = new Match(u, v, d);
    private final int initDeckSize = d.getSizeDeck();

    @Test
    void testCreate() {
        assertEquals(Set.of(u, v), match.getPlayers());
        for (final Player x : Set.of(u, v)) {
            assertEquals(x.getHand().size(), Match.HAND_SIZE);
            assertEquals(match.getScore(x), 0);
        }
        assertEquals(match.matchEnded(), false);
        assertEquals(match.getWinner(), Optional.empty());
        assertEquals(match.getTurnLeader(), u);
    }

    void testTurn() {
        u.playCard("Attk", u.getHand().getFirst());
        v.playCard("Attk", u.getHand().getFirst());
        assertEquals(match.playTurn(10, 0), false);
        assertEquals(match.getScore(u), 2);
        assertEquals(match.getScore(v), 0);
        assertEquals(match.getTurnLeader(), u);
        for (final Player p : Set.of(u, v)) {
            assertEquals(p.getPile(new LinkedList<>()).size(), match.getScore(p));
            assertEquals(p.getHand().size(), Match.HAND_SIZE);
        }
        final boolean end = d.isDeckEmpty() && u.getHand().isEmpty() && v.getHand().isEmpty();
        assertEquals(match.matchEnded(), end);
        assertEquals(d.getSizeDeck(), initDeckSize - (match.getScore(u) + match.getScore(v)));
        assertEquals(u.getHand().size(), v.getHand().size());
    }

    void testChangeTurn() {
        for (int i = 1; i < 3; i++) {
            match.playTurn(0, 10);
            assertEquals(match.getTurnLeader(), v);
        }
        match.playTurn(0, 10);
        assertEquals(match.getScore(v), 6);
        assertEquals(match.getTurnLeader(), u);
        match.playTurn(0, 10);
        assertEquals(match.getTurnLeader(), v);
    }

    void testTie() {
        final int uScore = match.getScore(u);
        final int vScore = match.getScore(v);
        match.playTurn(5, 5);
        for(final Player p : Set.of(u, v)) {
            assertEquals(match.getScore(p), p.equals(u) ? uScore : vScore);
            assertEquals(match.getScore(p), p.getPile(new LinkedList<>()).size());
        }
        match.playTurn(10, 0);
        assertEquals(match.getScore(u), uScore + 4);
    }

    void testEnd() {
        int prevU = match.getScore(u);
        while(!d.isDeckEmpty()) {
            match.playTurn(10, 0);
            assertEquals(match.getScore(u), prevU + 2);
            prevU += 2;
        }
        while (u.getHand().isEmpty()) {
            u.playCard("Attk", u.getHand().getFirst());
            v.playCard("Attk", v.getHand().getFirst());
        }
        assertTrue(u.getHand().isEmpty());
        assertTrue(v.getHand().isEmpty());
        assertTrue(match.playTurn(10, 0));
        assertTrue(match.matchEnded());
        assertNotNull(match.getWinner().get());
        assertEquals(match.getWinner(), u.getName());
        assertEquals(match.getScore(u) + match.getScore(v), initDeckSize);
    }

}
