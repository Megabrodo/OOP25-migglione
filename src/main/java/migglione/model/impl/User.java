package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Player;

/**
 * The class in which the methods of the user player are used.
 * The user gets to choose autonomously what card to play and what attribute to use.
 */
public class User implements Player {
    private final List<Card> hand = new ArrayList<>();
    private int chosenAttr;
    private final String name;

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
    public int playCard(final int attr, final Card playedCard) {
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
    public final void chooseAttr(final int attr) {
        this.chosenAttr = attr;
    }

    @Override
    public final int getAttr() {
        return chosenAttr;
    }

    /**
     * A method to understand what attributes is being searched.
     * 
     * @param attr used to know what attribute to use
     * @param playedCard the card we want to know the value of
     * @return the value of the specified card's attribute
     */
    protected int getAttr(final int attr, final Card playedCard) {
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
     * A method to get the name of the player, used for the scoreboard.
     *
     * @return User's name
     */
    public String getName() {
        return name;
    }
}
