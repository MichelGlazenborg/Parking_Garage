package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TimeInputPopupView {

    private final GridPane _layout;

    private int _labelRowNumber = 0;
    private int _fieldRowNumber = 0;

    private static final int COLUMN_LABELS = 0;
    private static final int COLUMN_FIELDS = 1;

    private Button _submit;

    public TimeInputPopupView(GridPane layout) {
        _layout = layout;
        _layout.setVgap(10);
        _layout.setHgap(10);
    }

    /**
     * Adds labels to the user inputs
     * @param week = label for weeks
     * @param day = label for days
     * @param hour = label for hours
     * @param minute = label for minutes
     */
    public void addLabels(Label week, Label day, Label hour, Label minute) {
        GridPane.setConstraints(week, COLUMN_LABELS, _labelRowNumber++);
        GridPane.setConstraints(day, COLUMN_LABELS, _labelRowNumber++);
        GridPane.setConstraints(hour, COLUMN_LABELS, _labelRowNumber++);
        GridPane.setConstraints(minute, COLUMN_LABELS, _labelRowNumber);

        _layout.getChildren().addAll(week, day, hour, minute);
    }

    /**
     * Adds the textfields for the user inputs
     * @param setWeek = user input for weeks
     * @param setDay = user input for days
     * @param setHour = user input for hours
     * @param setMinute = user input for minutes
     */
    public void addFields(TextField setWeek, TextField setDay, TextField setHour, TextField setMinute) {
        GridPane.setConstraints(setWeek, COLUMN_FIELDS, _fieldRowNumber++);
        GridPane.setConstraints(setDay, COLUMN_FIELDS, _fieldRowNumber++);
        GridPane.setConstraints(setHour, COLUMN_FIELDS, _fieldRowNumber++);
        GridPane.setConstraints(setMinute, COLUMN_FIELDS, _fieldRowNumber++);
        GridPane.setConstraints(_submit = new Button("Submit"), COLUMN_FIELDS, _fieldRowNumber);

        _layout.getChildren().addAll(setWeek, setDay, setHour, setMinute, _submit);
    }

    /**
     * Creates a button for submitting the user input
     * @return = If all inputs were legitimate, submit them to the program
     */
    public Button getSubmitButton() {
        return _submit;
    }
}
