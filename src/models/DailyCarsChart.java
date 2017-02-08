package models;

/**
 * This class generates a chart that contains the total amount of cars that entered each day.
 */
public class DailyCarsChart {

    /**
     * These properties will contain DailyCharsChartData objects.
     */
    private DailyCarsChartData _monday;
    private DailyCarsChartData _tuesday;
    private DailyCarsChartData _wednesday;
    private DailyCarsChartData _thursday;
    private DailyCarsChartData _friday;
    private DailyCarsChartData _saturday;
    private DailyCarsChartData _sunday;

    /**
     * We're creating a new chart, so we better make sure we initialize it.
     */
    public DailyCarsChart() {
        reset();
    }

    /**
     * Assign (new) values to the data objects. It's called reset, but this method is also invoked
     * upon the creation of a new object.
     */
    public void reset() {
        _monday = new DailyCarsChartData("Mon", 0);
        _tuesday = new DailyCarsChartData("Tue", 0);
        _wednesday = new DailyCarsChartData("Wed", 0);
        _thursday = new DailyCarsChartData("Thu", 0);
        _friday = new DailyCarsChartData("Fri", 0);
        _saturday = new DailyCarsChartData("Sat", 0);
        _sunday = new DailyCarsChartData("Sun", 0);
    }

    /**
     * Updates the data objects.
     * @param   monday      Arrivals on Monday
     * @param   tuesday     Arrivals on Tuesday
     * @param   wednesday   Arrivals on Wednesday
     * @param   thursday    Arrivals on Thursday
     * @param   friday      Arrivals on Friday
     * @param   saturday    Arrivals on Saturday
     * @param   sunday      Arrivals on Sunday
     */
    public void update(int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday) {
        _monday.setCarCounter(monday);
        _tuesday.setCarCounter(tuesday);
        _wednesday.setCarCounter(wednesday);
        _thursday.setCarCounter(thursday);
        _friday.setCarCounter(friday);
        _saturday.setCarCounter(saturday);
        _sunday.setCarCounter(sunday);
    }

    /**
     * Returns an object of data that contains the amount of cars that entered the parking garage on Monday.
     * @return  DailyCarsChartData  The object that contains the required data.
     */
    public DailyCarsChartData getMonday() {
        return _monday;
    }

    /**
     * Returns an object of data that contains the amount of cars that entered the parking garage on Tuesday.
     * @return  DailyCarsChartData  The object that contains the required data.
     */
    public DailyCarsChartData getTuesday() {
        return _tuesday;
    }

    /**
     * Returns an object of data that contains the amount of cars that entered the parking garage on Wednesday.
     * @return  DailyCarsChartData  The object that contains the required data.
     */
    public DailyCarsChartData getWednesday() {
        return _wednesday;
    }

    /**
     * Returns an object of data that contains the amount of cars that entered the parking garage on Thursday.
     * @return  DailyCarsChartData  The object that contains the required data.
     */
    public DailyCarsChartData getThursday() {
        return _thursday;
    }

    /**
     * Returns an object of data that contains the amount of cars that entered the parking garage on Friday.
     * @return  DailyCarsChartData  The object that contains the required data.
     */
    public DailyCarsChartData getFriday() {
        return _friday;
    }

    /**
     * Returns an object of data that contains the amount of cars that entered the parking garage on Saturday.
     * @return  DailyCarsChartData  The object that contains the required data.
     */
    public DailyCarsChartData getSaturday() {
        return _saturday;
    }

    /**
     * Returns an object of data that contains the amount of cars that entered the parking garage on Sunday.
     * @return  DailyCarsChartData  The object that contains the required data.
     */
    public DailyCarsChartData getSunday() {
        return _sunday;
    }
}