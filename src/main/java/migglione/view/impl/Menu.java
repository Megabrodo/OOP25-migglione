package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class designed to construct the Menu scene of the application,
 * it offers the option to start the game, see the tutorial, the gallery,
 * the credits and to quit the application.
 */
public final class Menu extends JPanel {

    private static final String TITLE = "Il Migglione";
    private static final String START_GAME = "Start Game";
    private static final String TUTORIAL = "Tutorial";
    private static final String GALLERY = "Gallery";
    private static final String CREDITS = "Credits";
    private static final String QUIT = "Quit";
    private static final String FONT_NAME = "Times New Roman";
    private static final int FONT_SIZE = 70;
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
    private static final String TRACK_PATH = "/soundtracks/Machine-Love-_feat.-Neuro-sama_-Neuro-sama-Community-Collab.wav";
    private final SwingViewImpl view;
    private final Image titleImage;
    private Clip audioClip;

    /**
     * Constructor of the class.
     * The Menu is designed to change its size while changing the window's,
     * so are the distances of the buttons and the image set as a background.
     * It is also designed to play music.
     * 
     * @param view is used in order to associate the changing scenes to the buttons
     */
    public Menu(final SwingViewImpl view) {
        this.view = view;
        this.setLayout(new BorderLayout());
        playMusic();

        final JPanel cPanel = new JPanel();
        final JPanel titleBox = new JPanel();
        final JLabel title = new JLabel(TITLE);
        final JButton startButton = new GenericButton(START_GAME, b -> System.exit(0));
        final JButton tutorial = new GenericButton(TUTORIAL, b -> System.exit(0));
        final JButton gallery = new GenericButton(GALLERY, b -> System.exit(0));
        final JButton credits = new GenericButton(CREDITS, b -> System.exit(0));
        final JButton quit = new GenericButton(QUIT, b -> System.exit(0));

        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.Y_AXIS));
        cPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cPanel.setOpaque(false);

        titleImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        titleBox.setLayout(new BorderLayout());
        titleBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        titleBox.setBackground(Color.BLACK);
        titleBox.add(title, BorderLayout.CENTER);

        title.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        title.setForeground(Color.YELLOW);
        title.setHorizontalAlignment(JLabel.CENTER);

        cPanel.add(Box.createVerticalGlue());
        cPanel.add(titleBox);
        cPanel.add(Box.createVerticalGlue());

        cPanel.add(startButton);
        cPanel.add(Box.createVerticalGlue());
        cPanel.add(tutorial);
        cPanel.add(Box.createVerticalGlue());
        cPanel.add(gallery);
        cPanel.add(Box.createVerticalGlue());
        cPanel.add(credits);
        cPanel.add(Box.createVerticalGlue());
        cPanel.add(quit);
        cPanel.add(Box.createVerticalGlue());

        this.add(cPanel, BorderLayout.CENTER);
    }

    /**
     * Functional method in order to play music.
     * It takes the relative path of the soundtrack and
     * makes it so it loops whenever it ends.
     */
    private void playMusic() {
        try {
            final String soundtrackPath = TRACK_PATH;
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                getClass().getResourceAsStream(soundtrackPath));

            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.start();
        } catch (final IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            System.err.println("Error in loading music:" + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(titleImage, 0, 0, getWidth(), getHeight(), this);
    }
}
