package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * The class in which the methods of the Mosquito player are used.
 * Different from the user class as the user gets to choose autonomously.
 */
public final class Mosquito extends User {
    private final List<Card> hand = new ArrayList<>();
    private boolean myTurn;

    /**
     * Constructor for mosquito player.
     * *there will be more variables for checking the game state*
     * 
     * @param hand the hand of the player
     * @param turn which turn is at the first round
     */
    public Mosquito(final List<Card> hand, final boolean turn) {
        super(hand);
        myTurn = turn;
    }

    @Override
    public final int playCard(final String attr, final Card playedCard) {
        //to implement a way to understand if it's mosquito's turn
        if (myTurn) {
            return playCardFirst(playedCard);
        } else {
            return playCardSecond(attr, playedCard);
        }
    }

    @Override
    public PointsPile getPile(List<Card> pointsWon) {
        for (Card point : pointsWon) {
            pile.addPile(point);
        }
        return pile;
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
            chooseAttr(bestAttr(c));
            if (getAttr(chosenAttr, c) > maxStat) {
                maxStat = getAttr(chosenAttr, c);
                bestCard = c;
            }
            if (maxStat == 10) {
                break;
            }
        }
        hand.remove(bestCard);
        return maxStat;
    }

    /**
     * Method to understand what attribute is best for the card, used in playCardFirst.
     * 
     * @param playedCard the card to analyze
     * @return the name of the best attribute for the card, in case of tie the last with the best is chosen
     */
    private String bestAttr(final Card playedCard) {
        if (playedCard.getAttk() > playedCard.getDeff() && playedCard.getAttk() > playedCard.getStrength() && playedCard.getAttk() > playedCard.getIntelligence() && playedCard.getAttk() > playedCard.getStealth()) {
            return "Attk";
        } else if (playedCard.getDeff() > playedCard.getStrength() && playedCard.getDeff() > playedCard.getIntelligence() && playedCard.getDeff() > playedCard.getStealth()) {
            return "Deff";
        } else if (playedCard.getStrength() > playedCard.getIntelligence() && playedCard.getStrength() > playedCard.getStealth()) {
            return "Strength";
        } else if (playedCard.getIntelligence() > playedCard.getStealth()) {
            return "Intelligence";
        } else {
            return "Stealth";
        }
    }

    /**
     * If mosquito's second, he'll play a card that has the highest attribute.
     * 
     * @param attr the attribute used by the User
     * @param playedCard the card that will be played and removed from hand
     * @return the value of the played card in the specified attribute
     */
    private int playCardSecond(final String attr, final Card playedCard) {
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
    private Card maxStat(final String attr, final Card playedCard) {
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
    public void setMyTurn(final boolean turn) {
        this.myTurn = turn;
    }
}
