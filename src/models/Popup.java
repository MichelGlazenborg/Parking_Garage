package models;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Popup {

    private Stage _window;

    private boolean _isResizable;
    private boolean _closeBeforeContinue;

    public Popup() {

    }

    public void init(boolean isResizable, boolean closeBeforeContinue) {
        _window = new Stage();
        _isResizable = isResizable;
        _closeBeforeContinue = closeBeforeContinue;
    }

    public void show() {
        if (_closeBeforeContinue) _window.initModality(Modality.APPLICATION_MODAL);

        _window.setResizable(_isResizable);
        _window.showAndWait();
    }

    public void sizeToScene() {
        _window.sizeToScene();
    }

    public void setScene(Scene scene) {
        _window.setScene(scene);
    }

    public void setTitle(String title) {
        _window.setTitle(title);
    }

    public int getWidth() {
        return (int) _window.getWidth();
    }

    public int getHeight() {
        return (int) _window.getHeight();
    }
}