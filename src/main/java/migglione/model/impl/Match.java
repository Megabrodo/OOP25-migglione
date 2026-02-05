package migglione.model.impl;

import java.util.HashMap;
import java.util.Map;

import migglione.model.api.CardDraw;
import migglione.model.api.Player;

/**
 * Class that covers a match of the game.
 * Takes care of turn order, card submission, comparisons and score updates.
 * 
 */
public class Match {

    private static final int HAND_SIZE = 3;
    private final Map<Player, DoubleStore<CardDraw, Integer>> scores = new HashMap<>();
    private int turn = 1;

    /**
     * Constructor of the class.
     * Creates a match involving an amount of players,
     * and creates the starting position of the game.
     * 
     * @param starter the player that starts the first turn
     * @param second the player that will go second in the first turn
     * @param stDeck the CardDraw strategy implemented for the first player
     * @param scDeck the CardDraw strategy implemented for the second player
     */
    public Match(final Player starter, final Player second, final CardDraw stDeck, final CardDraw scDeck) {
        scores.put(starter, new DoubleStore<>(stDeck, 0));
        scores.put(second, new DoubleStore<>(scDeck, 0));
        this.allDraw(HAND_SIZE);
        //other setup needed
    }

    /**
     * Starts a turn of the game. In order:
     * <ol>
     * <li> Awaits the attribute selection;
     * <li> Asks for the players to submit cards;
     * <li> Compares the cards based on the chosen attribute;
     * <li> sends the cards to the winner's winnings pile;
     * <li> updates the score.
     * </ol>
     */
    public void playTurn() {
        final int attrChoice = scores.keySet().stream().toList().get(turn % 2 - 1).getAttr();
        int compSign = 1;
        int comparison = 0;
        for (final Player p : scores.keySet()) {
            comparison += p.playCard(attrChoice, p.getHand().getFirst()) * compSign; //card currently chosen automatically
            compSign *= -1;
        }
        final Player winner = scores.keySet().stream().toList().get(comparison <= 0 ? 0 : 1);
        scores.get(winner).setY(scores.get(winner).getY() + Math.abs(comparison));
        turn++;
        this.allDraw(1);
    }

    private void allDraw(final int n) {
        for (int i = 0; i < n; i++) {
            for (final Player p : scores.keySet()) {
                p.drawCard(scores.get(p).getX().getCard());
            }
        }
    }

    /**
     * Method to get the turn the match is on.
     * 
     * @return the current turn.
     */
    public int getTurn() {
        return this.turn;
    }

    /** 
     * Checks if the match has ended.
     * 
     * @return a boolean indicating if this match has ended.
     */
    public boolean matchEnded() {
        for (final var p : scores.entrySet()) {
            if (p.getValue().getX().isDeckEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to get the current score of one player.
     * 
     * @param player the player whose score is requested.
     * @return the score of said player.
     */
    public int getScore(final Player player) {
        if (scores.keySet().contains(player)) {
            return scores.get(player).getY();
        } else {
            throw new IllegalArgumentException("Requested score of a player not in this match.");
        }
    }
}
