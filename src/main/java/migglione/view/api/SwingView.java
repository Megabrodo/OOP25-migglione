package migglione.view.api;

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
}
