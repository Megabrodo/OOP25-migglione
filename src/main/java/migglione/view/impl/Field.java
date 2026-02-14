package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import migglione.controller.api.Controller;
import migglione.model.api.Player;
import migglione.model.impl.Card;
import migglione.model.impl.GameImpl;
import migglione.model.impl.Mosquito;
import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.music.MusicTracks;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;
import migglione.view.impl.scenesimpl.AbstractGamePanel;

/** 
 * Class for managing the playing field's view, 
 * with both player's hands, decks and scores,
 * along with a settings section.
 * */
public final class Field extends AbstractGamePanel implements MusicProvider {

    private static final String CARDS_IMAGE_PATH = "/images/cards/";
    private static final String CARD_BACKSIDE_PATH = "/images/utilities/backside.png";
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
    private static final String FONT_NAME = "Times New Roman";
    private static final int CARDS_WIDTH = 180;
    private static final int CARDS_HEIGHT = 230;
    private static final int SPACE_BETWEEN_CARDS = 500;
    private static final int ATTR_BOX_WIDTH = 200;
    private static final int ATTR_BOX_HEIGHT = 150;

    private final Font titleFont = new Font(FONT_NAME, Font.BOLD, 17);
    private final Font boxFont = new Font(FONT_NAME, Font.BOLD, 23);

    private final transient Image playField;
    private final Controller controller;
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

    private int cycleCount = 0;
    private Optional<Timer> timer = Optional.empty();

