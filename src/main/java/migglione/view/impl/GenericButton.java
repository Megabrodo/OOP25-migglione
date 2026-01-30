package migglione.view.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GenericButton extends JButton {
    
    public GenericButton(String text, ActionListener action) {
        this.setText(text);
        this.addActionListener(action);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setMaximumSize(new Dimension(150, 50));

        this.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        
    }
}
