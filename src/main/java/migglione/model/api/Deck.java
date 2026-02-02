package migglione.model.api;

import java.util.List;

public interface Deck {
    void shuffle();

    List<Integer> getCards();
}
