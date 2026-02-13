package migglione.controller.impl;

import java.util.List;

import migglione.controller.api.Controller;
import migglione.model.api.Player;
import migglione.model.impl.Card;
import migglione.model.impl.Game;
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
    private Game model;
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
    public void startSession(final String name) {
        this.playerName = name;
        this.model = new Game(playerName);
        view.setScene(Scenes.FIELD.getScene());
    }

    @Override
    public void checkSession() {
        if (this.model.matchEnded()) {
            endSession();
        }
    }

    @Override
    public List<Player> getPlayers() {
        return this.model.getPlayers();
    }

    @Override
    public Card playUserTurn(String attr, Card played) {
        return this.model.playUserTurn(attr, played);
    }

    @Override
    public String getCurrAttr() {
        return this.model.getCurrAttr();
    }

    @Override
    public Player getTurnLeader() {
        return this.model.getTurnLeader();
    }

    @Override
    public int getScore(Player player) {
        return this.model.getScore(player);
    }

    @Override
    public void endSession() {
        this.model.writeWinner();
        this.view.endMessage(this.model.getWinner(), playerName, this.model.getPlayerScore(), this.model.getCPUScore());
    }
}
