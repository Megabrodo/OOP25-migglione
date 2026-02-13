package migglione.model.impl;

import migglione.model.api.Card;

/**
 * cards used in this game have a name and five atributes to choose during the game.
 */
public class CardImpl implements Card {
    private final String name;
    private final int attk;
    private final int deff;
    private final int strength;
    private final int intelligence;
    private final int stealth;

    /**
     * Standard constructor fot define card name and the five atribute.
     * 
     * @param name name of the card 
     * @param attk the atribute represent offensive power of the card
     * @param deff the atribute represent defence 
     * @param strength the atribute ho represent hand to hand poewr of the card
     * @param intelligence the atribute rapresent intelligence of the card
     * @param stealth the atribute ho rapresent stealth
     */
    public CardImpl(final String name, final int attk, final int deff,
        final int strength, final int intelligence, final int stealth) {
        this.name = name;
        this.attk = attk;
        this.deff = deff;
        this.strength = strength;
        this.intelligence = intelligence;
        this.stealth = stealth;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAttk() {
        return this.attk;
    }

    @Override
    public int getDeff() {
        return this.deff;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public int getIntelligence() {
        return this.intelligence;
    }

    @Override
    public int getStealth() {
        return this.stealth;
    }

    @Override
    public String getCard() {
        return "Card= " 
        + "name: " + this.name
        + "attk: " + this.attk 
        + "deff: " + this.deff
        + "strength: " + this.strength
        + "intelligence: " + this.intelligence
        + "stealth: " + this.stealth;
    }
}
