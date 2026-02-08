package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Player;

/**
 * The class in which the methods of the user player are used.
 * The user gets to choose autonomously what card to play and what attribute to use.
 */
public class User implements Player {
    protected final List<Card> hand = new ArrayList<>();
    protected String chosenAttr;
    private final String name;
    protected final PointsPile pile = new PointsPile();
    protected Card lastPlayed;

    /**
     * Constructor for the Mosquito player(or anonymous).
     * 
     * @param startHand the hand of the player at the start of the match
     */
    public User(final List<Card> startHand) {
        hand.addAll(startHand);
        this.name = "Player";
    }

    /**
     * Constructor for the User player with a specified name.
     * 
     * @param startHand the hand of the player at the start of the match
     * @param name the name of the player
     */
    public User(final List<Card> startHand, final String name) {
        hand.addAll(startHand);
        this.name = name;
    }

    @Override
    public int playCard(final String attr, final Card playedCard) {
        lastPlayed = playedCard;
        hand.remove(playedCard);
        return getAttr(attr, playedCard);
    }

    @Override
    public final List<Card> getHand() {
        return hand;
    }

    @Override
    public final void drawCard(final Card drawnCard) {
        if (hand.size() < 3) {
            this.hand.addLast(drawnCard);
        }
    }

    @Override
    public final void chooseAttr(final String attr) {
        this.chosenAttr = attr;
    }

    @Override
    public final String getAttr() {
        return chosenAttr;
    }

    @Override
    public PointsPile getPile(List<Card> pointsWon) {
        for (Card point : pointsWon) {
            pile.addPile(point);
        }
        return pile;
    }

    /**
     * A method to understand what attributes is being searched.
     * 
     * @param attr used to know what attribute to use
     * @param playedCard the card we want to know the value of
     * @return the value of the specified card's attribute
     */
    protected int getAttr(final String attr, final Card playedCard) {
        switch (attr) {
            case "Attk":
                return playedCard.getAttk();
            case "Deff":
                return playedCard.getDeff();
            case "Strength":
                return playedCard.getStrength();
            case "Intelligence":
                return playedCard.getIntelligence();
            case "Stealth":
                return playedCard.getStealth();
            default:
                throw new IllegalArgumentException("Invalid attribute: " + attr);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Card getPlayedCard() {
        return lastPlayed;
    }
}
