package models;

public class DailyCarsChart {

    private DailyCarsChartData _monday;
    private DailyCarsChartData _tuesday;
    private DailyCarsChartData _wednesday;
    private DailyCarsChartData _thursday;
    private DailyCarsChartData _friday;
    private DailyCarsChartData _saturday;
    private DailyCarsChartData _sunday;

    public DailyCarsChart() {
        reset();
    }

    public void reset() {
        _monday = new DailyCarsChartData("Mon", 0);
        _tuesday = new DailyCarsChartData("Tue", 0);
        _wednesday = new DailyCarsChartData("Wed", 0);
        _thursday = new DailyCarsChartData("Thu", 0);
        _friday = new DailyCarsChartData("Fri", 0);
        _saturday = new DailyCarsChartData("Sat", 0);
        _sunday = new DailyCarsChartData("Sun", 0);
    }

    public void update(int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday) {
        _monday.setCarCounter(monday);
        _tuesday.setCarCounter(tuesday);
        _wednesday.setCarCounter(wednesday);
        _thursday.setCarCounter(thursday);
        _friday.setCarCounter(friday);
        _saturday.setCarCounter(saturday);
        _sunday.setCarCounter(sunday);
    }

    public DailyCarsChartData getMonday() {
        return _monday;
    }

    public DailyCarsChartData getTuesday() {
        return _tuesday;
    }

    public DailyCarsChartData getWednesday() {
        return _wednesday;
    }

    public DailyCarsChartData getThursday() {
        return _thursday;
    }

    public DailyCarsChartData getFriday() {
        return _friday;
    }

    public DailyCarsChartData getSaturday() {
        return _saturday;
    }

    public DailyCarsChartData getSunday() {
        return _sunday;
    }
}