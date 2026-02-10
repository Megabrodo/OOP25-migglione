package migglione.view.impl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import migglione.model.impl.Card;

/**
 * This class is used to implement the hovering effect on the buttons.
 * It's an implementation of the MouseMotionListener interface but 
 * really only uses the mouseMoved() method, the other is empty 
 * because it has no real fucntion in the game.
 */
public final class Hovering implements MouseListener {
    private final HoveringCard hoveringCard;
    private final JPanel gamePanel;
    private static final String CARDS_IMAGE_PATH = "/images/cards/";

    public Hovering(final Card hoveringCard, final JPanel gamePanel) {
        this.hoveringCard = new HoveringCard(hoveringCard, CARDS_IMAGE_PATH + hoveringCard.getName() + ".png");
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //io farei qui la roba thomas, secondo me ti aiuta nel field e fai tutto in uno
        /*
        plan: 
        detect the card clicked (auto)
        if it's the player's turn to choose attribute, redirect to attribute choice 
        (should have back button)
        else find a way to obtain it
        send to Game the user's choices.
        */
       //add attr selection screen

       /*
       game.userChosen(attr, card);
        */
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //da implementare
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        final Image cardImg = new ImageIcon(getClass().getResource(hoveringCard.getImage())).getImage();
        gamePanel.getGraphics().drawImage(cardImg, e.getX(), e.getY(), 100, 150, gamePanel);
        //o faccio immagini per le stat(banale word) oppure cerco di fare un panel in cui si scrive
    }

    @Override
    public void mouseExited(MouseEvent e) {
        gamePanel.repaint();
    }

    /**
     * Nested class used to create a card with the same stats as the one in the hand but with an image path.
     * Like a card but with an image too, used to create the hovering effect on the buttons.
     */
    static class HoveringCard extends Card {
        private String path;

        public HoveringCard(final Card actualCard, final String path) {
            super(actualCard.getName(), actualCard.getAttk(), actualCard.getDeff(), actualCard.getStrength(), actualCard.getIntelligence(), actualCard.getStealth());
            this.path = path;
        }
        
        public String getImage() {
            return path;
        }
    }
}
