package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import migglione.model.api.Card;
import migglione.model.api.Cards;
import migglione.view.api.MusicPlayer;
import migglione.view.api.MusicStrategy;

public class Gallery extends JPanel implements MusicStrategy {

    private static final long serialVersionUID = 9879879879L;
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
    private static final String CARDS_IMAGE_PATH = "/images/cards/";
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
        final JPanel pGallery = new JPanel(new GridLayout(0, 3, 10, 10));
        final JButton back = new GenericButton(BACK, b -> view.setScene(SwingViewImpl.MENU_SCENE));

        pGallery.setOpaque(false);
        for (var entry : cards.entrySet()) {
            ImageIcon card = new ImageIcon(getClass().getResource(CARDS_IMAGE_PATH + entry.getValue().getName() + ".png"));
            JLabel cardLabel = new JLabel(card);
            pGallery.add(cardLabel);
        }

        pSouth.setOpaque(false);
        pSouth.add(back);

        this.add(pGallery, BorderLayout.CENTER);
        this.add(pSouth, BorderLayout.SOUTH);
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

