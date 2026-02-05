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
            return playCardFirst(playedCard);
        } else {
            return playCardSecond(Attr, playedCard);
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
    private int playCardFirst(Card playedCard) {
        int maxStat = 0; 
        for (Card c : hand) {
            for (int i = 0; i < 5; i++) {
                if (getAttr(i, c) > maxStat) {
                    maxStat = getAttr(i, c);
                    playedCard = c;
                    chooseAttr(i);
                }
                if (maxStat == 10) {
                    i = 4;
                }
            }
        }
        hand.remove(playedCard);
        return maxStat;
    }

    /**
     * If mosquito's second, he'll play a card that has the highest attribute
     * 
     * @param Attr
     * @param playedCard
     * @return
     */
    private int playCardSecond(final int Attr, Card playedCard) {
        chooseAttr(Attr);
        playedCard = maxStat(Attr, playedCard);
        hand.remove(playedCard);
        return getAttr(Attr, playedCard);
    }

    /**
     * To understand what card is best to be played, this is the algorhythm.
     * Might be optimized with "if i can't win, might use the worst"
     * 
     * @param Attr
     * @param BestCard
     * @return
     */
    private Card maxStat(final int Attr, Card BestCard) {
        for (Card c : hand) {
            if (getAttr(Attr, c) > getAttr(Attr, BestCard)) {
                BestCard = c;
            }
        }
        return BestCard;
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
