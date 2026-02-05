package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Player;

public class User implements Player {
    private final List<Card> hand = new ArrayList<>();
    private int chosenAttr;

    public User(List<Card> startHand) {
        hand.addAll(startHand);
    }

    @Override
    public int playCard(int attr, final Card playedCard) {
        hand.remove(playedCard);
        return getAttr(attr, playedCard);
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public void drawCard(Card drawnCard) {
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
        return chosenAttr;
    }
    
    private int getAttr(final int Attr, final Card playedCard) {
        switch (Attr) {
            case 1:
                return playedCard.getAttk();
            case 2:
                return playedCard.getDeff();
            case 3:
                return playedCard.getStrength();
            case 4:
                return playedCard.getIntelligence();
            case 5:
                return playedCard.getStealth();
            default:
                throw new IllegalArgumentException("Invalid attribute: " + Attr);
        }
    }
}
