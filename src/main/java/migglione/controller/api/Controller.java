package migglione.controller.api;

import migglione.model.impl.Game;

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
     * Used to start the real game.
     * 
     * @param name is the name of the player,
     *             it will be used later if they
     *             won the game
     */
    void startSession(String name);

    /**
     * Used to check if its time to end the match.
     * 
     * <p>
     * This method decides if endSession should be
     * called, by asking directly the model
     */
    void checkSession();

    /**
     * Determines the end of the match.
     * 
     * <p>
     * This method decides who wins by comparing the points,
     * and if the player is the one who won then its name
     * will be put in the scores file, so that it can be
     * displayed in the Scores scene
     */
    void endSession();

    /**
     * Method to obtain the stored player name.
     * 
     * @return the name of the player
     */
    String getPlayerName();

    /**
     * Getter for the model of the game.
     * 
     * @return the model
     */
    Game getModel();
}
