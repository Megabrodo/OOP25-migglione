package migglione.view.impl.scenesimpl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
private static final String FILE_TXT_PATH = "/resources/file/ScoreTable.txt";
private final JTextArea score;

    public Scores(final SwingViewImpl view) {

        /*
        scorImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();
        setTitle("PLAYERS SCORES");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        */

        this.setLayout(new BorderLayout());
        scorImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER)); //
        final JButton back = new GenericButton(BACK, b -> view.setScene(Scenes.MENU.getScene()));
        pSouth.setOpaque(false);
        pSouth.add(back);
        this.add(pSouth, BorderLayout.SOUTH);

        score = new JTextArea();
        score.setEditable(false);

        final JScrollPane pane = new JScrollPane(score);
        add(pane, BorderLayout.CENTER);

        addFile(FILE_TXT_PATH);

    }

    private final void addFile(String FILE_TXT_PATH) {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_TXT_PATH))) {
            String line;
            
            while((line = reader.readLine()) != null) {
                score.append(line + "\n");
            }
        } catch (final IOException error) {
                JOptionPane.showMessageDialog(this, "error file reader", "error", 
                JOptionPane.ERROR_MESSAGE);
            }
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
