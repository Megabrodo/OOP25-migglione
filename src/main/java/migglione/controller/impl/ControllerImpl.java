package migglione.controller.impl;

import migglione.controller.api.Controller;
import migglione.view.api.SwingView;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.SwingViewImpl;

/**
 * Implementation of the Controller class.
 * 
 * <p>
 * It is responsible for storing the player's name,
 * to allow either the player or the CPU to play a card,
 * to decide if it's time to go to the next round or
 * to end the match and declaring the winner
 */
public final class ControllerImpl implements Controller {

    private final SwingView view;
    private String playerName;

    /**
     * Simple constructor of the Controller.
     * 
     * <p>
     * For now, it simply initializes the main class
     * of the GUI, so that the menu is seen by the player
     */
    public ControllerImpl() {
        this.view = new SwingViewImpl(this);
    }

    @Override
    public void startMatch(final String name) {
        this.playerName = name;
        //Qui inizializzo la classe del model che gestisce la partita
        view.setScene(Scenes.FIELD.getScene());
    }

    @Override
    public void endMatch() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endMatch'");
    }

}
