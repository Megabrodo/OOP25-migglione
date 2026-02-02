package migglione.model.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

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
}
