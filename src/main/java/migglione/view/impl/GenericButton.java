package migglione.view.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GenericButton extends JButton {

    private static final String FONT_NAME = "Times New Roman";
    
    public GenericButton(String text, ActionListener action) {
        this.setText(text);
        this.addActionListener(action);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setMaximumSize(new Dimension(150, 50));

        this.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
        
    }
}
