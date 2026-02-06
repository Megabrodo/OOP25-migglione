package migglione.model.impl;

public class Card {
    private final String name;
    private final int attk;
    private final int deff;
    private final int strength;
    private final int intelligence;
    private final int stealth;

    public Card(final String name, final int attk, final int deff,
        final int strength, final int intelligence, final int stealth) {
        this.name = name;
        this.attk = attk;
        this.deff = deff;
        this.strength = strength;
        this.intelligence = intelligence;
        this.stealth = stealth;
    }

    public String getName() {
        return this.name;
    }

    public int getAttk() {
        return this.attk;
    }

    public int getDeff() {
        return this.deff;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public int getStealth() {
        return this.stealth;
    }

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
