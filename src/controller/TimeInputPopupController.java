package controller;

import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import models.Popup;
import models.TimeInputPopup;
import view.TimeInputPopupView;

public class TimeInputPopupController extends Popup {

    private GridPane _layout;
    private Scene _scene;
    private TimeInputPopup _model;
    private TimeInputPopupView _view;

    private static final int SCENE_WIDTH = 400;
    private static final int SCENE_HEIGHT = 300;

    public TimeInputPopupController() {
        init(true, true);

        setupLayout(); _layout = new GridPane();
        _view = new TimeInputPopupView(_layout);

        _model = new TimeInputPopup(_view);
        _model.setLayout(_layout);

        setup();

        _scene = new Scene(_layout, SCENE_WIDTH, SCENE_HEIGHT);

        setTitle("Please enter the date and time");
        setScene(_scene);
    }

    public void show() {
        super.show();
    }

    private void setupLayout() {
        _layout = new GridPane();
        ColumnConstraints colLabel = new ColumnConstraints();
        ColumnConstraints colFields = new ColumnConstraints();

        colLabel.setPercentWidth(50);
        colFields.setPercentWidth(50);
        _layout.getColumnConstraints().addAll(colLabel, colFields);
    }

    private void setup() {
        _model.setFields();
    }
}