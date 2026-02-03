package migglione.view.impl;

import java.awt.Color;
import java.awt.GridBagLayout;

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

        mainField.setBackground(new Color(125 * 2 ^ 16 + 80 * 2 ^ 8));
        mainField.setBorder(new SoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY));
        pScore.setBackground(Color.BLUE);
        oScore.setBackground(Color.RED);
        pScore.setEnabled(false);
        oScore.setEnabled(false);

        mainField.setLayout(new GridBagLayout());
        mainField.add(scoreCol);
        scoreCol.setLayout(new BoxLayout(scoreCol, BoxLayout.Y_AXIS));
        scoreCol.add(oScore);
        scoreCol.add(Box.createVerticalGlue());
        scoreCol.add(pScore);
    }
}
