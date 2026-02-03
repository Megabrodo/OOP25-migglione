package migglione.model.impl;

public class Card {
private String Name;
    private int Attk;
    private int Deff;
    private int Strength;
    private int Intelligence;
    private int Stealth;
    

    public Card (final String Name, final int Attk, final int Deff,
         final int Strength, final int Intelligence, final int Stealth){
        this.Name = Name;
        this.Attk = Attk;
        this.Deff = Deff;
        this.Strength = Strength;
        this.Intelligence = Intelligence;
        this.Stealth = Stealth;
    }

    public String getName() {
        return this.Name;
    }

    public int getAttk() {
        return this.Attk;
    }

    public int getDeff() {
        return this.Deff;
    }

    public int getStrength(){
        return this.Strength;
    }

    public int getIntelligence(){
        return this.Intelligence;
    }

    public int getStealth() {
        return this.Stealth;
    }

    public String getCard(){
        return "Card= " 
        + "Name: " + this.Name
        + "Attk: " + this.Attk 
        + "Deff: " + this.Deff
        + "Strength: " + this.Strength
        + "Intelligence: " + this.Intelligence
        + "Stealth: " + this.Stealth;
    }  
}
