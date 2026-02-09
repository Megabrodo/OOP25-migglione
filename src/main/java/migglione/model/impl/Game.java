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

    /**
     * Upon user's choice, plays the rest of the turn. 
     * The chosen card is "played", but only virtually as it is redrawn
     * in order to set the played card for easier access during calculcations.
     * 
     * @param attr
     * @param played
     */
    public void userChosen(final String attr, final Card played) {
        this.getPlayers().getFirst().playCard(attr, played);
        this.getPlayers().getFirst().drawCard(played);
        final boolean end = playTurn();
        if (end) {
            //post-game stuff
        }

    }
}
