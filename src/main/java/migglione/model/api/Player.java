package migglione.model.api;

import java.util.List;

/**
 * interface where the methods in common with the user and the CPU are described
 */
public interface Player {
    /**
     * Method used for playing a specified card.
     * Needs the Integer representing the card in the Cards.java class,
     * then removes the card from the player's hand.
     */
    public void playCard(int cardNum);

    /**
     * Used for knowing the cards in hand at the beginning for each round 
     * 
     * @return the List of Integer values representing
     *         the cards in Player's hand
     */
    public List<Integer> getHand();
}
