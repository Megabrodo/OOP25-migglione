package migglione.controller.impl;

import java.util.List;
import java.util.Optional;

import migglione.controller.api.Controller;
import migglione.model.api.Game;
import migglione.model.api.Player;
import migglione.model.impl.Card;
import migglione.model.impl.GameImpl;
import migglione.persistence.api.ScoreRepository;
import migglione.persistence.api.TutorialRepository;
import migglione.persistence.impl.ScoreRepositoryImpl;
import migglione.persistence.impl.TutorialRepositoryImpl;
import migglione.view.api.SwingView;
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
    private final ScoreRepository sRep;
    private final TutorialRepository tRep;
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
        this.sRep = new ScoreRepositoryImpl();
        this.tRep = new TutorialRepositoryImpl();

        checkFirstTime();
    }

    private void checkFirstTime() {
        if (!this.tRep.haveTutorialBeenSeen()) {
            this.tRep.writeOnTutorial();
            this.view.showTutorialPrompt();
        }
    }

    @Override
    public void startSession(final String name) {
        this.playerName = name;
        this.model = new GameImpl(playerName);
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
    public boolean playUserTurn(final String attr, final Card played) {
        return this.model.playUserTurn(attr, played);
    }

    public void playTurnLead(final String attr, final Card played) {
        this.model.playTurnLead(attr, played);
    }

    public void playTurnTail(final Card played) {
        this.model.playTurnTail(played);
    }

    public boolean playTurn() {
        return this.model.playTurn();
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
    public int getScore(final Player player) {
        return this.model.getScore(player);
    }

    @Override
    public void endSession() {
        final Optional<String> winner = this.model.getWinner();
        final Optional<Integer> pScoreOpt = this.model.getPlayerScore();
        final Optional<Integer> cScoreOpt = this.model.getCPUScore();
        if (winner.isEmpty() || pScoreOpt.isEmpty() || cScoreOpt.isEmpty()) {
            return;
        }

        if (winner.get().equals(playerName)) {
            this.sRep.writeWinner(playerName, pScoreOpt.get());
        }

        this.view.endMessage(winner.get(), playerName, pScoreOpt.get(), cScoreOpt.get());
    }
}
