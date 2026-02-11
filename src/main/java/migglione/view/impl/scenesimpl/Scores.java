package migglione.view.impl.scenesimpl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.SwingViewImpl;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

public final class Scores extends AbstractGamePanel implements MusicProvider {

private static final String TRACK_PATH = "/soundtracks/ENA Dream BBQ.wav";
private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
private final transient Image scorImage;
private static final String BACK = "Back";

    public Scores(final SwingViewImpl view) {
        this.setLayout(new BorderLayout());
        scorImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton back = new GenericButton(BACK, b -> view.setScene(Scenes.MENU.getScene()));

        pSouth.setOpaque(false);
        pSouth.add(back);
        this.add(pSouth, BorderLayout.SOUTH);

        
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(TRACK_PATH);
    }

    @Override
    protected Image getImage() {
        return scorImage;
    }
}
