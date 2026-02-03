package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Card;
import migglione.model.api.Player;

public class Match {

    private final List<Player> players = new ArrayList<>();
    private final List<List<Card>> winnings = new ArrayList<>();
    private final List<Integer> scores = new ArrayList<>();
    private int turnStart = 0;
    
    /**
     * Constructor of the class.
     * Creates a match involving an amount of players,
     * and manages all aspects of a game.
     */
    public Match(final Player starter, final Player second) {
        // setting up players' scores and winnings
        players.add(starter);
        players.add(second);
        for (int i = 0; i < players.size(); i++) {
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
        List<Card> cardsPlayed = new ArrayList<>();
        final int attrChoice = players.get(turnStart).chooseAttr(); //function not implemented yet
        final int comparison = compareCards(
            players.getFirst().playCard(attrChoice), 
            players.getLast().playCard(attrChoice), 
            attrChoice
        );
        final int winner = comparison < 0 ? 0 : 1;
        scores.set(winner, scores.get(winner) + Math.abs(comparison));
    }
    /**
     * Compares two given cards on a specific attribute.
     * @param a the first card
     * @param b the second card
     * @param stat the given attribute to compare the cards on
     * @return the difference between the two cards' values on the given attribute.
     */
    private int compareCards(Card a, Card b, int stat) {
        switch (stat) {
            case 0: return a.getAttk() - b.getAttk();
            case 1: return a.getDeff() - b.getDeff();
            case 2: return a.getAbiity() - b.getAbiity();
            case 3: return a.getIntelligenza() - b.getIntelligenza();
            case 4: return a.getFurtivita() - b.getFurtivita();
        }
    }

}
