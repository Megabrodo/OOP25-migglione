package migglione.controller.api;

/**
 * Interface for the Controller of the application.
 * 
 * <p>
 * Accordingly to the MVC method, the role of the
 * Controller will be the one of making practical decisions
 * like to determine the winner or deciding who is playing
 */
public interface Controller {

    /**
     * Used to store the player's name.
     * 
     * @param name is the name of the player,
     *             it will be used later if they
     *             won the game
     */
    void setPlayerName(String name);

    /**
     * Used to determine who will play a card.
     * 
     * <p>
     * Since in this game there the player will be
     * put against a CPU, this method uses informations
     * gathered in the model to decide whose turn this is,
     * and will make them play a card.
     */
    void move();

    /**
     * Decides if it's time to move to another round.
     * 
     * <p>
     * When another round begins, both players will receive
     * a new card from the deck and the cards played will be
     * trasformed into points
     */
    void nextRound();

    /**
     * Determines the end of the match.
     * 
     * <p>
     * This method decides who wins by comparing the points,
     * and if the player is the one who won then its name
     * will be put in the scores file, so that it can be
     * displayed in the Scores scene
     */
    void endMatch();
}
