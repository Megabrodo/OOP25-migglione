package migglione.model.impl;

public enum CardId {
    ANDER_BLEIDO(1, "Ander Bleido", 6, 5, 7, 4, 7),
    CAPITAN_VESUVIO(2,"Capitan vesuvio",7, 8, 5, 2, 5);

    private final int id;
    private final Card card;

    CardId(final int id, final String name, final int attk, final int deff, final int strength,
        final int intelligence, final int stealth) {
        this.id = id;
        this.card = new Card(name, attk, deff, strength, intelligence, stealth);
    }
}
