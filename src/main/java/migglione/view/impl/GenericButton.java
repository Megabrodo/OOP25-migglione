package migglione.view.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GenericButton extends JButton {

    private static final String FONT_NAME = "Times New Roman";
    private static final int WIDTH = 250;
    private static final int HEIGHT = 75;
    private static final int FONT_SIZE = 26;
    
    public GenericButton(String text, ActionListener action) {
        this.setText(text);
        this.addActionListener(action);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        this.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        this.setForeground(Color.YELLOW);
        this.setBackground(Color.BLACK);
        this.setFocusPainted(false);
    }
}
