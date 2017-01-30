package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class StatsGraph {

    private ObservableList _graphData;
    private PieChart _chart;

    public StatsGraph() {

    }

    public void setData(ObservableList graphData) {
        _graphData = graphData;
    }

    public void generate() {
        _chart = new PieChart(_graphData);
        _chart.setTitle("Statistics");
    }
}