package migglione.view.impl.scenesimpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.music.MusicTracks;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.SwingViewImpl;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

public final class Scores extends AbstractGamePanel implements MusicProvider {

private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
private final transient Image scorImage;
private static final String BACK = "Back";
private static final String FILE_TXT_PATH = "/file/ScoreTable.txt";
private JTextArea score;
//private BorderedLine bordered = (BorderedLine) new JLabel();


    public Scores(final SwingViewImpl view) {

        this.setLayout(new BorderLayout());
        scorImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JButton back = new GenericButton(BACK, b -> view.setScene(Scenes.MENU.getScene()));
        pSouth.setOpaque(false);
        pSouth.add(back);
        this.add(pSouth, BorderLayout.SOUTH);

        //this.score = new JTextArea();
        this.score = new BorderedLine();
        this.score.setEditable(false);
        this.score.setOpaque(false);
        this.score.setFont(new Font("Verdana", Font.PLAIN, 50));
        this.score.setForeground(new Color(252, 64, 167));
        this.score.setBorder(null);

        final JScrollPane pane = new JScrollPane(score);
        this.add(pane, BorderLayout.CENTER); 
        pane.setOpaque(false);
        pane.getViewport().setOpaque(false);
        pane.setBorder(null);

        readFile(FILE_TXT_PATH);

        /*se togli i comenti per usare bordered non mi fa fare start non toccare */
        //this.bordered = new BorderedLine(score.getText());

        /* 
        this.bordered.setEnabled(false);
        this.bordered.setOpaque(false);
        this.bordered.setFont(new Font("Verdana", Font.PLAIN, 50));
        //*/
    } 

    public void readFile(final String FILE_TXT_PATH) {

        final Path path = Paths.get(System.getProperty("user.home"), ".migglione", "ScoreTable.txt");

        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {

                String line;

                while((line = reader.readLine()) != null) {
                    score.append(line + "\n"); //
                }
            }
            catch (final IOException error) {
                JOptionPane.showMessageDialog(null, "error file reader", "error", 
                JOptionPane.ERROR_MESSAGE);
                error.printStackTrace();
            }
        }
    }

    public void refresh() {
        score.setText("");
        readFile(FILE_TXT_PATH);
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(MusicTracks.ENA.getTrackPath());
    }

    @Override
    protected Image getImage() {
        return scorImage;
    }
}
