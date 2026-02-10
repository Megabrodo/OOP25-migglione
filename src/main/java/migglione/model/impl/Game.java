package migglione.model.impl;

import java.util.ArrayList;

import migglione.model.api.Player;

/**
 * Class designed to overlook a match.
 * Takes care of managing when the turns happen and more
 */
public class Game extends Match {

    private String currAttr = "Attk";
    private int cpuStoredVal;
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
     * Assumes the user is always inputted first when creating an instance.
     * 
     * @param attr the attribute to play on
     * @param played the card chosen to be played
     * 
     * If the user isn't starting the next turn, the CPU's choice is already registered.
     */
    public void playUserTurn(final String attr, final Card played) {
        System.out.println("entered, ");
        getPlayers().getFirst().chooseAttr(attr);
        getPlayers().getFirst().playCard(attr, played);
        getPlayers().getFirst().drawCard(played);
        System.out.print("set up, ");
        final boolean end = playTurn();
        System.out.print("turned");
        if (end) {
            //post-game stuff
        } else if (getPlayers().get(turnLead).equals(getPlayers().getLast())) {
            final Player msq = getPlayers().getLast();
            cpuStoredVal = msq.playCard(attr, played);
            currAttr = msq.getAttr();
            getPlayers().getFirst().chooseAttr(currAttr);

        }

    }

    /**
     * Method to obtain the attribute this turn is being played on.
     * 
     * @return the current active attribute.
     */
    public String getCurrAttr() {
        return this.currAttr;
    }
}
