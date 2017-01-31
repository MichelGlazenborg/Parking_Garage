package Models;

public class StatsData {

    private String _label;
    private int _percentage;

    public StatsData(String label, int percentage) {
        _label = label;
        _percentage = percentage;
    }

    public String getLabel() {
        return _label;
    }

    public int getPercentage() {
        return _percentage;
    }

    public void setPercentage(int percentage) {
        _percentage = percentage;
    }
}