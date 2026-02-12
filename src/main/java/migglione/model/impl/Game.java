package migglione.model.impl;

import java.util.ArrayList;
import java.util.Optional;

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
     * @param name the name of the player
     */
    public Game(final String name) {
        super(new User(new ArrayList<>(), name), new Mosquito(new ArrayList<>(), false), new StandardCardDrawImpl(new DeckImpl())); 
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
     * @return null if it's the user's turn, the card played by the CPU it it's their turn.
     * 
     * If the user isn't starting the next turn, the CPU's choice is already registered.
     */
    public Card playUserTurn(final String attr, final Card played) {
        final Player msq = getPlayers().getLast();
        final Player plr = getPlayers().getFirst();
        plr.chooseAttr(attr);
        if (getPlayers().get(turnLead).equals(plr)) {
            msq.chooseAttr(attr);
           cpuStoredVal = msq.playCard(attr, played);
        }
        final int pTurn = plr.playCard(attr, played);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        final boolean end = playTurn(pTurn, cpuStoredVal);
        if (end) {
            //post-game stuff
        } else if (getPlayers().get(turnLead).equals(msq)) {
            cpuStoredVal = msq.playCard(attr, played);
            currAttr = msq.getAttr();
            plr.chooseAttr(currAttr);
            return msq.getPlayedCard();
        }
        return null;

    }

    /**
     * Method to obtain the attribute this turn is being played on.
     * 
     * @return the current active attribute.
     */
    public String getCurrAttr() {
        return this.currAttr;
    }

    public Optional<Integer> getPlayerScore() {
        for (var p : getPlayers()) {
            if (p.getName().equals("Player")) {
                return Optional.of(getScore(p));
            }
        }
        return null;
    }

    /**
     * Method to write the username in scores.txt.
     * 
     * @param playerName is the name of the player,
     *                   it will be written only if they won
     */
    public void writeWinner(String playerName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeWinner'");
    }
}
