package migglione.model.impl;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Cards {
    private Map <Integer,Card> cards = new HashMap <> ();

    public Cards(){
        cards.put(1, new Card("Ander Bleido", 6, 5, 7, 4, 7));
        cards.put(2, new Card("Capitan Vesuvio", 7, 8, 5, 2, 5));
        cards.put(3, new Card("Acquaman", 7, 4, 7, 6, 8));
        cards.put(4, new Card("Il Mazzo Di Carte", 10, 10, 10, 10, 10));
        cards.put(5, new Card("Il Dia-Bolo", 9, 3, 5, 6, 3));
        cards.put(6, new Card("Il Grande Barraza Magno", 8, 9, 8, 5, 3));
        cards.put(7, new Card("Il Falchiere", 6, 4, 2, 7, 9));
        cards.put(8, new Card("Waqualuigi", 8, 3, 9, 5, 2));
        cards.put(9, new Card("L", 1, 1, 1, 10, 1));
        cards.put(10, new Card("Calabria", 1, 10, 1, 1, 1));
        cards.put(11, new Card("Ulisse", 9, 9, 9, 7, 8));
        cards.put(12, new Card("Maestro Zanus", 7, 8, 8, 9, 3));
        cards.put(13, new Card("Il Dio Rey", 8, 8, 8, 9, 9));
        cards.put(14, new Card("La Lega Delle Leggende", 7, 8, 9 ,6, 2));
        cards.put(15, new Card("Il Grande Danizambo Magno", 1, 1, 1, 1, 1));
        cards.put(16, new Card("La Lomgo", 1, 1, 9, 8, 10));
        cards.put(17, new Card("Giordano Bruno", 5, 5, 7, 6, 1));
        cards.put(18, new Card("Brigus", 3, 3, 3, 9, 9));
        cards.put(19, new Card("Giotàro Giorgio", 4, 8, 4, 7, 4));
        cards.put(20, new Card("Lo Sgreve", 2, 2, 3, 4, 8));
        cards.put(21, new Card("La Cally", 6, 9, 2, 7, 1));
        cards.put(22, new Card("Il Filosofo", 4, 3, 5, 6, 5));
        cards.put(23, new Card("La Velocità", 1, 1, 1, 1, 10));
        cards.put(24, new Card("Geppetto", 7, 7, 7, 7, 7));
    }

    public Map<Integer,Card> getCards(){
        return Collections.unmodifiableMap(cards);
    }
    
}
