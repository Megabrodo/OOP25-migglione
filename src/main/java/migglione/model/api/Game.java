package migglione.model.api;

import java.util.List;
import java.util.Optional;

import migglione.model.impl.Card;

/**
 * Interface of the model of the application.
 * 
 * <p>
 * Since its implementation extends another class,
 * the Javadocs of this methods can be also seen
 * in the classes themselves
 */
public interface Game {

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
     * @param plrStat the value of the attribute of the player
     * @param cpuStat the value of the attribute of the CPU
     * @return if this was the last turn of the match.
     */
    boolean playTurn(int plrStat, int cpuStat);

    /**
     * Upon user's choice, plays the rest of the turn. 
     * The chosen card is "played", but only virtually as it is redrawn
     * in order to set the played card for easier access during calculcations.
     * Assumes the user is always inputted first when creating an instance.
     * If the user isn't starting the next turn, the CPU's choice is already registered.
     * 
     * @param attr the attribute to play on
     * @param played the card chosen to be played
     * 
     * @return a boolean indicating whether it's the CPU's turn to start.
     */
    boolean playUserTurn(String attr, Card played);

    public void playTurnLead(final String attr, final Card card);

    public void playTurnTail(final Card card);

    public boolean playTurn();

    /**
     * Method to obtain the attribute this turn is being played on.
     * 
     * @return the current active attribute.
     */
    String getCurrAttr();

    /**
     * Method to return the name of the winner.
     * If match hasn't finished, returns null.
     * 
     * @return the String of the winner's name, or null if match is not finished.
     */
    Optional<String> getWinner();

    /**
     * Method used to get the score of the player.
     * 
     * @return the optional of the score
     */
    Optional<Integer> getPlayerScore();

    /**
     * Method used to get the score of the mosquito.
     * 
     * @return the optional of the score
     */
    Optional<Integer> getCPUScore();

    /**
     * Method to get the current score of one player.
     * 
     * @param player the player whose score is requested.
     * @return the score of said player.
     */
    int getScore(Player player);

    /**
     * Method to get who started the current turn.
     * 
     * @return the player that starts the current turn.
     */
    Player getTurnLeader();

    /**
     * Method to obtain the players involved in the match.
     * 
     * @return a list containing the players in the match.
     */
    List<Player> getPlayers();

    /** 
     * Checks if the match has ended.
     * 
     * @return a boolean indicating if this match has ended.
     */
    boolean matchEnded();
}
