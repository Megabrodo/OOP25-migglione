package migglione.view.impl.scenesimpl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.SwingViewImpl;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

public class Tutorial extends AbstractGamePanel implements MusicProvider {
    private static final long serialVersionUID = 9879879879L;
    private static final String TRACK_PATH = "/soundtracks/ENA Dream BBQ.wav";
    private static final String TUTORIAL_IMAGES_PATH = "/images/Tutorial/";
    private static final String BACK = "Back";
    private static final String FORWARD = "Forward";
    private transient Image tutorialImage;
    private int currentImageIndex = 1;

    public Tutorial(final SwingViewImpl view) {
        this.setLayout(new BorderLayout());
        tutorialImage = new ImageIcon(getClass().getResource(TUTORIAL_IMAGES_PATH + String.valueOf(currentImageIndex) + ".png")).getImage();

        final JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JPanel tutorialPanel = new JPanel(new BorderLayout());
        final JButton back = new GenericButton(BACK, b -> updateTutorialImage(-1, view));
        final JButton forward = new GenericButton(FORWARD, b -> updateTutorialImage(1, view));

        buttonsPanel.setOpaque(false);
        buttonsPanel.add(back);
        buttonsPanel.add(forward);
        tutorialPanel.setOpaque(false);
        tutorialPanel.add(buttonsPanel, BorderLayout.SOUTH);

        this.add(tutorialPanel, BorderLayout.CENTER);
    }

    private void updateTutorialImage(int index, final SwingViewImpl view) {
        currentImageIndex += index;
        if (currentImageIndex == 0) {
            currentImageIndex = 1;
            view.setScene(Scenes.MENU.getScene());
        } else if (currentImageIndex > 6) {
            currentImageIndex = 1;
            view.setScene(Scenes.MENU.getScene());
        }
        tutorialImage = new ImageIcon(getClass().getResource(TUTORIAL_IMAGES_PATH + String.valueOf(currentImageIndex) + ".png")).getImage();
        repaint();
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(TRACK_PATH);
    }

    @Override
    protected Image getImage() {
        return tutorialImage;
    }
    
}
