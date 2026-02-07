package migglione.model.impl;

public enum CardId {
    ANDER_BLEIDO(1, "Ander Bleido", 6, 5, 7, 4, 7),
    CAPITAN_VESUVIO(2, "Capitan vesuvio", 7, 8, 5, 2, 5),
    ACQUAMAN(3, "Acquaman", 7, 4, 7, 6, 8),
    IL_MAZZO_DI_CARTE(4, "Il Mazzo Di Carte", 10, 10, 10, 10, 10),
    IL_DIA_BOLO(5, "Il Dia-Bolo", 9, 3, 5, 6, 3),
    IL_GRANDE_BARAZZO_MAGNO(6, "Il Grande Barraza Magno", 8, 9, 8, 5, 3),
    IL_FALCHIERE(7, "Il Falchiere", 6, 4, 2, 7, 9),
    WAQUALUIGI(8, "Waqualuigi", 8, 3, 9, 5, 2),
    L(9, "L", 2, 2, 2, 10, 2),
    CALABRIA(10, "Calabria", 1, 1, 1, 1, 1),
    ULISSE(11, "Ulisse", 9, 9, 9, 7, 8),
    MAESTRO_ZANUS(12, "Maestro Zanus", 7, 8, 8, 9, 3),
    IL_DIO_REY(13, "Il Dio Rey", 8, 8, 8, 9, 9),
    LA_LEGA_DELLE_LEGGENDE(14, "La Lega Delle Leggende", 7, 8, 9, 6, 2),
    IL_GRANDE_DANIZAMBO_MAGNO(15, "Il Grande Danizambo Magno", 2, 2, 2, 2, 2),
    LA_LOMGO(16, "La Lomgo", 2, 2, 9, 8, 10),
    GIORDANO_BRUNO(17, "Giordano Bruno", 5, 5, 7, 6, 2),
    BRIGUS(18, "Brigus", 3, 3, 3, 9, 9),
    GIOTARO_GIORGIO(19, "Giotaro Giorgio", 4, 8, 4, 7, 4),
    LO_SGREVE(20, "Lo Sgreve", 2, 2, 3, 4, 8),
    LA_CALLY(21, "La Cally", 6, 9, 2, 7, 2),
    IL_FIOSOFO(22, "Il Filosofo", 4, 3, 5, 6, 5),
    LA_VELOCITA(23, "La Velocità", 2, 2, 2, 1, 10),
    GEPPETTO(24, "Geppetto", 7, 7, 7, 7, 7);

    private final int id;
    private final Card card;

    CardId(final int id, final String name, final int attk, final int deff, final int strength,
        final int intelligence, final int stealth) {
        this.id = id;
        this.card = new Card(name, attk, deff, strength, intelligence, stealth);
    }

    public Card getCardId() {
        return card;
    }

    public int getId() {
        return id;
    }
}
