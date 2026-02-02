package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Player;

public class Mosquito implements Player {
    private final List<Integer> hand = new ArrayList<>();

    public Mosquito(List<Integer> hand) {
        this.hand.addAll(hand);
    }

    @Override
    public void playCard(int cardNum) {
        this.hand.remove(this.hand.indexOf(cardNum));
    }

    @Override
    public List<Integer> getHand() {
        return hand;
    }
    
}
