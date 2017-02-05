package models;

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
}