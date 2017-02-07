package models;

public class DailyCarsChartData {

    private final String _name;
    private int _carCounter;

    public DailyCarsChartData(String name, int carCounter) {
        _name = name;
        _carCounter = carCounter;
    }

    public String getName() {
        return _name;
    }

    public int getCarCounter() {
        return _carCounter;
    }

    public void setCarCounter(int counter) {
        _carCounter = counter;
    }
}