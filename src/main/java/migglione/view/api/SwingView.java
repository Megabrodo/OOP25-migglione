package migglione.view.api;

import java.util.Optional;

/**
 * Interface used as a base to SwingViewImpl implementation.
 */
public interface SwingView {
    /**
     * Method used to change scene with the CardLayout.
     * 
     * @param sceneName is the String name of the scene to set
     */
    void setScene(String sceneName);

    /**
     * To return the current scene being displayed.
     * 
     * @return a String, that is the current scene name
     */
    String getSceneName();

    /**
     * Functional method to quit the application.
     */
    void quit();

    /**
     * Method used to display the end game message.
     * 
     * @param winner is the name of the winner of the game
     * @param player is the name of the player
     * @param pScore is the score of the player
     * @param cScore is the score of the CPU
     */
    void endMessage(String winner, String player, Optional<Integer> pScore, Optional<Integer> cScore);
}
