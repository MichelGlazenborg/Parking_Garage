package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TimeInputPopupView {

    private GridPane _layout;
    private static final int ROW_FIELD_WEEK = 2;
    private static final int ROW_FIELD_DAY = 4;
    private static final int ROW_FIELD_HOUR = 6;
    private static final int ROW_FIELD_MINUTE = 8;

    private static final int COLUMN_LABELS = 1;
    private static final int COLUMN_FIELDS = 2;

    public TimeInputPopupView(GridPane layout) {
        _layout = layout;
        _layout.setVgap(10);
        _layout.setHgap(10);
    }

    public void addLabels(Label week, Label day, Label hour, Label minute) {
        GridPane.setConstraints(week, COLUMN_LABELS, ROW_FIELD_WEEK);
        GridPane.setConstraints(day, COLUMN_LABELS, ROW_FIELD_DAY);
        GridPane.setConstraints(hour, COLUMN_LABELS, ROW_FIELD_HOUR);
        GridPane.setConstraints(minute, COLUMN_LABELS, ROW_FIELD_MINUTE);

        _layout.getChildren().addAll(week, day, hour, minute);
    }

    public void addFields(TextField setWeek, TextField setDay, TextField setHour, TextField setMinute) {
        GridPane.setConstraints(setWeek, COLUMN_FIELDS, ROW_FIELD_WEEK);
        GridPane.setConstraints(setDay, COLUMN_FIELDS, ROW_FIELD_DAY);
        GridPane.setConstraints(setHour, COLUMN_FIELDS, ROW_FIELD_HOUR);
        GridPane.setConstraints(setMinute, COLUMN_FIELDS, ROW_FIELD_MINUTE);

        _layout.getChildren().addAll(setWeek, setDay, setHour, setMinute);
    }
}
