package migglione.view.impl;

import javax.swing.JPanel;

import migglione.view.api.SceneFactory;

public class SceneFactoryImpl implements SceneFactory {

    @Override
    public JPanel createScene(final SwingViewImpl view, final String n) {
        return switch(n) {
            case "MENU" -> new Menu(view);
            case "CREDITS" -> new Credits(view);
            default -> throw new IllegalArgumentException("No such scene exist");
        };
    }
    
}
