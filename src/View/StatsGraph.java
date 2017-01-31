package View;

import javafx.collections.ObservableList;
import javafx.scene.chart.*;

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

    public PieChart getChart() {
        return _chart;
    }
}