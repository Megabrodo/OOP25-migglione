package migglione.view.impl;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import migglione.view.api.Scene;
import migglione.view.api.SwingView;

public final class SwingViewImpl implements SwingView {

    private static final String START = "Start Game";
    private static final String GALLERY = "Gallery";
    private static final String QUIT = "Quit";
    private static final String FRAME_NAME = "Migglione: the game";

    private final JFrame frame = new JFrame(FRAME_NAME);
    private final CardLayout cards = new CardLayout();
    private final JPanel firstPanel = new JPanel(cards);

    public SwingViewImpl() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setResizable(true);

        firstPanel.add(new Menu(this), "MENU");

        frame.add(firstPanel);
        frame.setVisible(true);
        setScene("MENU");
    }

    @Override
    public void setScene(String sceneName) {
        this.cards.show(firstPanel, sceneName);
    }

    @Override
    public JPanel getScene() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScene'");
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refresh'");
    }

    public static void main(String[] args) {
        new SwingViewImpl();
    }
}
