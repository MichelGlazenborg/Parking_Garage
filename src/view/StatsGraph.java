package view;

import models.StatsPie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

public class StatsGraph {

    private ObservableList _graphData;
    private PieChart _chart;
    private StatsPie _statsPie;

    private PieChart.Data _statsPieUnoccupied;
    private PieChart.Data _statsPieAdHoc;
    private PieChart.Data _statsPiePassHolders;
    private PieChart.Data _statsPieCarWithReservations;

    public StatsGraph(StatsPie pie) {
        _statsPie = pie;
        _chart = new PieChart();
        _chart.setTitle("Statistics");

        _graphData = FXCollections.observableArrayList();
    }

    public void setData() {
        _graphData.clear();

        _graphData = FXCollections.observableArrayList(
            _statsPieUnoccupied = new PieChart.Data(_statsPie.getUnoccupied().getLabel(), _statsPie.getUnoccupied().getPercentage()),
            _statsPieAdHoc = new PieChart.Data(_statsPie.getAdHoc().getLabel(), _statsPie.getAdHoc().getPercentage()),
            _statsPiePassHolders = new PieChart.Data(_statsPie.getPassHolders().getLabel(), _statsPie.getPassHolders().getPercentage()),
            _statsPieCarWithReservations = new PieChart.Data(_statsPie.getCarsWithReservation().getLabel(), _statsPie.getCarsWithReservation().getPercentage())
        );
        _chart.setData(_graphData);
    }

    public void update() {
        _statsPieUnoccupied.setPieValue(_statsPie.getUnoccupied().getPercentage());
        _statsPieAdHoc.setPieValue(_statsPie.getAdHoc().getPercentage());
        _statsPiePassHolders.setPieValue(_statsPie.getPassHolders().getPercentage());
        _statsPieCarWithReservations.setPieValue(_statsPie.getCarsWithReservation().getPercentage());
    }

    public PieChart getChart() {
        return _chart;
    }
}