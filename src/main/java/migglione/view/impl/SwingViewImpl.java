package migglione.view.impl;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import migglione.view.api.MusicPlayer;
import migglione.view.api.MusicStrategy;
import migglione.view.api.SceneFactory;
import migglione.view.api.Scenes;
import migglione.view.api.SwingView;

/**
 * Implementation of the SwingView interface.
 * 
 * <p>
 * As such, it adopts the Swing style and rapresent the
 * "center" of the MVC method, it initialize the elements
 * of the frame and set the different scenes using CardLayout.
 * 
 * <p>
 * The frame must not be resizable under a certain amount and
 * is expected to make the user able to navigate to the various
 * contents of the application while changing soundtracks and backgrounds.
 */
public final class SwingViewImpl implements SwingView {

    private static final Dimension MONITOR_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int INITIAL_WIDTH = (int) (MONITOR_DIMENSION.getWidth() * 0.7);
    private static final int INITIAL_HEIGHT = (int) (MONITOR_DIMENSION.getHeight() * 0.7);
    private static final int MIN_WIDTH = (int) (INITIAL_WIDTH * 0.9);
    private static final int MIN_HEIGHT = (int) (INITIAL_HEIGHT * 0.9);
    private static final String FRAME_NAME = "Migglione: the game";

    private final JFrame frame = new JFrame(FRAME_NAME);
    private final CardLayout cards = new CardLayout();
    private final JPanel firstPanel = new JPanel(cards);
    private final SceneFactory sceneCreator;
    private String currentSceneName;
    private MusicPlayer music;

    /**
     * The constructor of the class.
     * Thanks to the implementations of different scenes,
     * it has only the responsibility to change them and to set the
     * restraints of the frame, which is resizable over a certain point.
     */
    public SwingViewImpl() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        frame.setResizable(true);

        sceneCreator = new SceneFactoryImpl();
        firstPanel.add(sceneCreator.createScene(this, Scenes.MENU), Scenes.MENU.getScene());
        firstPanel.add(sceneCreator.createScene(this, Scenes.CREDITS), Scenes.CREDITS.getScene());
        firstPanel.add(sceneCreator.createScene(this, Scenes.GALLERY), Scenes.GALLERY.getScene());

        frame.add(firstPanel);
        frame.setVisible(true);

        setScene(Scenes.MENU.getScene());
    }

    @Override
    public void setScene(final String sceneName) {

        this.cards.show(firstPanel, sceneName);
        currentSceneName = sceneName;

        for (final var c : firstPanel.getComponents()) {
            if (c.isVisible()) {
                if (c instanceof Gallery gallery) {
                    gallery.resetScrollBar();
                }
                if (c instanceof MusicStrategy musicGetter) {
                    final MusicPlayer newMusic = musicGetter.getMusic();
                    if (music != null) {
                        if (!sameMusic(music, newMusic)) {
                            endMusic();
                            this.music = musicGetter.getMusic();
                            this.music.playMusic();
                        }
                    } else {
                        this.music = newMusic;
                        this.music.playMusic();
                    }
                    break;
                }
            }
        }
    }

    private boolean sameMusic(final MusicPlayer oldMusic, final MusicPlayer newMusic) {
        return newMusic.getPath().equals(oldMusic.getPath());
    }

    private void endMusic() {
        music.stopMusic();
        music = null;
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

    /**
     * Functional main put just to see the implementations better.
     * 
     * @param args is functional
     */
    public static void main(final String[] args) {
        new SwingViewImpl();
    }
}
