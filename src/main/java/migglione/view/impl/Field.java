package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import migglione.controller.api.Controller;
import migglione.model.api.Player;
import migglione.model.impl.Card;
import migglione.model.impl.Game;
import migglione.model.impl.Mosquito;
//import migglione.model.impl.User;
import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;
import migglione.view.impl.scenesimpl.AbstractGamePanel;

/** 
 * Class for managing the playing field's view, 
 * with both player's hands, decks and scores,
 * along with a settings section.
 * */
public final class Field extends AbstractGamePanel implements MusicProvider {

    private static final String TRACK_PATH = "/soundtracks/ENA Dream BBQ.wav";
    private static final String CARDS_IMAGE_PATH = "/images/cards/";
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
    private static final int CARDS_WIDTH = 100;
    private static final int CARDS_HEIGHT = 125;
    private static final int SPACE_BETWEEN_CARDS = 500;
    private final transient Image playField;
    private final Game game;

    private final JPanel pCards = new JPanel();
    private final JPanel oCards = new JPanel();
    private final JPanel mainField = new JPanel();
    private final JPanel scoreCol = new JPanel();
    private final JPanel plays = new JPanel();
    private final JButton pScore = new JButton("0");
    private final JButton oScore = new JButton("0");
    private final JButton pPlay = new JButton();
    private final JButton oPlay = new JButton(); 

    /**
     * Constructor of this class. 
     * Divides screen into play field, player's hand,
     * opponent's hand, menu and scores.
     */
    public Field(final Controller controller) {

        this.game = new Game();
        this.playField = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();
        this.setLayout(new BorderLayout());

        pCards.setOpaque(false);
        oCards.setOpaque(pCards.isOpaque());
        pCards.setLayout(new FlowLayout(FlowLayout.CENTER, SPACE_BETWEEN_CARDS, SPACE_BETWEEN_CARDS)); //maybe grid is better?
        oCards.setLayout(pCards.getLayout());

        for (final Player p : game.getPlayers()) {
            final JPanel pHand = (p instanceof Mosquito) ? oCards : pCards;
            for (final Card c : p.getHand()) {
                final JButton card = new JButton();
                final ImageIcon bc = new ImageIcon(getClass().getResource(CARDS_IMAGE_PATH + c.getName() + ".png"));
                final ImageIcon bg = new ImageIcon(
                    bc.getImage().getScaledInstance(CARDS_WIDTH, CARDS_HEIGHT, Image.SCALE_SMOOTH)
                );
                card.setIcon(bg);
                card.setContentAreaFilled(false);
                //card.setBorderPainted(false);
                card.setFocusPainted(false);
                card.addMouseListener(new Hovering(c, mainField));
                card.putClientProperty("card", c);
                //card.setPreferredSize(getPreferredSize());
                card.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent dispose) {
                        final JButton pB = (p instanceof Mosquito) ? oPlay : pPlay;
                        pB.setIcon(bg);
                        //to implement in the logic
                        /*
                        idea for logic - GUI level:
                            REQUIRES: 2 buttons in the mainfield, one top one bottom

                        - add selected card into bottom button (OK)
                        - await mosquito response
                        - do same for top side
                        - once draw ends, check for new hand and update icon
                          */

                        final Card cc = (Card) card.getClientProperty("card");
                        game.playUserTurn(game.getCurrAttr(), cc);

                        updateScores();
                        resetHandIcons();
                        
                    }
                });
                pHand.add(card);
                
            }
            pHand.add(pScore, BorderLayout.WEST);
            pHand.add(pScore, BorderLayout.EAST);
        }
        
        this.add(oCards, BorderLayout.NORTH);
        this.add(mainField, BorderLayout.CENTER);
        this.add(pCards, BorderLayout.SOUTH);
       
        pScore.setBackground(Color.BLUE);
        oScore.setBackground(Color.RED);
        pScore.setEnabled(false);
        oScore.setEnabled(false);

        mainField.setOpaque(false);
        mainField.setLayout(new BorderLayout());
        mainField.add(scoreCol, BorderLayout.EAST);
        mainField.add(plays);
        plays.add(pPlay, BorderLayout.WEST);
        setTransparentWithIcon(oPlay);
        setTransparentWithIcon(pPlay);
        plays.add(oPlay, BorderLayout.EAST);
        plays.setOpaque(false);


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

    private void resetHandIcons() {
        updateScores();
        for (final Player p : game.getPlayers()) {
            final JPanel pHand = (p instanceof Mosquito) ? oCards : pCards;
            int handUnderSize = Game.HAND_SIZE - p.getHand().size();
            final Card newCard = p.getHand().getLast();
            for (final Component c : pHand.getComponents()) {
                if (c instanceof JButton) {
                    final JButton cc = (JButton) c;
                    final Card card = (Card) cc.getClientProperty("card");
                    cc.setVisible(true);
                    if (!p.getHand().contains(card) && handUnderSize == 0) {
                        cc.putClientProperty("card", newCard);
                        final ImageIcon bc = new ImageIcon(getClass().getResource(CARDS_IMAGE_PATH + newCard.getName() + ".png"));
                        final ImageIcon bg = new ImageIcon(
                            bc.getImage().getScaledInstance(CARDS_WIDTH, CARDS_HEIGHT, Image.SCALE_SMOOTH)
                        );
                        cc.setIcon(bg);
                        break;
                    } else if (handUnderSize > 0) {
                        cc.setVisible(false);
                        handUnderSize--;
                    }
                    
                }
            }
        }
    }

    private void updateScores() {
        for (final Player p : game.getPlayers()) {
            final JButton b = (p instanceof Mosquito) ? oScore : pScore;
            b.setText("" + game.getScore(p));
        }
    }

    private void setTransparentWithIcon(final JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(TRACK_PATH);
    }

    @Override
    protected Image getImage() {
        return playField;
    }
}
