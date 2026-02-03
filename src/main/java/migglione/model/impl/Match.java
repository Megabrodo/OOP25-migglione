package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;
import migglione.model.api.Player;

/**
 * Class that covers a match of the game.
 * Takes care of turn order, card submission, comparisons and score updates.
 * 
 */
public class Match {

    private final List<Player> players = new ArrayList<>();
    private final List<List<Card>> winnings = new ArrayList<>();
    private final List<Integer> scores = new ArrayList<>();
    private int turnStart;

    /**
     * Constructor of the class.
     * Creates a match involving an amount of players,
     * and manages all aspects of a game.
     * 
     * @param starter the player that starts the first turn
     * @param second the player that will go second in the first turn
     */
    public Match(final Player starter, final Player second) {
        // setting up players' scores and winnings
        players.add(starter);
        players.add(second);
        for (Player p : players) {
            winnings.add(new ArrayList<>());
            scores.add(0);
        }
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
        final List<Card> cardsPlayed = new ArrayList<>();
        final int attrChoice = turnStart; //rudimental sub, should be like: players.get(turnStart).chooseAttr();
        final int comparison = this.compareCards(cardsPlayed.getFirst(), cardsPlayed.get(1), attrChoice);
        /* comparison should be like: compareCards(
            players.getFirst().playCard(attrChoice),
            players.getLast().playCard(attrChoice),
            attrChoice
        );*/
        final int winner = comparison < 0 ? 0 : 1;
        scores.set(winner, scores.get(winner) + Math.abs(comparison));
        turnStart = (turnStart + 1) % players.size();
    }

    /**
     * Compares two given cards on a specific attribute.
     * 
     * @param a the first card
     * @param b the second card
     * @param stat the given attribute to compare the cards on
     * @return the difference between the two cards' values on the given attribute.
     */
    private int compareCards(final Card a, final Card b, final int stat) {
        switch (stat) {
            case 0: return a.getAttk() - b.getAttk();
            case 1: return a.getDeff() - b.getDeff();
            case 2: return a.getStrength() - b.getStrength();
            case 3: return a.getIntelligence() - b.getIntelligence();
            case 4: return a.getStealth() - b.getStealth();
            default: throw new IllegalArgumentException("Illegal attribute chosen.");
        }
    }

}
