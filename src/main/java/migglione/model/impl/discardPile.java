package migglione.model.impl;

import migglione.model.impl.Cards;
import java.util.ArrayList;
import java.util.List;
import migglione.model.api.CardDraw;
import migglione.model.api.Deck;

public class discardPile {

//List<Card> pile = new ArrayList<>();
private List<Card> discardPile;
    
    public discardPile(){
        //this.pile = new ArrayList<>();
        this.discardPile = new ArrayList<>();
    }

    public void discard (Card card){
        discardPile.add(card);
    }

    public boolean isDiscardPileEmpty(){
        return this.discardPile.isEmpty();
    }

    public Card scry(){
        if(isDiscardPileEmpty()){
            return null;
        }
        return discardPile.get(discardPile.size() -1);
    }

    /*
    public void replace(ArrayList<Cards> cards){
        this.discardPile = discardPile.toArray(new Cards cards.size());
        this.
    }
    */
}
