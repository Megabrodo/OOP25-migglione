package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

public class PointsPile {

private List<Card> pile;
    
    public PointsPile() {
        this.pile = new ArrayList<>();
    }

    public void discard(Card card) {
        pile.add(card);
    }

    public boolean isPileEmpty() {
        return this.pile.isEmpty();
    }

    public List<Card> getPile() {
        return this.pile;
    }

    public Card scry() {
        if(isPileEmpty()) {
            return null;
        }
        return pile.get(pile.size() -1);
    }
}
