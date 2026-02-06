package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

/** 
 * Class for managing the playing field's view, 
 * with both player's hands, decks and scores,
 * along with a settings section.
 * */
public final class StartGame extends JPanel implements MusicProvider {

    private static final long serialVersionUID = 5456545654L;
    private static final int RED_HUE = 173;
    private static final int GREEN_HUE = 103;
    private static final int BLUE_HUE = 44;
    private static final Color TABLE_COLOR = new Color(RED_HUE, GREEN_HUE, BLUE_HUE);
    private static final String TRACK_PATH = "/soundtracks/ENA Dream BBQ.wav";
    private static final String CARDS_IMAGE_PATH = "/images/cards/";

    /**
     * Constructor of this class. 
     * Divides screen into play field, player's hand,
     * opponent's hand, menu and scores.
     */
    public StartGame() {
        this.setLayout(new BorderLayout()); //prob is "this inside boxlayout"

        final JPanel pCards = new JPanel();
        final JPanel oCards = new JPanel();
        final JPanel mainField = new JPanel();
        final JPanel scoreCol = new JPanel();
        final JButton pScore = new JButton("0");
        final JButton oScore = new JButton("0");

        this.add(oCards, BorderLayout.NORTH);
        this.add(mainField, BorderLayout.CENTER);
        this.add(pCards, BorderLayout.SOUTH);

        mainField.setBackground(TABLE_COLOR);
        mainField.setBorder(new SoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY));
        pScore.setBackground(Color.GREEN);
        oScore.setBackground(Color.RED);
        pScore.setEnabled(false);
        oScore.setEnabled(false);

        mainField.setLayout(new BorderLayout());
        mainField.add(scoreCol, BorderLayout.EAST);
        scoreCol.setLayout(new BoxLayout(scoreCol, BoxLayout.Y_AXIS));
        scoreCol.setOpaque(false);
        scoreCol.add(oScore);
        scoreCol.add(Box.createVerticalGlue());
        scoreCol.add(pScore);

        final Set<JPanel> cards = new HashSet<>();
        cards.add(oCards);
        cards.add(pCards);
        for (final JPanel p : cards) {
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        }

    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(TRACK_PATH);
    }
}
