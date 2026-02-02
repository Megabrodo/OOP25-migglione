package migglione.view.impl;

import javax.swing.JPanel;

import migglione.view.api.SceneFactory;

/**
 * Implementation of SceneFactory.
 * 
 * <p>
 * It lets the creation of the different scenes
 * up to a factory, in order to incapsulate the names
 * of the scenes and to allow extentions by simply adding
 * new values cleanly.
 */
public final class SceneFactoryImpl implements SceneFactory {

    @Override
    public JPanel createScene(final SwingViewImpl view, final String n) {
        return switch (n) {
            case "MENU" -> new Menu(view);
            case "CREDITS" -> new Credits(view);
            default -> throw new IllegalArgumentException("No such scene exist");
        };
    }
}
