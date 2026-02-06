package migglione.view.impl.scenesimpl;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;

public abstract class GamePanel extends JPanel {
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Image panelImg = getImage();
        g.drawImage(panelImg, 0, 0, getWidth(), getHeight(), this);
    }

    protected abstract Image getImage();
}
