package controller;

import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Popup {

    private Parent _root;
    private Stage _window;

    private boolean _isResizable;
    private boolean _closeBeforeContinue;

    public Popup() {

    }

    public void init(boolean isResizable, boolean closeBeforeContinue, String filename) {
        try {
            _root = FXMLLoader.load(getClass().getResource("../view/" + filename));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        _window = new Stage();
        _isResizable = isResizable;
        _closeBeforeContinue = closeBeforeContinue;
    }

    public void setScene(Scene scene) {
        _window.setScene(scene);
    }

    public void setTitle(String title) {
        _window.setTitle(title);
    }

    public void show() {
        if (_closeBeforeContinue) _window.initModality(Modality.APPLICATION_MODAL);

        _window.setResizable(_isResizable);
        _window.showAndWait();
    }
}