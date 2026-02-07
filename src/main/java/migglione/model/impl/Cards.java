package migglione.model.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cards {
    private final Map<Integer, Card> card;

    public Cards() {
        card = CardId.buildCardsMap();
    }

    public Map<Integer, Card> getCards() {
        return Collections.unmodifiableMap(card);
    }

    /**
     * Getter for the path of the sprites of the cards.
     * 
     * @return the path of the image (see Gallery for use)
     */
    public List<String> getCardsPaths() {
        return card.keySet().stream().map(n -> "/images/cards/" + n + ".png").collect(Collectors.toList());
    }
}
