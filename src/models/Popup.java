package models;

import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Constructor for the abstract class popup
 */
public abstract class Popup {

    private Stage _window;

    private boolean _isResizable;           //Defines whether the window is resizable or not
    private boolean _closeBeforeContinue;   //Defines whether the popup has to be closed before you can continue

    /**
     * Sets the popup properties
     * @param isResizable           Defines whether the window is resizable or not
     * @param closeBeforeContinue   Defines whether the popup has to be closed before you can continue
     */
    public void init(boolean isResizable, boolean closeBeforeContinue) {
        _window = new Stage();
        _isResizable = isResizable;
        _closeBeforeContinue = closeBeforeContinue;
    }

    /**
     * Shows the popup window
     */
    public void show() {
        if (_closeBeforeContinue)
            _window.initModality(Modality.APPLICATION_MODAL);

        _window.setResizable(_isResizable);
        _window.showAndWait();
    }

    /**
     * Sets the scene for the window
     * @param scene The scene for the window
     */
    public void setScene(Scene scene) {
        _window.setScene(scene);
    }

    /**
     * Sets the title of the window
     * @param title The title of the window
     */
    public void setTitle(String title) {
        _window.setTitle(title);
    }

    /**
     * Closes the window
     */
    public void close() {
        _window.close();
    }

    /**
     * Gets the window
     * @return The window
     */
    protected Stage getWindow() {
        return _window;
    }
}