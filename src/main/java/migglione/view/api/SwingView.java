package migglione.view.api;

import javax.swing.JPanel;

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
     * @return a JPanel, that is the current scene
     */
    JPanel getScene();

    /**
     * Functional method to refresh the scene to apply changes.
     */
    void refresh();
}
