package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Player;

public class Mosquito implements Player {
    private final List<Card> hand = new ArrayList<>();
    private int chosenAttr;
    private boolean myTurn;
    
    /**
     * Constructor for mosquito player, there is the hand of the player and:
     * *there will be more variables for checking the game state*
     * 
     * @param hand
     */
    public Mosquito(final List<Card> hand, final boolean turn) {
        this.hand.addAll(hand);
        myTurn = turn;
    }

    @Override
    public int playCard(final int Attr, final Card playedCard) {
        if (myTurn) {
            return playCardFirst();
        } else {
            return playCardSecond(Attr);
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

    /**
     * If it's mosquito's turn, he'll decide what attribute to play on,
     * an algorhythm to understand what could be best is used.
     * 
     * @param playedCard
     * @return
     */
    private int playCardFirst() {
        //algoritmo per capire che attributo usare
        
        return 0;
    }
    /**
     * If mosquito's second, he'll play a card that has the highest attribute
     * 
     * @param Attr
     * @param playedCard
     * @return
     */
    private int playCardSecond(final int Attr) {
        chooseAttr(Attr);
        return maxStat(chosenAttr);
    }

    private int maxStat(final int Attr) {
        int max = 0;
        for (Card c : hand) {
            switch (Attr) {
                case 1:
                    if (c.getAttk() > max) {
                        max = c.getAttk();
                    }
                    break;
                case 2:
                    if (c.getDeff() > max) {
                        max = c.getDeff();
                    }
                    break;
                case 3:
                    if (c.getStrength() > max) {
                        max = c.getStrength();
                    }
                    break;
                case 4:
                    if (c.getIntelligence() > max) {
                        max = c.getIntelligence();
                    }
                    break;
                case 5:
                    if (c.getStealth() > max) {
                        max = c.getStealth();
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid attribute: " + Attr);
            }
        }
        return max;
    }
}
