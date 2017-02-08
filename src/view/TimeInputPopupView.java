package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TimeInputPopupView {

    private GridPane _layout;

    private int _labelRowNumber = 0;
    private int _fieldRowNumber = 0;

    private static final int COLUMN_LABELS = 0;
    private static final int COLUMN_FIELDS = 1;

    private Button _submit;


    /**
     * The constructor for the popup for setting the time
     * @param layout The layout of the popup
     */
    public TimeInputPopupView(GridPane layout) {
        _layout = layout;
        _layout.setVgap(10);
        _layout.setHgap(10);
    }

    /**
     * Adds labels to the popup
     * @param week   The week
     * @param day    The day
     * @param hour   The hour
     * @param minute The minute
     */
    public void addLabels(Label week, Label day, Label hour, Label minute) {
        GridPane.setConstraints(week, COLUMN_LABELS, _labelRowNumber++);
        GridPane.setConstraints(day, COLUMN_LABELS, _labelRowNumber++);
        GridPane.setConstraints(hour, COLUMN_LABELS, _labelRowNumber++);
        GridPane.setConstraints(minute, COLUMN_LABELS, _labelRowNumber);

        _layout.getChildren().addAll(week, day, hour, minute);
    }

    /**
     * Adds fields to the popup
     * @param setWeek    The week field
     * @param setDay     The day field
     * @param setHour    The hour field
     * @param setMinute  The minute field
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
     * The submit button
     * @return The button
     */
    public Button getSubmitButton() {
        return _submit;
    }
}
