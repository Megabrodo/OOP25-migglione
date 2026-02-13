package migglione.model.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.logging.Logger;

import java.util.logging.Level;

import migglione.model.api.Player;

/**
 * Class designed to overlook a match.
 * Takes care of managing when the turns happen and more
 */
public class Game extends Match {

    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    private final String playerName;
    private String currAttr = "Attk";
    private int cpuStoredVal;
    /**
     * Constructor of the class.
     * Due to being a "more functional" version of Match,
     * it inherits its characteristics.
     * 
     * @param name the name of the player
     */
    public Game(final String name) {
        super(new User(new ArrayList<>(), name), new Mosquito(new ArrayList<>(), false), new StandardCardDrawImpl(new DeckImpl()));
        this.playerName = name;
    }

    /**
     * Upon user's choice, plays the rest of the turn. 
     * The chosen card is "played", but only virtually as it is redrawn
     * in order to set the played card for easier access during calculcations.
     * Assumes the user is always inputted first when creating an instance.
     * 
     * @param attr the attribute to play on
     * @param played the card chosen to be played
     * 
     * @return null if it's the user's turn, the card played by the CPU it it's their turn.
     * 
     * If the user isn't starting the next turn, the CPU's choice is already registered.
     */
    public Card playUserTurn(final String attr, final Card played) {
        final Player msq = getPlayers().getLast();
        final Player plr = getPlayers().getFirst();
        plr.chooseAttr(attr);
        if (getPlayers().get(turnLead).equals(plr)) {
            msq.chooseAttr(attr);
           cpuStoredVal = msq.playCard(attr, played);
        }
        final int pTurn = plr.playCard(attr, played);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        final boolean end = playTurn(pTurn, cpuStoredVal);
        if (end) {
            //post-game stuff
        } else if (getPlayers().get(turnLead).equals(msq)) {
            cpuStoredVal = msq.playCard(attr, played);
            currAttr = msq.getAttr();
            plr.chooseAttr(currAttr);
            return msq.getPlayedCard();
        }
        return null;

    }

    /**
     * Method to obtain the attribute this turn is being played on.
     * 
     * @return the current active attribute.
     */
    public String getCurrAttr() {
        return this.currAttr;
    }

    /**
     * Method used to get the score of the player.
     * 
     * @return the optional of the score
     */
    public Optional<Integer> getPlayerScore() {
        for (final var p : getPlayers()) {
            if (p.getName().equals(playerName)) {
                return Optional.of(getScore(p));
            }
        }
        return Optional.empty();
    }

    /**
     * Method used to get the score of the mosquito.
     * 
     * @return the optional of the score
     */
    public Optional<Integer> getCPUScore() {
        for (final var p : getPlayers()) {
            if (!p.getName().equals(playerName)) {
                return Optional.of(getScore(p));
            }
        }
        return Optional.empty();
    }

    /**
     * Method to write the username in a file.
     * 
     * <p>
     * It will use the stored name to make sure
     * that it's the same as the winner's, so that
     * it can be written in the file of scores
     * 
     * <p>
     * Since the jar can't write, i decided to
     * use an external folder named .migglione
     * to store the txt file, now it should work
     * even with the jar
     */
    public void writeWinner() {
        if (this.playerName.equals(getWinner())) {
            final Optional<Integer> pScoreOptional = getPlayerScore();
            final Map<String, Integer> scores = new HashMap<>();

            if (pScoreOptional.isEmpty()) {
                return;
            }
            final int pScore = pScoreOptional.get();

            final Path path = Paths.get(System.getProperty("user.home"), ".migglione", "ScoreTable.txt");
            try {
                Files.createDirectories(path.getParent());
            } catch (final IOException e) {
                LOGGER.log(Level.SEVERE, "Error during creation of folder", e);
                return;
            }

            if (Files.exists(path)) {
                try (BufferedReader read = Files.newBufferedReader(path)) {
                    String s = read.readLine();
                    while (s != null) {
                        final String[] split = s.split("->");
                        if (split.length == 2) {
                            scores.put(split[0].trim(), Integer.parseInt(split[1].trim()));
                        }
                        s = read.readLine();
                    }
                } catch (final IOException e) {
                    LOGGER.log(Level.SEVERE, "Error while reading file", e);
                }
            }
            scores.merge(playerName, pScore, Math::max);

            final List<Map.Entry<String, Integer>> orderedScores = new ArrayList<>(scores.entrySet());
            orderedScores.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                for (final Map.Entry<String, Integer> entry : orderedScores) {
                    writer.write(entry.getKey() + "->" + entry.getValue());
                    writer.newLine();
                }
            } catch (final IOException e) {
                LOGGER.log(Level.SEVERE, "Error in writing in file", e);
            }
        }
    }
}
