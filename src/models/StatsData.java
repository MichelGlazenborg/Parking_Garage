package models;

public class StatsData {

    private String _label;
    private double _percentage;

    public StatsData(String label) {
        _label = label;
    }

    public StatsData(String label, double percentage) {
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