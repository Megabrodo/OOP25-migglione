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
    private static final String CARDS_IMAGE_PATH = "/images/Cards/";
    private static final String STATS_IMAGE_PATH = "/images/Statistics/";

    /**
     * Constructor for the Hovering class.
     * It takes a card and a panel as parameters and creates a hovering card.
     * The card has the image and stats specified by itself.
     * 
     * @param hoveringCard the card to be hovered
     * @param gamePanel the panel on which the card will be hovered
     */
    public Hovering(final Card hoveringCard, final JPanel gamePanel) {
        this.hoveringCard = new HoveringCard(hoveringCard);
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
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
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        final String imagePath = hoveringCard.getImage();
        final String statsPath = hoveringCard.getStats();
    
        final java.net.URL cardUrl = getClass().getResource(imagePath);
        final java.net.URL statsUrl = getClass().getResource(statsPath);
    
        // Skip if resources not found
        if (cardUrl == null || statsUrl == null) {
            System.err.println("Resource not found - Card: " + hoveringCard.getName() + 
                            ", Card path: " + imagePath + ", Stats path: " + statsPath);
            return;
        }
    
        final Image cardImg = new ImageIcon(cardUrl).getImage();
        final Image statsImg = new ImageIcon(statsUrl).getImage();
        gamePanel.getGraphics().drawImage(cardImg, gamePanel.getWidth() / 3, gamePanel.getHeight() / 2, Integer.min(gamePanel.getWidth() / 4, gamePanel.getHeight() / 2), Integer.min(gamePanel.getHeight() / 2, gamePanel.getWidth() / 4), gamePanel);
        gamePanel.getGraphics().drawImage(statsImg, gamePanel.getWidth() / 3 + Integer.min(gamePanel.getWidth() / 4, gamePanel.getHeight() / 2), gamePanel.getHeight() / 2, Integer.min(gamePanel.getWidth() / 4, gamePanel.getHeight() / 2), Integer.min(gamePanel.getHeight() / 2, gamePanel.getWidth() / 4), gamePanel);
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        gamePanel.repaint();
    }

    /**
     * Nested class used to create a card with the same stats as the one in the hand but with an image path.
     * Like a card but with an image too, used to create the hovering effect on the buttons.
     */
static class HoveringCard extends Card {
        private Card actualCard;

        HoveringCard(final Card actualCard) {
            super(actualCard.getName(), actualCard.getAttk(), actualCard.getDeff(), actualCard.getStrength(),
                  actualCard.getIntelligence(), actualCard.getStealth());
            this.actualCard = actualCard;
        }
        
        public String getImage() {
            return CARDS_IMAGE_PATH + actualCard.getName() + ".png";
        }

        public String getStats() {
            return STATS_IMAGE_PATH + actualCard.getName() + ".png";
        }

        public void setHoveredCard(final Card newCard) {
            actualCard = newCard;
        }
    }
}
