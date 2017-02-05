package controller;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import models.Popup;
import models.TimeInputPopup;
import view.TimeInputPopupView;

public class TimeInputPopupController extends Popup {

    private GridPane _layout;
    private Scene _scene;
    private TimeInputPopup _model;
    private TimeInputPopupView _view;

    public TimeInputPopupController() {
        init(true, true);

        _layout = new GridPane();
        _view = new TimeInputPopupView(_layout);

        _model = new TimeInputPopup(_view);
        _model.setLayout(_layout);

        setTitle("Please enter the date and time");
    }

    public void show() {
        _model.setFields();
        _scene = new Scene(_layout);
        setScene(_scene);
        sizeToScene();
        super.show();
    }
}