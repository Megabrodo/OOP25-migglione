package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Player;

public class Mosquito implements Player {
    private final List<Card> hand = new ArrayList<>();
    private int chosenAttr;
    
    /**
     * Constructor for mosquito player, there is the hand of the player and:
     * *there will be more variables for checking the game state*
     * 
     * @param hand
     */
    public Mosquito(final List<Card> hand) {
        this.hand.addAll(hand);
    }

    @Override
    public int playCard(final int Attr, final Card playedCard) {
        final Card attrCard = this.hand.remove(this.hand.indexOf(playedCard));
        chooseAttr(Attr);
        switch (chosenAttr) {
            case 0: return attrCard.getAttk();
            case 1: return attrCard.getDeff();
            case 2: return attrCard.getStrength();
            case 3: return attrCard.getIntelligence();
            case 4: return attrCard.getStealth();
            default: throw new IllegalArgumentException("Illegal attribute chosen.");
        }
        
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public void drawCard(final Card drawnCard) {
        if (hand.size() < 3) {
            this.hand.addLast(drawnCard);
        }
    }

    @Override
    public void chooseAttr(int Attr) {
        this.chosenAttr = Attr;
    }

    @Override
    public int getAttr() {
        return this.chosenAttr;
    }
}
