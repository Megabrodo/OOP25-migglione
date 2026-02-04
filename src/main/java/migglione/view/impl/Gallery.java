package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import migglione.model.impl.Card;
import migglione.model.impl.Cards;
import migglione.view.api.MusicPlayer;
import migglione.view.api.MusicStrategy;
import migglione.view.api.Scenes;

/**
 * The class Gallery allows to see all the sprites of the cards.
 * 
 * <p>
 * It incapsulates the sprites in a gridlayout, allowing
 * the view to be put in another container that covers part
 * of the screen. The sprites are taken from the folder in
 * resources and is bound by the database (Cards.java)
 * 
 * <p>
 * Moving in the container involves the usage of a ScrollPane
 * and the container itself is completely created in the private
 * createGalleryBox method, which makes sure that only the sprites
 * are visible (looking at the values would be cheating!)
 */
public final class Gallery extends JPanel implements MusicStrategy {

    private static final long serialVersionUID = 9879879879L;
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
    private static final String CARDS_IMAGE_PATH = "/images/cards/";
    private static final int CARDS_WIDTH = 150;
    private static final int CARDS_HEIGHT = 200;
    private static final String TRACK_PATH = "/soundtracks/Jodio-vibin-to-his-opening.wav";
    private static final String BACK = "Back";

    private final transient Image galleryImage;
    private final Cards database;

    /**
     * Constructor of Gallery.
     * 
     * @param view is used to return back to the Menu
     */
    public Gallery(final SwingViewImpl view) {
        this.database = new Cards();
        final Map<Integer, Card> cards = database.getCards();

        this.setLayout(new BorderLayout());
        galleryImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JPanel pGallery = createGalleryBox(cards);
        final JButton back = new GenericButton(BACK, b -> view.setScene(Scenes.MENU.getScene()));

        pSouth.setOpaque(false);
        pSouth.add(back);

        this.add(pGallery, BorderLayout.CENTER);
        this.add(pSouth, BorderLayout.SOUTH);
    }

    private JPanel createGalleryBox(final Map<Integer, Card> cards) {

        final JPanel galleryBox = new JPanel(new BorderLayout());
        final JPanel cardsGrid = new JPanel(new GridLayout(0, 3, 10, 20));
        cardsGrid.setOpaque(false);

        for (final var entry : cards.entrySet()) {
            final ImageIcon card = new ImageIcon(getClass().getResource(CARDS_IMAGE_PATH + entry.getValue().getName() + ".png"));
            final ImageIcon scaledCard = new ImageIcon(
                card.getImage().getScaledInstance(CARDS_WIDTH, CARDS_HEIGHT, Image.SCALE_SMOOTH)
            );
            final JLabel cardLabel = new JLabel(scaledCard);
            cardLabel.setHorizontalAlignment(JLabel.CENTER);
            cardsGrid.add(cardLabel);
        }

        final JScrollPane galleryScroll = new JScrollPane(cardsGrid);

        galleryScroll.getViewport().setBackground(Color.BLACK);
        galleryScroll.setOpaque(false);
        galleryScroll.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
        galleryScroll.getVerticalScrollBar().setBackground(Color.BLACK);

        galleryBox.add(galleryScroll, BorderLayout.CENTER);
        return galleryBox;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(galleryImage, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(TRACK_PATH);
    }
}

