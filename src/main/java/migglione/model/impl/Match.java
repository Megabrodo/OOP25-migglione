package migglione.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import migglione.model.api.CardDraw;
import migglione.model.api.Player;

/**
 * Class that covers a match of the game.
 * Takes care of turn order, card submission, comparisons and score updates.
 * 
 */
public class Match {

    public static final int HAND_SIZE = 3;
    private static final int MAX_CONSEC_WINS = 3;
    private final Map<Player, Integer> scoring = new HashMap<>();
    private final CardDraw deck;
    private int turn = 1;
    private int consecWins;
    private Player latestWin;
    private int turnLead;
    private List<Card> cardsStakes;

    /**
     * Constructor of the class.
     * Creates a match involving an amount of players,
     * and creates the starting position of the game.
     * 
     * @param starter the player that starts the first turn
     * @param second the player that will go second in the first turn
     * @param deck the CardDraw strategy implemented
     */
    public Match(final Player starter, final Player second, final CardDraw deck) {
        scoring.put(starter, 0);
        scoring.put(second, 0);
        this.deck = deck;
        this.allDraw(HAND_SIZE);
        this.cardsStakes = new ArrayList<>();
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
     * 
     * @return if this was the last turn of the match.
     */
    public boolean playTurn() {
        final String attrChoice = scoring.keySet().stream().toList().get(turnLead).getAttr(); 
        int compSign = 1;
        int comparison = 0;
        for (final Player p : scoring.keySet()) {
            comparison += p.playCard(attrChoice, p.getPlayedCard()) * compSign;
            compSign *= -1;
            cardsStakes.add(p.getPlayedCard());
        }
        if (comparison != 0) {
            final Player winner = scoring.keySet().stream().toList().get(comparison <= 0 ? 0 : 1);
            scoring.replace(winner, scoring.get(winner) + cardsStakes.size());
            for (final Player p : scoring.keySet()) {
                if (p.equals(winner)) {
                    p.getPile(cardsStakes);
                } else {
                    p.getPile(new ArrayList<>());
                }
            }
            this.changeTurn(winner);
            cardsStakes.clear();
        }
        final boolean isEnd = matchEnded();
        if (!isEnd) {
            turn++;
            this.allDraw(1);
        }
        return isEnd;
    }

    private void allDraw(final int n) {
        if (!deck.isDeckEmpty()) {
            for (int i = 0; i < n; i++) {
                for (final Player p : scoring.keySet()) {
                    p.drawCard(deck.getCard());
                }
            }
        }
    }

    private void changeTurn(final Player winner) {
        if (consecWins == 0) {
            latestWin = winner;
        } else if (latestWin.equals(winner)) {
            consecWins++;
            if (consecWins >= MAX_CONSEC_WINS) {
                turnLead = 1 - turnLead;
                consecWins = 1;
            }
        } else {
            latestWin = winner;
            consecWins = 1;
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
        for (final var p : scoring.keySet()) {
            if (!p.getHand().isEmpty()) {
                return false;
            }
        }
        return deck.isDeckEmpty();
    }

    /**
     * Method to get the current score of one player.
     * 
     * @param player the player whose score is requested.
     * @return the score of said player.
     */
    public int getScore(final Player player) {
        if (scoring.keySet().contains(player)) {
            return scoring.get(player);
        } else {
            throw new IllegalArgumentException("Requested score of a player not in this match.");
        }
    }

    /**
     * Method to obtain the players involved in the match.
     * 
     * @return a list containing the players in the match.
     */
    public List<Player> getPlayers() {
        return scoring.keySet().stream().toList();
    }
}
