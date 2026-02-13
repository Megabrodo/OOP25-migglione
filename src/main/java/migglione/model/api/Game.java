package migglione.model.api;

import java.util.List;
import java.util.Optional;

import migglione.model.impl.CardImpl;

/**
 * Interface of the model of the application.
 * 
 * <p>
 * Since its implementation extends another class,
 * the Javadocs of this methods will be displayed
 * in the classes themselves
 */
public interface Game {

    CardImpl playUserTurn(String attr, CardImpl played);

    String getCurrAttr();

    Optional<String> getWinner();

    Optional<Integer> getPlayerScore();

    Optional<Integer> getCPUScore();

    int getScore(Player player);

    Player getTurnLeader();

    List<Player> getPlayers();

    boolean matchEnded();
}
