package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Player;

public class Mosquito implements Player {
    private final List<Card> hand = new ArrayList<>();

    public Mosquito(List<Card> hand) {
        this.hand.addAll(hand);
    }

    @Override
    public int playCard(int attrChoice, Card playedCard) {
        final Card attrCard = this.hand.remove(this.hand.indexOf(playedCard));
        switch (attrChoice) {
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
    public void drawCard(Card drawnCard) {
        if (hand.size() < 3) {
            this.hand.addLast(drawnCard);
        }
    }
}
