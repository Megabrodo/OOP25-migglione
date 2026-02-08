package migglione.model.impl;

import java.util.ArrayList;

/**
 * Class designed to overlook a match.
 * Takes care of managing when the turns happen and more
 */
public class Game extends Match {

    /**
     * Constructor of the class.
     * Due to being a "more functional" version of Match,
     * it inherits its characteristics.
     * 
     * @param starter the player that starts the first turn
     * @param second the player that goes second
     * @param deck the deck the players will draw from
     */
    public Game() {
        super(new User(new ArrayList<>()), new Mosquito(new ArrayList<>(), false), new StandardCardDrawImpl(new DeckImpl()));
    }
}
