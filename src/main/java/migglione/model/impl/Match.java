package migglione.model.impl;

import java.util.HashMap;
import java.util.Map;

import migglione.model.api.Deck;
import migglione.model.api.CardDraw;
import migglione.model.api.Player;

/**
 * Class that covers a match of the game.
 * Takes care of turn order, card submission, comparisons and score updates.
 * 
 */
public class Match {

    private static final int DECKS_SIZE = 24;
    private final Map<Player, DoubleStore<CardDraw, Integer>> scores = new HashMap<>();
    private int turn = 1;

    /**
     * Constructor of the class.
     * Creates a match involving an amount of players,
     * and manages all aspects of a game.
     * 
     * @param starter the player that starts the first turn
     * @param second the player that will go second in the first turn
     */
    public Match(final Player starter, final Player second, final CardDraw stDeck, final CardDraw scDeck) {
        scores.put(starter, new DoubleStore<CardDraw,Integer>(stDeck, 0));
        scores.put(second, new DoubleStore<CardDraw,Integer>(scDeck, 0));
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
        for ( Player p : scores.keySet()) {
            comparison += p.playCard(attrChoice, p.getHand().getFirst()) * compSign; //card currently chosen automatically
            compSign *= -1;
        }
        final Player winner = scores.keySet().stream().toList().get(comparison <= 0 ? 0 : 1);
        scores.get(winner).setY(scores.get(winner).getY() + Math.abs(comparison));
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
            return scores.get(player).getY();
        } else {
            throw new IllegalArgumentException("Requested score of a player not in this match.");
        }
    }
}
