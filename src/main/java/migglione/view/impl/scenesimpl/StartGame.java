package migglione.view.impl.scenesimpl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import migglione.controller.api.Controller;
import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.SwingViewImpl;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

public class StartGame extends AbstractGamePanel implements MusicProvider {

    private static final long serialVersionUID = 9879879879L;
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
    private static final String TRACK_PATH = "/soundtracks/ENA Dream BBQ.wav";
    private static final String BACK = "Back";
    private final transient Image startImage;

    /**
     * Constructor of Credits.
     * 
     * @param view is used to return back to the Menu
     */
    public StartGame(final SwingViewImpl view, final Controller controller) {
        this.setLayout(new BorderLayout());

        startImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        final JButton start = new GenericButton("Start!", a -> {
            String playerName = JOptionPane.showInputDialog("Enter your name to play:");
            controller.startMatch(playerName);
        });

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton back = new GenericButton(BACK, b -> view.setScene(Scenes.MENU.getScene()));

        pSouth.setOpaque(false);
        pSouth.add(back);
        this.add(pSouth, BorderLayout.SOUTH);
        this.add(start, BorderLayout.CENTER);
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(TRACK_PATH);
    }

    @Override
    protected Image getImage() {
        return startImage;
    }
}

