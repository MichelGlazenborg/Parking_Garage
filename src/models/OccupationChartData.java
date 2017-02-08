package models;

public class OccupationChartData {

    private final String _label;
    private double _percentage;

    public OccupationChartData(String label) {
        _label = label;
        _percentage = 0;
    }

    /**
     * Overloading constructors for use with just a label, and use with a label and a percentage
     * @param label = label for the data
     * @param percentage = what part of the pie chart is filled in with this data
     */
    public OccupationChartData(String label, double percentage) {
        _label = label;
        _percentage = percentage;
    }

    /**
     * Gets the label for the data
     * @return = the label
     */
    public String getLabel() {
        return _label;
    }

    /**
     * Gets the percentage of the pie chart this data occupies
     * @return = the percentage of the pie chart
     */
    public double getPercentage() {
        return _percentage;
    }

    /**
     * Sets the percentage of the pie chart this data should occupy
     * @param percentage = the percentage
     */
    public void setPercentage(double percentage) {
        _percentage = percentage;
    }
}