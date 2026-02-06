package migglione.model.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Cards {
    private Map <String, Card> cards = new HashMap<>();

    public Cards() {
        cards.put("Ander Bleido",
        new Card("Ander Bleido", 6, 5, 7, 4, 7));
        cards.put("Capitan Vesuvio",
        new Card("Capitan Vesuvio", 7, 8, 5, 2, 5));
        cards.put("Acquaman",
        new Card("Acquaman", 7, 4, 7, 6, 8));
        cards.put("Il Mazzo Di Carte",
        new Card("Il Mazzo Di Carte", 10, 10, 10, 10, 10));
        cards.put("Il Dia-Bolo",
        new Card("Il Dia-Bolo", 9, 3, 5, 6, 3));
        cards.put("Il Grande Barraza Magno",
        new Card("Il Grande Barraza Magno", 8, 9, 8, 5, 3));
        cards.put("Il Falchiere",
        new Card("Il Falchiere", 6, 4, 2, 7, 9));
        cards.put("Waqualuigi",
        new Card("Waqualuigi", 8, 3, 9, 5, 2));
        cards.put("L",
        new Card("L", 1, 1, 1, 10, 1));
        cards.put("Calabria",
        new Card("Calabria", 1, 1, 1, 1, 1));
        cards.put("Ulisse",
        new Card("Ulisse", 9, 9, 9, 7, 8));
        cards.put("Maestro Zanus",
        new Card("Maestro Zanus", 7, 8, 8, 9, 3));
        cards.put("Il Dio Rey",
        new Card("Il Dio Rey", 8, 8, 8, 9, 9));
        cards.put("La Lega Delle Leggende",
        new Card("La Lega Delle Leggende", 7, 8, 9 ,6, 2));
        cards.put("Il Grande Danizambo Magno",
        new Card("Il Grande Danizambo Magno", 1, 1, 1, 1, 1));
        cards.put("La Lomgo",
        new Card("La Lomgo", 1, 1, 9, 8, 10));
        cards.put("Giordano Bruno",
        new Card("Giordano Bruno", 5, 5, 7, 6, 1));
        cards.put("Brigus",
        new Card("Brigus", 3, 3, 3, 9, 9));
        cards.put("Giotàro Giorgio",
        new Card("Giotàro Giorgio", 4, 8, 4, 7, 4));
        cards.put("Lo Sgreve",
        new Card("Lo Sgreve", 2, 2, 3, 4, 8));
        cards.put("La Cally",
        new Card("La Cally", 6, 9, 2, 7, 1));
        cards.put("Il Filosofo",
        new Card("Il Filosofo", 4, 3, 5, 6, 5));
        cards.put("La Velocità",
        new Card("La Velocità", 1, 1, 1, 1, 10));
        cards.put("Geppetto",
        new Card("Geppetto", 7, 7, 7, 7, 7));
    }

    public Map<String, Card> getCards() {
        return Collections.unmodifiableMap(cards);
    }
}
