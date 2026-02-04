package migglione.model.impl;

import java.util.HashMap;
import java.util.Map;

import migglione.model.api.Player;

/**
 * Class that covers a match of the game.
 * Takes care of turn order, card submission, comparisons and score updates.
 * 
 */
public class Match {

    private static final int DECKS_SIZE = 24;
    private final Map<Player, Integer> scores = new HashMap<>();
    private int turn = 1;

    /**
     * Constructor of the class.
     * Creates a match involving an amount of players,
     * and manages all aspects of a game.
     * 
     * @param starter the player that starts the first turn
     * @param second the player that will go second in the first turn
     */
    public Match(final Player starter, final Player second) {
        // setting up players' scores
        scores.put(starter, 0);
        scores.put(second, 0);
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
        final int attrChoice = turn % 5; //rudimental sub, should be like: players.get(turnStart % 2).chooseAttr();
        int compSign = 1;
        int comparison = 0;
        for ( Player p : scores.keySet()) {
            comparison += p.playCard(attrChoice, p.getHand().getFirst()) * compSign; //card currently chosen automatically
            compSign *= -1;
        }
        final Player winner = scores.keySet().stream().toList().get(comparison <= 0 ? 0 : 1);
        scores.replace(winner, scores.get(winner) + Math.abs(comparison));
        turn++;
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
    */
    public boolean matchEnded() {
        return this.turn == DECKS_SIZE;
    }

    public int getScore(Player player) {
        if(scores.keySet().contains(player)) {
            return scores.get(player);
        } else {
            throw new IllegalArgumentException("Requested score of a player not in this match.");
        }
    }
}
