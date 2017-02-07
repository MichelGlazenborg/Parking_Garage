package models;

public class OccupationChartData {

    private final String _label;
    private double _percentage;

    public OccupationChartData(String label) {
        _label = label;
        _percentage = 0;
    }

    public OccupationChartData(String label, double percentage) {
        _label = label;
        _percentage = percentage;
    }

    public String getLabel() {
        return _label;
    }

    public double getPercentage() {
        return _percentage;
    }

    public void setPercentage(double percentage) {
        _percentage = percentage;
    }
}