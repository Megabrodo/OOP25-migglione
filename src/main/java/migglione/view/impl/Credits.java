package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public final class Credits extends JPanel {
    
    private static final long serialVersionUID = 9879879879L;
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/credits.png";
    private final transient Image creditsImage;

    public Credits(final SwingViewImpl view) {
        this.setLayout(new BorderLayout());

        creditsImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(creditsImage, 0, 0, getWidth(), getHeight(), this);
    }
}
