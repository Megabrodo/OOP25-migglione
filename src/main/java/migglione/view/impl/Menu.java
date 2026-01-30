package migglione.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

    private final SwingViewImpl view;

    public Menu(SwingViewImpl view) {
        this.view = view;
        this.setLayout(new BorderLayout());
        JPanel cPanel = new JPanel();
        JLabel title = new JLabel("Il Migglione");
        JButton gallery = new GenericButton("Gallery", b -> System.exit(0));

        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.Y_AXIS));
        cPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cPanel.setBackground(Color.BLACK);

        title.setFont(new Font("Times New Roman", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        cPanel.add(Box.createVerticalGlue());
        cPanel.add(title);
        cPanel.add(Box.createVerticalGlue());

        cPanel.add(gallery);
        cPanel.add(Box.createVerticalGlue());

        this.add(cPanel, BorderLayout.CENTER);
    }
}
