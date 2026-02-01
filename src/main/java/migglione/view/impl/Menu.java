package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

    private final SwingViewImpl view;
    private final Image titleImage;
    private static final String TITLE = "Il Migglione";
    private static final String GALLERY = "Gallery";
    private static final String FONT_NAME = "Times New Roman";
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";

    public Menu(SwingViewImpl view) {
        this.view = view;
        this.setLayout(new BorderLayout());
        JPanel cPanel = new JPanel();
        JPanel titleBox = new JPanel();
        JLabel title = new JLabel(TITLE);
        JButton gallery = new GenericButton(GALLERY, b -> System.exit(0));

        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.Y_AXIS));
        cPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cPanel.setOpaque(false);

        titleImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        titleBox.setLayout(new BorderLayout());
        titleBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        titleBox.setBackground(Color.BLACK);
        titleBox.add(title, BorderLayout.CENTER);

        title.setFont(new Font(FONT_NAME, Font.BOLD, 70));
        title.setForeground(Color.YELLOW);
        title.setHorizontalAlignment(JLabel.CENTER);

        cPanel.add(Box.createVerticalGlue());
        cPanel.add(titleBox);
        cPanel.add(Box.createVerticalGlue());

        cPanel.add(gallery);
        cPanel.add(Box.createVerticalGlue());

        this.add(cPanel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(titleImage, 0, 0, getWidth(), getHeight(), this);
    }
}
