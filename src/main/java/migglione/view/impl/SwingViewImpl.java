package migglione.view.impl;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Locale;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import migglione.view.api.Scene;
import migglione.view.api.SwingView;

/**
 * Implementation of the SwingView interface.
 * As such, it adopts the Swing style and rapresent the
 * "center" of the MVC method, it initialize the elements
 * of the frame and set the different scenes using CardLayout.
 * The frame must not be resizable under a certain amount and
 * is expected to make the user able to navigate to the various
 * contents of the application while changing soundtracks and backgrounds.
 */
public final class SwingViewImpl implements SwingView {

    private static final String FRAME_NAME = "Migglione: the game";
    private static final String MENU_SCENE = "MENU";
    private static final String CREDITS_SCENE = "CREDITS";
    private static final int INITIAL_WIDTH = 800;
    private static final int INITIAL_HEIGHT = 600;
    private static final String TRACK_PATH = "/soundtracks/Machine-Love-_feat.-Neuro-sama_-Neuro-sama-Community-Collab.wav";

    private final JFrame frame = new JFrame(FRAME_NAME);
    private final CardLayout cards = new CardLayout();
    private final JPanel firstPanel = new JPanel(cards);
    private String currentSceneName;
    private Clip audioClip;

    /**
     * The constructor of the class.
     * Thanks to the implementations of different scenes,
     * it has only the responsibility to change them and to set the
     * restraints of the frame, which is resizable over a certain point.
     */
    public SwingViewImpl() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        frame.setResizable(true);

        firstPanel.add(new Menu(this), MENU_SCENE);
        firstPanel.add(new Credits(this), CREDITS_SCENE);

        frame.add(firstPanel);
        frame.setVisible(true);
        setScene(MENU_SCENE);
        currentSceneName = MENU_SCENE;

        playMusic();
    }

    /**
     * Functional method in order to play music.
     * It takes the relative path of the soundtrack and
     * makes it so it loops whenever it ends.
     */
    private void playMusic() {
        try {
            final String soundtrackPath = TRACK_PATH;
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(soundtrackPath));

            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.start();
        } catch (final IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace(); //NOPMD Intentional design since we don't use logs
        }
    }

    @Override
    public void setScene(final String sceneName) {
        this.cards.show(firstPanel, sceneName);
        currentSceneName = sceneName;
    }

    @Override
    public String getScene() {
        return currentSceneName;
    }

    @Override
    public void quit() {
        endMusic();
        frame.dispose();
    }

    private void endMusic() {
        audioClip.stop();
        audioClip = null;
    }

    /**
     * Functional main put just to see the implementations better.
     * 
     * @param args is functional
     */
    public static void main(final String[] args) {
        Locale.setDefault(Locale.ROOT);
        new SwingViewImpl();
    }
}
