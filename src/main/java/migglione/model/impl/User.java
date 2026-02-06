package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Player;

public class User implements Player {
    private final List<Card> hand = new ArrayList<>();
    private int chosenAttr;
    private final String name;

    public User(List<Card> startHand) {
        hand.addAll(startHand);
        this.name = "Player";
    }

    public User(List<Card> startHand, String name) {
        hand.addAll(startHand);
        this.name = name;
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

    public String getName() {
        return name;
    }
    
    /**
     * A method to understand what attributes is being searched.
     * 
     * @param attr used to know what attribute to use
     * @param playedCard the card we want to know the value of
     * @return the value of the specified card's attribute
     */
    protected int getAttr(final int Attr, final Card playedCard) {
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
