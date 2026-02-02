package migglione.view.api;

import javax.swing.JPanel;

import migglione.view.impl.SwingViewImpl;

/**
 * Factory needed to create scenes for CardLayout more easily.
 */
public interface SceneFactory {
    /**
     * Method used to create the scenes of CardLayout.
     * 
     * @param view is the parent View from which CardLayout is initialized
     * @param n will be parameter to decide what scene it to be created
     * @return a JPanel rapresenting the new Scene
     */
    JPanel createScene(SwingViewImpl view, String n);
}
