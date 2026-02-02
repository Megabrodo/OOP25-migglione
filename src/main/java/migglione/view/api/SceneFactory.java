package migglione.view.api;

import javax.swing.JPanel;

import migglione.view.impl.SwingViewImpl;

/**
 * Factory needed to create scenes for CardLayout more easily.
 */
public interface SceneFactory {
    
    JPanel createScene(SwingViewImpl view, String n);
}
