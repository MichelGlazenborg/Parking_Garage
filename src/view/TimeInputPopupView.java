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

    public void addLabels(Label week, Label day, Label hour, Label minute) {
        GridPane.setConstraints(week, COLUMN_LABELS, _labelRowNumber++);
        GridPane.setConstraints(day, COLUMN_LABELS, _labelRowNumber++);
        GridPane.setConstraints(hour, COLUMN_LABELS, _labelRowNumber++);
        GridPane.setConstraints(minute, COLUMN_LABELS, _labelRowNumber);

        _layout.getChildren().addAll(week, day, hour, minute);
    }

    public void addFields(TextField setWeek, TextField setDay, TextField setHour, TextField setMinute) {
        GridPane.setConstraints(setWeek, COLUMN_FIELDS, _fieldRowNumber++);
        GridPane.setConstraints(setDay, COLUMN_FIELDS, _fieldRowNumber++);
        GridPane.setConstraints(setHour, COLUMN_FIELDS, _fieldRowNumber++);
        GridPane.setConstraints(setMinute, COLUMN_FIELDS, _fieldRowNumber++);
        GridPane.setConstraints(_submit = new Button("Submit"), COLUMN_FIELDS, _fieldRowNumber);

        _layout.getChildren().addAll(setWeek, setDay, setHour, setMinute, _submit);
    }

    public Button getSubmitButton() {
        return _submit;
    }
}
