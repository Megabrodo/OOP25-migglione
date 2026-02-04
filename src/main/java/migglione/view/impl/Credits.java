package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import migglione.view.api.MusicPlayer;
import migglione.view.api.MusicStrategy;
import migglione.view.api.Scenes;

/**
 * Class Credits scene of the application,
 * it simply presents the creators and gives
 * credits to whoever was involved in the project.
 */
public final class Credits extends JPanel implements MusicStrategy {

    private static final long serialVersionUID = 9879879879L;
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/credits.png";
    private static final String TRACK_PATH = "/soundtracks/Jodio-vibin-to-his-opening.wav";
    private static final String BACK = "Back";
    private final transient Image creditsImage;

    /**
     * Constructor of Credits.
     * 
     * @param view is used to return back to the Menu
     */
    public Credits(final SwingViewImpl view) {
        this.setLayout(new BorderLayout());

        creditsImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton back = new GenericButton(BACK, b -> view.setScene(Scenes.MENU.getScene()));

        pSouth.setOpaque(false);
        pSouth.add(back);
        this.add(pSouth, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(creditsImage, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(TRACK_PATH);
    }
}
