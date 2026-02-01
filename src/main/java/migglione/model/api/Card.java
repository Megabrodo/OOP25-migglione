package migglione.model.api;

public class Card {
    private String Name;
    private int Attk;
    private int Deff;
    private int Corpo_a_Corpo;
    private int Intelligenza;
    private int Furtivita;
    

    public Card (final String Name, final int Attk, final int Deff,
         final int Corpo_a_Corpo, final int Intelligenza, final int Furtivita){
        this.Name = Name;
        this.Attk = Attk;
        this.Deff = Deff;
        this.Corpo_a_Corpo = Corpo_a_Corpo;
        this.Intelligenza = Intelligenza;
        this.Furtivita = Furtivita;
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

    public int getAbiity(){
        return this.Corpo_a_Corpo;
    }

    public int getIntelligenza(){
        return this.Intelligenza;
    }

    public int getFurtivita() {
        return this.Furtivita;
    }

    public String getCard(){
        return "Card= " 
        + "Name: " + this.Name
        + "Attk: " + this.Attk 
        + "Deff: " + this.Deff
        + "Corpo a Corpo: " + this.Corpo_a_Corpo
        + "Intelligenza: " + this.Intelligenza
        + "Furtivita: " + this.Furtivita;
    }  
}
