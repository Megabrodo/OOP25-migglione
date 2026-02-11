package migglione.view.impl.scenesimpl;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTable;

import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.impl.SwingViewImpl;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

public final class Scores extends AbstractGamePanel implements MusicProvider {
private static final String TRACK_PATH = "/soundtracks/ENA Dream BBQ.wav";
private static final String BACKGROUND_IMAGE_PATH;

    Scores(final SwingViewImpl view) {

        

    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(TRACK_PATH);
    }

    @Override
    protected Image getImage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    }
}
