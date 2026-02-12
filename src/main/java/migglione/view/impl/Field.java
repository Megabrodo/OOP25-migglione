package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
    private static final int CARDS_WIDTH = 180;
    private static final int CARDS_HEIGHT = 230;
    private static final int SPACE_BETWEEN_CARDS = 500;
    private final transient Image playField;
    private final Game game;
    private final String attrs[] = {"Attk", "Deff", "Strength", "Intelligence", "Stealth"};

    private final JPanel pCards = new JPanel();
    private final JPanel oCards = new JPanel();
    private final JPanel mainField = new JPanel();
    private final JPanel scoreCol = new JPanel();
    private final JPanel plays = new JPanel();
    private final JButton pScore = new JButton("0");
    private final JButton oScore = new JButton("0");
    private final JButton pPlay = new JButton();
    private final JButton oPlay = new JButton(); 
    private final JComboBox<String> attrChoice = new JComboBox<>(attrs);

    /**
     * Constructor of this class. 
     * Divides screen into play field, player's hand,
     * opponent's hand, menu and scores.
     */
    public Field(final Controller controller) {
        
        this.game = controller.getModel();
        this.playField = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();
        this.setLayout(new BorderLayout());

        pCards.setOpaque(false);
        oCards.setOpaque(pCards.isOpaque());
        pCards.setLayout(new FlowLayout(FlowLayout.CENTER, SPACE_BETWEEN_CARDS, SPACE_BETWEEN_CARDS)); //maybe grid is better?
        oCards.setLayout(pCards.getLayout());

        final JPanel attrHold = new JPanel();
        final JTextArea title = new JTextArea("CURRENT ATTRIBUTE:");
        attrHold.setLayout(new BoxLayout(attrHold, BoxLayout.Y_AXIS));
        attrHold.add(Box.createVerticalGlue());
        attrHold.add(title);
        attrHold.add(attrChoice);
        attrHold.add(Box.createVerticalGlue());
        

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
                card.setBorderPainted(false);
                card.setFocusPainted(false);
                card.addMouseListener(new Hovering(c, mainField));
                card.putClientProperty("card", c);
                //card.setPreferredSize(getPreferredSize());
                card.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent dispose) {
                        final JButton pB = (p instanceof Mosquito) ? oPlay : pPlay;
                        pB.setVisible(true);
                        pB.setIcon(bg);
                        final Card cc = (Card) card.getClientProperty("card");
                        final Card sub = game.playUserTurn(attrChoice.getItemAt(attrChoice.getSelectedIndex()), cc);
                        if (sub != null) {
                            System.out.println(sub.getCard());
                            final ImageIcon subb = new ImageIcon(getClass().getResource(CARDS_IMAGE_PATH + sub.getName() + ".png"));
                            final ImageIcon subbg = new ImageIcon(
                                subb.getImage().getScaledInstance(CARDS_WIDTH, CARDS_HEIGHT, Image.SCALE_SMOOTH)
                            );
                            oPlay.setIcon(subbg);
                            oPlay.setVisible(true);
                            attrChoice.setEnabled(false);
                        } else {
                            attrChoice.setEnabled(true);
                        }
                        attrChoice.setSelectedItem(game.getCurrAttr());
                        updateScores();
                            resetHandIcons();
                        if (game.matchEnded()) {
                            controller.endMatch();
                        }
                    }
                });
                pHand.add(card);
                
            }
        }
        
        this.add(oCards, BorderLayout.NORTH);
        this.add(mainField, BorderLayout.CENTER);
        this.add(pCards, BorderLayout.SOUTH);
       
        pScore.setBackground(Color.BLUE);
        oScore.setBackground(Color.RED);
        pScore.setEnabled(false);
        oScore.setEnabled(false);
        pScore.setBorderPainted(false);
        oScore.setBorderPainted(false);

        mainField.setOpaque(false);
        mainField.setLayout(new BorderLayout());
        mainField.add(scoreCol, BorderLayout.EAST);
        mainField.add(attrHold, BorderLayout.WEST);
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
        for (final Player p : game.getPlayers()) {
            final JPanel pHand = (p.equals(game.getPlayers().getLast())) ? oCards : pCards;
            final JButton pCenter = (p.equals(game.getPlayers().getLast())) ? oPlay : pPlay;
            pCenter.setVisible(false);
            //move CPU displaying into the center down here - change return of playUserTurn to bool for CPU turn lead
            boolean handUnderSize = (Game.HAND_SIZE - p.getHand().size() - (p.equals(game.getTurnLeader()) && p.equals(game.getPlayers().getLast()) ? 1 : 0)) > 0;
            for (final Component c : pHand.getComponents()) {
                if (c instanceof JButton) {
                    final JButton cc = (JButton) c;
                    try {
                        final Card newCard = p.getHand().getLast();
                        final Card card = (Card) cc.getClientProperty("card");
                    cc.setVisible(true);
                    if (!p.getHand().contains(card)) {
                        System.out.println(handUnderSize);
                        if (!handUnderSize){
                            System.out.println("replacing..." + card.getName() + " to " + newCard.getName());
                            cc.putClientProperty("card", newCard);
                            final ImageIcon bc = new ImageIcon(getClass().getResource(CARDS_IMAGE_PATH + newCard.getName() + ".png"));
                            final ImageIcon bg = new ImageIcon(
                                bc.getImage().getScaledInstance(CARDS_WIDTH, CARDS_HEIGHT, Image.SCALE_SMOOTH)
                            );
                            cc.setIcon(bg);
                            for (final var l : cc.getMouseListeners()) {
                                if (l instanceof Hovering) {
                                    final Hovering hv = (Hovering) l;
                                    hv.setHoveredCard(newCard);
                                    break;
                                }
                            }
                            break;
                        } else {
                            cc.setVisible(false);
                        } 
                    }
                    } catch (final NoSuchElementException e) {
                        cc.setVisible(false);
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
