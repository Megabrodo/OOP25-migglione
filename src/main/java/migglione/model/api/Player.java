package migglione.model.api;

import java.util.List;

import migglione.model.impl.Card;

/**
 * interface where the methods in common with the user and the CPU are described
 */
public interface Player {
    /**
     * Method used for playing a specified card.
     * Needs the Integer representing the card in the Cards.java class,
     * then removes the card from the player's hand.
     * 
     * @param attr
     * @param playedCard
     * @return
     */
    int playCard(final int attr, final Card playedCard);

    /**
     * Used for knowing the cards in hand at the beginning for each round 
     * 
     * @return the List of Integer values representing
     *         the cards in Player's hand
     */
    List<Card> getHand();

    /**
     * Method to draw a card at the start of each round,
     * only if player has < 3 cards in hand or the deck is not empty
     * 
     * @param drawnCard
     */
    void drawCard(final Card drawnCard);

    /**
     * The chosen attribute is stored internally to the player, 
     * so that playing a card and choosing the attributes are 2 separate methods
     * 
     * @param Attr
     */
    void chooseAttr(final int Attr);

    /**
     * Method to understand what attribute a player is using in a round
     * 
     * @param Attr
     * @return current selected attribute
     */
    int getAttr();
}
