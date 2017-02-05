package models;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class TimeInputPopup extends Popup {

    private VBox _layout;
    private Scene _scene;

    public TimeInputPopup() {
        init(false, true);

        _layout = new VBox(10);
        _scene = new Scene(_layout, getHeight(), getWidth());

        setScene(_scene);
        setTitle("Please enter the date and time");
    }

    public void show() {
        setFields();
        super.show();
    }

    private void setFields() {
        _layout.getChildren().addAll();
    }
}