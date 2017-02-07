package controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import models.Popup;
import models.TimeInputPopup;
import view.TimeInputPopupView;

public class TimeInputPopupController extends Popup {

    private final GridPane _layout;
    private final TimeInputPopup _model;
    private final TimeInputPopupView _view;

    private static final int SCENE_WIDTH = 400;
    private static final int SCENE_HEIGHT = 200;

    private final int[] _input;

    public TimeInputPopupController() {
        _input = new int[4];
        init(true, true);

        _layout = new GridPane();
        setupLayout();
        _view = new TimeInputPopupView(_layout);

        _model = new TimeInputPopup(_view);

        setup();

        setTitle("Please enter the date and time");
        setScene(new Scene(_layout, SCENE_WIDTH, SCENE_HEIGHT));

        enableSubmitButton();
    }

    public int[] getInput() {
        return _input;
    }

    private void setupLayout() {
        ColumnConstraints colLabel = new ColumnConstraints();
        ColumnConstraints colFields = new ColumnConstraints();

        colLabel.setPercentWidth(50);
        colFields.setPercentWidth(50);
        _layout.getColumnConstraints().addAll(colLabel, colFields);
        _layout.setPadding(new Insets(20, 20, 20, 20));
    }

    private void setup() {
        _model.setFields();
    }

    private void handleInput() {
        _input[0] = _model.validateInput(_model.getInputWeek().getText(), 52);
        _input[1] = _model.validateInput(_model.getInputDay().getText(), 7);
        _input[2] = _model.validateInput(_model.getInputHour().getText(), 24);
        _input[3] = _model.validateInput(_model.getInputMinute().getText(), 60);
        super.close();
    }

    private void enableSubmitButton() {
        Button _submit = _view.getSubmitButton();
        _submit.setOnAction(e -> handleInput());

        _model.getInputWeek().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) handleInput();
        });

        _model.getInputDay().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) handleInput();
        });

        _model.getInputHour().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) handleInput();
        });

        _model.getInputMinute().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) handleInput();
        });
    }
}