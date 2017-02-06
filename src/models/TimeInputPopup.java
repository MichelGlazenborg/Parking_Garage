package models;

import controller.Controller;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import view.TimeInputPopupView;

public class TimeInputPopup {

    private TimeInputPopupView _view;
    private GridPane _layout;

    private TextField _inputWeek;
    private TextField _inputDay;
    private TextField _inputHour;
    private TextField _inputMinute;

    public TimeInputPopup(TimeInputPopupView view) {
        _view = view;
    }

    public void setFields() {
        _inputWeek = new TextField();
        _inputDay = new TextField();
        _inputHour = new TextField();
        _inputMinute = new TextField();

        _inputWeek.setPromptText("Week");
        _inputDay.setPromptText("Day");
        _inputHour.setPromptText("Hour");
        _inputMinute.setPromptText("Minute");

        _view.addLabels(
            new Label("Week:"),
            new Label("Day:"),
            new Label("Hour:"),
            new Label("Minute:")
        );
        _view.addFields(_inputWeek, _inputDay, _inputHour, _inputMinute);
    }

    public void setLayout(GridPane layout) {
        _layout = layout;
    }

    public int validateInput(String input, int maxInput) {
        int inputInteger = -1;
        boolean failed = false;

        try {
            inputInteger = Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            Controller.showError();
            failed = true;
        }
        finally {
            if (inputInteger < 1 && !failed) Controller.showError();
            else if (inputInteger >= maxInput) Controller.showError();
        }

        return inputInteger;
    }

    public TextField getInputWeek() {
        return _inputWeek;
    }

    public TextField getInputDay() {
        return _inputDay;
    }

    public TextField getInputHour() {
        return _inputHour;
    }

    public TextField getInputMinute() {
        return _inputMinute;
    }
}