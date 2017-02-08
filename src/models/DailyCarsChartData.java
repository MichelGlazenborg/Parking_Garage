package models;

/**
 * This class is used for storing data used by the daily cars chart
 * @author  MATA
 * @since   1.0
 */
public class DailyCarsChartData {

    /**
     * This is the label used for the current day. Usually this is an abbreviation of the day name.
     */
    private final String _name;

    /**
     * Contains the amount of cars that entered today.
     */
    private int _carCounter;

    /**
     * Creates a new data object.
     * @param   name        The day name abbreviation
     * @param   carCounter  How many cars entered today?
     */
    public DailyCarsChartData(String name, int carCounter) {
        _name = name;
        _carCounter = carCounter;
    }

    /**
     * @return  String  The day name
     */
    public String getName() {
        return _name;
    }

    /**
     * @return  int     The amount of cars that entered
     */
    public int getCarCounter() {
        return _carCounter;
    }

    /**
     * Updates the car counter.
     * @param   counter     The new amount of cars that entered today
     */
    public void setCarCounter(int counter) {
        _carCounter = counter;
    }
}