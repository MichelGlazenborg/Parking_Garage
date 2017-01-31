package Models;

public class StatsData {

    private String _label;
    private int _amount;
    private int _percentage;

    public StatsData(String label, int amount) {
        _label = label;
        _amount = amount;
        _percentage = 0;
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