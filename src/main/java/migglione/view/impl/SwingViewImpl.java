package migglione.view.impl;

import java.awt.CardLayout;
import java.awt.Dimension;

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
    private static final int INITIAL_WIDTH = 800;
    private static final int INITIAL_HEIGHT = 600;

    private final JFrame frame = new JFrame(FRAME_NAME);
    private final CardLayout cards = new CardLayout();
    private final JPanel firstPanel = new JPanel(cards);

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

        frame.add(firstPanel);
        frame.setVisible(true);
        setScene(MENU_SCENE);
    }

    @Override
    public void setScene(final String sceneName) {
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

    /**
     * Functional main put just to see the implementations better.
     * 
     * @param args is functional
     */
    public static void main(final String[] args) {
        new SwingViewImpl();
    }
}
