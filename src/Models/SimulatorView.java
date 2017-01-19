package Models;

public class SimulatorView {

    private int _numberOfFloors;
    private int _numberOfRows;
    private int _numberOfPlaces;
    private int _numberOfOpenSpots;
    private Car[][][] _cars;

    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        _numberOfFloors = numberOfFloors;
        _numberOfRows = numberOfRows;
        _numberOfPlaces = numberOfPlaces;
        _numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        _cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
    }


    public int getNumberOfFloors() {
        return _numberOfFloors;
    }

    public int getNumberOfRows() {
        return _numberOfRows;
    }

    public int getNumberOfPlaces() {
        return _numberOfPlaces;
    }

    public int getNumberOfOpenSpots() {
        return _numberOfOpenSpots;
    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return _cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            _cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            _numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        _cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        _numberOfOpenSpots++;
        return car;
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= _numberOfFloors || row < 0 || row > _numberOfRows || place < 0 || place > _numberOfPlaces) {
            return false;
        }
        return true;
    }

}