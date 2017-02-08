package view;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import models.DailyCarsChart;

public class DailyCarsChartView {

    private final LineChart<String, Number> _lineChart;
    private final XYChart.Series _chartData;
    private final DailyCarsChart _chart;

    private XYChart.Data _dataMonday;
    private XYChart.Data _dataTuesday;
    private XYChart.Data _dataWednesday;
    private XYChart.Data _dataThursday;
    private XYChart.Data _dataFriday;
    private XYChart.Data _dataSaturday;
    private XYChart.Data _dataSunday;

    public DailyCarsChartView(DailyCarsChart chart) {
        _chart = chart;

        CategoryAxis _xAxis = new CategoryAxis();
        NumberAxis _yAxis = new NumberAxis();

        _xAxis.setLabel("Day");

        _lineChart = new LineChart<>(_xAxis, _yAxis);
        _lineChart.getYAxis().setAutoRanging(true);
        setChartTitle("Cars per day");

        _chartData = new XYChart.Series();
    }

    /**
     * Allocates the chart to our data
     */
    public void setData() {
        _chartData.getData().add(_dataMonday = new XYChart.Data(_chart.getMonday().getName(), _chart.getMonday().getCarCounter()));
        _chartData.getData().add(_dataTuesday = new XYChart.Data(_chart.getTuesday().getName(), _chart.getTuesday().getCarCounter()));
        _chartData.getData().add(_dataWednesday = new XYChart.Data(_chart.getWednesday().getName(), _chart.getWednesday().getCarCounter()));
        _chartData.getData().add(_dataThursday = new XYChart.Data(_chart.getThursday().getName(), _chart.getThursday().getCarCounter()));
        _chartData.getData().add(_dataFriday = new XYChart.Data(_chart.getFriday().getName(), _chart.getFriday().getCarCounter()));
        _chartData.getData().add(_dataSaturday = new XYChart.Data(_chart.getSaturday().getName(), _chart.getSaturday().getCarCounter()));
        _chartData.getData().add(_dataSunday = new XYChart.Data(_chart.getSunday().getName(), _chart.getSunday().getCarCounter()));

        _lineChart.getData().add(_chartData);
    }

    /**
     * Updates the chart with actual data
     */
    public void update() {
        _dataMonday.setYValue(_chart.getMonday().getCarCounter());
        _dataTuesday.setYValue(_chart.getTuesday().getCarCounter());
        _dataWednesday.setYValue(_chart.getWednesday().getCarCounter());
        _dataThursday.setYValue(_chart.getThursday().getCarCounter());
        _dataFriday.setYValue(_chart.getFriday().getCarCounter());
        _dataSaturday.setYValue(_chart.getSaturday().getCarCounter());
        _dataSunday.setYValue(_chart.getSunday().getCarCounter());
    }

    /**
     * Returns the chart for viewing
     * @return the chart filled with data
     */
    public LineChart getChart() {
        return _lineChart;
    }

    /**
     * Set a title for the chart
     * @param title = the title of this chart
     */
    private void setChartTitle(String title) {
        _lineChart.setTitle(title);
    }
}