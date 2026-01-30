package migglione.view.api;

import javax.swing.JPanel;

public interface SwingView {
    void setScene(String sceneName);

    JPanel getScene();

    void refresh();
}
