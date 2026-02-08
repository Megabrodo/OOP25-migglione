package migglione.view.impl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import migglione.model.impl.Card;

/**
 * This class is used to implement the hovering effect on the buttons.
 * It's an implementation of the MouseMotionListener interface but 
 * really only uses the mouseMoved() method, the other is empty 
 * because it has no real fucntion in the game.
 */
public final class Hovering implements MouseMotionListener {
    private final HoveringCard hoveringCard;
    private static final String CARDS_IMAGE_PATH = "/images/cards/";

    public Hovering(final Card hoveringCard) {
        this.hoveringCard = new HoveringCard(hoveringCard, CARDS_IMAGE_PATH + hoveringCard.getName() + ".png");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //empty for now, might find a function for it
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //here is the real hovering
        
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
