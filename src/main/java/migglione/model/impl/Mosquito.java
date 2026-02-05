package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Player;

/**
 * The class in which the methods of the Mosquito player are used.
 * Different from the user class as the user gets to choose autonomously.
 */
public final class Mosquito implements Player {
    private final List<Card> hand = new ArrayList<>();
    private int chosenAttr;
    private boolean myTurn;

    /**
     * Constructor for mosquito player.
     * *there will be more variables for checking the game state*
     * 
     * @param hand the hand of the player
     * @param turn which turn is at the first round
     */
    public Mosquito(final List<Card> hand, final boolean turn) {
        this.hand.addAll(hand);
        myTurn = turn;
    }

    @Override
    public int playCard(final int attr, final Card playedCard) {
        //to implement a way to understand if it's mosquito's turn
        setMyTurn(!myTurn);
        if (myTurn) {
            return playCardFirst(playedCard);
        } else {
            return playCardSecond(attr, playedCard);
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
    public void chooseAttr(final int attr) {
        this.chosenAttr = attr;
    }

    @Override
    public int getAttr() {
        return this.chosenAttr;
    }

    /**
     * A method to understand what attributes is being searched.
     * 
     * @param attr used to know what attribute to use
     * @param playedCard the card we want to know the value of
     * @return the value of the specified card's attribute
     */
    private int getAttr(final int attr, final Card playedCard) {
        switch (attr) {
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
                throw new IllegalArgumentException("Invalid attribute: " + attr);
        }
    }

    /**
     * If it's mosquito's turn, he'll decide what attribute to play on.
     * An algorhythm to understand what could be best is used.
     * 
     * @param playedCard the card that is going to be played and removed from the hand
     * @return the value of the played card in the best attribute, 10 is the max value for a card
     */
    private int playCardFirst(final Card playedCard) {
        int maxStat = 0;
        Card bestCard = playedCard;
        for (final Card c : hand) {
            for (int i = 1; i < 6; i++) {
                if (getAttr(i, c) > maxStat) {
                    maxStat = getAttr(i, c);
                    bestCard = c;
                    chooseAttr(i);
                }
                if (maxStat == 10) {
                    i = 5;
                }
            }
        }
        hand.remove(bestCard);
        return maxStat;
    }

    /**
     * If mosquito's second, he'll play a card that has the highest attribute.
     * 
     * @param attr the attribute used by the User
     * @param playedCard the card that will be played and removed from hand
     * @return the value of the played card in the specified attribute
     */
    private int playCardSecond(final int attr, final Card playedCard) {
        chooseAttr(attr);
        final Card bestCard = maxStat(attr, playedCard);
        hand.remove(bestCard);
        return getAttr(attr, bestCard);
    }

    /**
     * To understand what card is best to be played, this is the algorhythm.
     * Might be optimized with "if i can't win, might use the worst"
     * 
     * @param attr the attribute used by the User
     * @param playedCard the card that will be played and removed from hand
     * @return the value of the played card in the specified attribute
     */
    private Card maxStat(final int attr, final Card playedCard) {
        Card bestCard = playedCard;
        for (final Card c : hand) {
            if (getAttr(attr, c) > getAttr(attr, bestCard)) {
                bestCard = c;
            }
        }
        return bestCard;
    }

    /**
     * Not good implementation, just for test purposes.
     * for now
     * 
     * @param turn whose turn it is, true for mosquito, false for user
     */
    private void setMyTurn(final boolean turn) {
        this.myTurn = turn;
    }
}
