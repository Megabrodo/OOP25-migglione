package migglione.controller.impl;

import migglione.controller.api.Controller;
import migglione.view.api.SwingView;
import migglione.view.impl.SwingViewImpl;

public class ControllerImpl implements Controller {

    private final SwingView view;

    public ControllerImpl() {
        this.view = new SwingViewImpl();
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void nextRound() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextRound'");
    }

    @Override
    public void endMatch() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endMatch'");
    }
    
}
