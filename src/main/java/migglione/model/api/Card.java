package migglione.model.api;

public interface Card {
    /**
     * Standard getter.
     * 
     * @return name of the card
     */
    String getName();

    /**
     * Standard getter.
     * 
     * @return attak atribute of the card
     */
    int getAttk();

    /**
     * Standard getter.
     * 
     * @return atribute defence of the card
     */
    int getDeff();

    /**
     * Standard getter.
     * 
     * @return atribute strength of the card
     */
    int getStrength();

    /**
     * Standard getter.
     * 
     * @return atribute intelligence of the card
     */
    int getIntelligence();

    /**
     * Standard getter.
     * 
     * @return atribute sthealth of the card
     */
    int getStealth();

    /**
     * Standard getter toString.
     * 
     * @return the card information, name and all atributes of the card
     */
    String getCard();
}
