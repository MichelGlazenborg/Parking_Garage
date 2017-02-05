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

    public TimeInputPopupView(GridPane layout) {
        _layout = layout;
        _layout.setVgap(10);
        _layout.setHgap(10);
    }

    public void addLabels(Label week, Label day, Label hour, Label minute) {
        GridPane.setConstraints(week, 1, ROW_FIELD_WEEK);
        GridPane.setConstraints(day, 1, ROW_FIELD_DAY);
        GridPane.setConstraints(hour, 1, ROW_FIELD_HOUR);
        GridPane.setConstraints(minute, 1, ROW_FIELD_MINUTE);
    }

    public void addFields(TextField setWeek, TextField setDay, TextField setHour, TextField setMinute) {
        GridPane.setConstraints(setWeek, 3, ROW_FIELD_WEEK);
        GridPane.setConstraints(setDay, 3, ROW_FIELD_DAY);
        GridPane.setConstraints(setHour, 3, ROW_FIELD_HOUR);
        GridPane.setConstraints(setMinute, 3, ROW_FIELD_MINUTE);

        _layout.getChildren().addAll(setWeek, setDay, setHour, setMinute);
    }
}
