package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

/** 
 * Class for managing the playing field's view, 
 * with both player's hands, decks and scores,
 * along with a settings section.
 * */
public class PlayField extends JPanel {

    private static final long serialVersionUID = 5456545654L;
    private static final int RED_HUE = 173;
    private static final int GREEN_HUE = 103;
    private static final int BLUE_HUE = 44;
    private static final Color TABLE_COLOR = new Color(RED_HUE, GREEN_HUE, BLUE_HUE);

    /**
     * Constructor of this class. 
     * Divides screen into play field, player's hand,
     * opponent's hand, menu and scores.
     */
    public PlayField() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        final JPanel pCards = new JPanel();
        final JPanel oCards = new JPanel();
        final JPanel mainField = new JPanel();
        final JPanel scoreCol = new JPanel();
        final JButton pScore = new JButton("0");
        final JButton oScore = new JButton("0");
        this.add(oCards);
        this.add(mainField);
        this.add(pCards);

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
    }
}
