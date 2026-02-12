package migglione.view.impl.scenesimpl;

import java.awt.Image;

import javax.swing.ImageIcon;

import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.impl.SwingViewImpl;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

public class Tutorial extends AbstractGamePanel implements MusicProvider {
    private static final long serialVersionUID = 9879879879L;
    private static final String TRACK_PATH = "/soundtracks/ENA Dream BBQ.wav";
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/credits.png";
    private final transient Image creditsImage;


    public Tutorial(final SwingViewImpl view) {
        this.setLayout(null);
        creditsImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(TRACK_PATH);
    }

    @Override
    protected Image getImage() {
        return creditsImage;
    }
    
}
