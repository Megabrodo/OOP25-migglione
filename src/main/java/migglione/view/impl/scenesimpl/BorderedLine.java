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
import java.util.Arrays;
import java.util.List;

import javax.swing.JTextArea;

public class BorderedLine extends JTextArea {
    private Color borderedLine = Color.getHSBColor(0.63f, 0.62f, 0.90f); 
    private Color textColor = Color.getHSBColor(0.353f, 0.56f, 0.86f);
    private float stroke = 6f;

    public BorderedLine() {
        super();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        
        Graphics2D graphic = (Graphics2D) graphics;

        graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphic.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        String text = getText();
        if (text == null || text.isEmpty()) return;

        FontMetrics metrics = graphic.getFontMetrics(getFont());

        int tx = getInsets().left;
        int ty = metrics.getAscent() + getInsets().top;
        int height = metrics.getHeight();

        List<String> lines = Arrays.asList(text.split("\n"));

        for(int i=0;i<lines.size();i++) {

            TextLayout layout = new TextLayout(lines.get(i),getFont(), graphic.getFontRenderContext());
            AffineTransform transform = AffineTransform.getTranslateInstance(tx, ty +(i * height));
            Shape outline = layout.getOutline(transform);

            graphic.setColor(borderedLine);
            graphic.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphic.draw(outline);

            graphic.setColor(textColor);
            graphic.fill(outline);
        }
    }
}