    /**
     * Constructor of this class. 
     * Divides screen into play field, player's hand,
     * opponent's hand, menu and scores.
     */
    public Field(final Controller controller) {

        this.controller = controller;
        this.playField = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();
        this.setLayout(new BorderLayout());

        pCards.setOpaque(false);
        oCards.setOpaque(pCards.isOpaque());
        pCards.setLayout(new FlowLayout(FlowLayout.CENTER, SPACE_BETWEEN_CARDS, SPACE_BETWEEN_CARDS));
        oCards.setLayout(pCards.getLayout());

        final JPanel attrHold = createAttributeBox();

        for (final Player p : controller.getPlayers()) {
            final boolean isCPU = p.equals(controller.getPlayers().getLast());
            final JPanel pHand = (isCPU) ? oCards : pCards;
            for (final Card c : p.getHand()) {
                final JButton card = new JButton();
                changeIcon(card, isCPU ? CARD_BACKSIDE_PATH : CARDS_IMAGE_PATH + c.getName() + ".png");
                setTransparentWithIcon(card);
                card.putClientProperty("card", c);
                if(!isCPU) {
                    card.addMouseListener(new Hovering(c, mainField));
                    //card.setPreferredSize(getPreferredSize());
                    card.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent dispose) {
                            if (!timer.isEmpty()) {
                                return;
                            }
                            cycleCount = 0;
                            final Card cc = (Card) card.getClientProperty("card");
                            Timer t = new Timer(1000, new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    switch (cycleCount) {
                                        case 0: if (controller.getTurnLeader().equals(p)) {
                                                    controller.playTurnLead(attrChoice.getItemAt(attrChoice.getSelectedIndex()), cc);
                                                } else {
                                                    controller.playTurnTail(cc);
                                                }
                                                pPlay.putClientProperty("card",cc);
                                                changeIcon(pPlay, CARDS_IMAGE_PATH + cc.getName() + ".png");
                                                pPlay.setVisible(true); 
                                                pPlay.addMouseListener(new Hovering(cc, plays));
                                                //card.setVisible(false);
                                                resetHandIcons();
                                                break;
                                        case 1:                                                                                                 
                                                break;
                                        case 2: if (controller.getTurnLeader().equals(p)) {
                                                    controller.playTurnTail(cc);
                                                    final Card sub = controller.getPlayers().getLast().getPlayedCard();
                                                    oPlay.putClientProperty("card", sub);
                                                    changeIcon(oPlay, CARD_BACKSIDE_PATH);
                                                    
                                                }
                                                resetHandIcons();
                                                break;
                                        case 3: flipCards();
                                                controller.playTurn();
                                                break;
                                        case 4: updateScores();
                                                Set.of(pPlay, oPlay).forEach(jb -> {
                                                    jb.setIcon(null);
                                                    for (final MouseListener ml : jb.getMouseListeners()) {
                                                        jb.removeMouseListener(ml);
                                                    }
                                                });
                                                if (!p.equals(controller.getTurnLeader())) {
                                                    controller.playTurnLead(attrs[0],cc);
                                                    final Card sub = controller.getTurnLeader().getPlayedCard();
                                                    oPlay.putClientProperty("card", sub);
                                                    changeIcon(oPlay, CARD_BACKSIDE_PATH);
                                                    oPlay.setVisible(true);
                                                    attrChoice.setEnabled(false);
                                                } else {
                                                    attrChoice.setEnabled(true);
                                                }
                                                attrChoice.setSelectedItem(controller.getCurrAttr());
                                                resetHandIcons();
                                                ((Timer)e.getSource()).stop();
                                                controller.checkSession();
                                                timer = Optional.empty();
                                                break;
                                    }
                                    cycleCount++;
                                }
                                
                            });
                            timer = Optional.of(t);
                            t.start();
                        }
                    });
                }
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
        Set.of(oPlay, pPlay).forEach(b -> {
            setTransparentWithIcon(b);
        });
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

    private void changeIcon(final JButton jb, final String path) {
        final ImageIcon bc = new ImageIcon(
            getClass().getResource(path)
        );
        final ImageIcon bg = new ImageIcon(
            bc.getImage().getScaledInstance(CARDS_WIDTH, CARDS_HEIGHT, Image.SCALE_SMOOTH)
        );
        jb.setIcon(bg);
    }
    private void flipCards() {
        for (final JButton jb : Set.of(oPlay, pPlay)) {
            changeIcon(jb, CARDS_IMAGE_PATH + ((Card)jb.getClientProperty("card")).getName() + ".png");
            jb.addMouseListener(new Hovering((Card)jb.getClientProperty("card"), plays));
        }
    }

    private void resetHandIcons() {
        for (final Player p : controller.getPlayers()) {
            System.out.println(p.getName() + " hand is: ");
            p.getHand().forEach(c -> System.out.println(c.getCard()));
            final boolean isCPU = p.equals(controller.getPlayers().getLast());
            final JPanel pHand = (isCPU) ? oCards : pCards;
            //move CPU displaying into the center down here - change return of playUserTurn to bool for CPU turn lead
            int handUnderSize = GameImpl.HAND_SIZE - p.getHand().size();
            for (final Component c : pHand.getComponents()) {
                if (c instanceof JButton) {
                    final JButton cc = (JButton) c;
                    try {
                        final Card newCard = p.getHand().getLast();
                        //System.out.println(isCPU + " has ");
                        //p.getHand().stream().forEach(d -> System.out.println(d.getCard()));
                        final Card card = (Card) cc.getClientProperty("card");
                    cc.setVisible(true);
                    if (!p.getHand().contains(card)) {
                        if (handUnderSize == 0){
                            //System.out.println("replacing..." + card.getName() + " to " + newCard.getName());
                            cc.putClientProperty("card", newCard);
                           changeIcon(cc, isCPU ? CARD_BACKSIDE_PATH : CARDS_IMAGE_PATH + newCard.getName() + ".png");
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
                            handUnderSize--;
                        } 
                    }
                    } catch (final NoSuchElementException e) {
                        cc.setVisible(false);
                    }
                }
            }
        }
        System.out.println("-----");
    }

    private void updateScores() {
        for (final Player p : controller.getPlayers()) {
            final JButton b = (p instanceof Mosquito) ? oScore : pScore;
            b.setText("" + controller.getScore(p));
        }
    }

    private void setTransparentWithIcon(final JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    private JPanel createAttributeBox() {
        final JPanel attrBox = new JPanel();
        final JLabel title = new JLabel("CURRENT ATTRIBUTE:");

        title.setFont(titleFont);
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setBackground(Color.BLACK);
        title.setOpaque(true);
        title.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        title.setForeground(Color.YELLOW);

        attrBox.setLayout(new BoxLayout(attrBox, BoxLayout.Y_AXIS));
        attrBox.add(Box.createVerticalGlue());
        attrBox.add(title);

        attrChoice.setFont(boxFont);
        attrChoice.setBackground(Color.BLACK);
        attrChoice.setForeground(Color.YELLOW);
        attrChoice.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        attrChoice.setAlignmentX(CENTER_ALIGNMENT);
        attrChoice.setMaximumSize(new Dimension(ATTR_BOX_WIDTH, ATTR_BOX_HEIGHT));

        attrBox.add(attrChoice);
        attrBox.add(Box.createVerticalGlue());
        attrBox.setOpaque(false);

        return attrBox;
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(MusicTracks.DELTARUNE.getTrackPath());
    }

    @Override
    protected Image getImage() {
        return playField;
    }
}
