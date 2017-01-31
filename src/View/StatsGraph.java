package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

import java.util.ArrayList;

public class StatsGraph {

    private ObservableList _graphData;
    private PieChart _chart;

    public StatsGraph() {

    }

    public void setData(ArrayList graphData) {
        _graphData = FXCollections.observableArrayList(graphData);
    }

    public void generate() {
        _chart = new PieChart(_graphData);
        _chart.setTitle("Statistics");
    }

    public PieChart getChart() {
        return _chart;
    }
}