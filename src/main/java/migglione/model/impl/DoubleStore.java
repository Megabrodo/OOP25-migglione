package migglione.model.impl;

public class DoubleStore<X, Y> {

    private X x;
    private Y y;

    public DoubleStore (final X x, final Y y) {
        this.x = x;
        this.y = y;
    } 

    public X getX() {
        return this.x;
    }

    public Y getY() {
        return this.y;
    }

    public void setX(X newval) {
        this.x = newval;
    }

    public void setY(Y newval) {
        this.y = newval;
    }
}
