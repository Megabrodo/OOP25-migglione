package migglione.model.impl;

/**
 * Class to store and edit multiple variables.
 */
public final class DoubleStore<X, Y> {

    private X x;
    private Y y;

    /**
     * Creates a DoubleStore containing the given variables.
     * 
     * @param x the first variable. Will always be referred as X from now on.
     * @param y the second variable. Will always be referred as Y from now on.
     */
    public DoubleStore(final X x, final Y y) {
        this.x = x;
        this.y = y;
    } 

    /**
     * Method to get the first value of this instance.
     * 
     * @return the first var's value.
     */
    public X getX() {
        return this.x;
    }

    /**
     * Method to get the second value of this instance.
     * 
     * @return the second var's value.
     */
    public Y getY() {
        return this.y;
    }

    /**
     * Method to set the first value of this instance.
     */
    public void setX(final X newval) {
        this.x = newval;
    }

    /**
     * Method to set the second value of this instance. 
     */
    public void setY(final Y newval) {
        this.y = newval;
    }
}
