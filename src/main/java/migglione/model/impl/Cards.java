package migglione.model.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cards {
    private final Map<String, Card> card = new HashMap<>();

    public Cards() {
        card.put("Ander Bleido",
        new Card("Ander Bleido", 6, 5, 7, 4, 7));
        card.put("Capitan Vesuvio",
        new Card("Capitan Vesuvio", 7, 8, 5, 2, 5));
        card.put("Acquaman",
        new Card("Acquaman", 7, 4, 7, 6, 8));
        card.put("Il Mazzo Di Carte",
        new Card("Il Mazzo Di Carte", 10, 10, 10, 10, 10));
        card.put("Il Dia-Bolo",
        new Card("Il Dia-Bolo", 9, 3, 5, 6, 3));
        card.put("Il Grande Barraza Magno",
        new Card("Il Grande Barraza Magno", 8, 9, 8, 5, 3));
        card.put("Il Falchiere",
        new Card("Il Falchiere", 6, 4, 2, 7, 9));
        card.put("Waqualuigi",
        new Card("Waqualuigi", 8, 3, 9, 5, 2));
        card.put("L",
        new Card("L", 2, 2, 2, 10, 2));
        card.put("Calabria",
        new Card("Calabria", 1, 1, 1, 1, 1));
        card.put("Ulisse",
        new Card("Ulisse", 9, 9, 9, 7, 8));
        card.put("Maestro Zanus",
        new Card("Maestro Zanus", 7, 8, 8, 9, 3));
        card.put("Il Dio Rey",
        new Card("Il Dio Rey", 8, 8, 8, 9, 9));
        card.put("La Lega Delle Leggende",
        new Card("La Lega Delle Leggende", 7, 8, 9, 6, 2));
        card.put("Il Grande Danizambo Magno",
        new Card("Il Grande Danizambo Magno", 2, 2, 2, 2, 2));
        card.put("La Lomgo",
        new Card("La Lomgo", 2, 2, 9, 8, 10));
        card.put("Giordano Bruno",
        new Card("Giordano Bruno", 5, 5, 7, 6, 2));
        card.put("Brigus",
        new Card("Brigus", 3, 3, 3, 9, 9));
        card.put("Giotaro Giorgio",
        new Card("Giotaro Giorgio", 4, 8, 4, 7, 4));
        card.put("Lo Sgreve",
        new Card("Lo Sgreve", 2, 2, 3, 4, 8));
        card.put("La Cally",
        new Card("La Cally", 6, 9, 2, 7, 2));
        card.put("Il Filosofo",
        new Card("Il Filosofo", 4, 3, 5, 6, 5));
        card.put("La Velocità",
        new Card("La Velocità", 2, 2, 2, 1, 10));
        card.put("Geppetto",
        new Card("Geppetto", 7, 7, 7, 7, 7));
    }

    public Map<String, Card> getCards() {
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
