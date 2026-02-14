package migglione.view.impl.scenesimpl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class BorderedLine extends JLabel{
private Color borderedLine = Color.getHSBColor(63, 62, 90);
private float stroke = 6f;

    public BorderedLine(final String FILE_TXT){
        super(FILE_TXT);
    }

    void colorPaint(Graphics graphics) {

        Graphics2D graphic = (Graphics2D) graphics;

    }
}
