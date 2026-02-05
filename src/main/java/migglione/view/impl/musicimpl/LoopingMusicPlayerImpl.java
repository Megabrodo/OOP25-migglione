package migglione.view.impl.musicimpl;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import migglione.view.api.music.MusicPlayer;

/**
 * Implementation of MusicPlayer.
 * 
 * <p>
 * This rapresents the main implementation used for
 * most of the tracks in the application, since it
 * simply makes the music loops itself upon its ending
 * and upon invocating stopMusic it completly cuts off the Clip
 */
public final class LoopingMusicPlayerImpl implements MusicPlayer {

    private final String trackPath;
    private Clip audioClip;

    /**
     * Constructor the class.
     * 
     * @param trackPath is the path of the track to loop
     */
    public LoopingMusicPlayerImpl(final String trackPath) {
        this.trackPath = trackPath;
    }

    @Override
    public void playMusic() {
        try {
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(trackPath));

            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.start();
        } catch (final IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace(); //NOPMD Intentional design since we don't use logs
        }
    }

    @Override
    public void stopMusic() {
        audioClip.stop();
        audioClip = null;
    }

    @Override
    public String getPath() {
        return this.trackPath;
    }

}
