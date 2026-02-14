package migglione.view.impl.scenesimpl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;

public class BorderedLine extends JLabel {
    private Color borderedLine = Color.getHSBColor(0.63f, 0.62f, 0.90f); 
    private Color textColor = Color.getHSBColor(0.353f, 0.56f, 0.86f);
    private float stroke = 6f;

    public BorderedLine(String text) {
        super(text);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        
        Graphics2D graphic = (Graphics2D) graphics;

        graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphic.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        String text = getText();
        if (text == null || text.isEmpty()) return;

        FontMetrics metrics = graphic.getFontMetrics();

        int tx = getInsets().left;
        int ty = metrics.getAscent() + getInsets().top;

        TextLayout layout = new TextLayout(text, getFont(), graphic.getFontRenderContext());
        AffineTransform transform = AffineTransform.getTranslateInstance(tx, ty);
        Shape outline = layout.getOutline(transform);

        graphic.setColor(borderedLine);
        graphic.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphic.draw(outline);

        graphic.setColor(textColor);
        graphic.fill(outline);
    }
}