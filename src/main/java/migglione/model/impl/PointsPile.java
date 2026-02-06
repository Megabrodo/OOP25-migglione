package migglione.model.impl;

import migglione.model.impl.Cards;
import java.util.ArrayList;
import java.util.List;
import migglione.model.api.CardDraw;
import migglione.model.api.Deck;

public class PointsPile {

//List<Card> pile = new ArrayList<>();
private List<Card> pile;
    
    public PointsPile(){
        //this.pile = new ArrayList<>();
        this.pile = new ArrayList<>();
    }

    public void discard (Card card){
        pile.add(card);
    }

    public boolean ispileEmpty(){
        return this.pile.isEmpty();
    }

    public Card scry(){
        if(ispileEmpty()){
            return null;
        }
        return pile.get(pile.size() -1);
    }

    /*
    public void replace(ArrayList<Cards> cards){
        this.pile = pile.toArray(new Cards cards.size());
        this.
    }
    */
}
