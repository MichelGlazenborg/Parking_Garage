package Models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimulatorView {

    private int _numberOfFloors;
    private int _numberOfRows;
    private int _numberOfPlaces;
    private int _numberOfOpenSpots;
    private Car[][][] _cars;

    private CarParkView _carParkView;

    public SimulatorView(Canvas canvas, int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        _numberOfFloors = numberOfFloors;
        _numberOfRows = numberOfRows;
        _numberOfPlaces = numberOfPlaces;
        _numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        _cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        _carParkView = new CarParkView(canvas);
    }

    public void updateView() {
        _carParkView.update();
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

    public void makePassHolderPlaces() {
        for(int i=0; i<30; i++) {
            setPassHolderSpace(new Location(0, 0, i), new PassHolderSpace());
            setPassHolderSpace(new Location(0, 1, i), new PassHolderSpace());
        }
        updateView();
    }


    public void makeReservationsAt(Location loc) {
        Reservation res = new Reservation();
        setReservation(loc, res);
        updateView();
    }



    public boolean setPassHolderSpace(Location loc, PassHolderSpace phs) {
        if (!locationIsValid(loc)) {
            return false;
        }
        Car oldcar = getCarAt(loc);
        if(oldcar == null) {
            _cars[loc.getFloor()][loc.getRow()][loc.getPlace()] = phs;
            phs.setLocation(loc);
            return true;
        }
        return false;
    }

    public boolean setReservation(Location loc, Reservation res) {
        if (!locationIsValid(loc)) {
            return false;
        }
        Car oldcar = getCarAt(loc);
        if(oldcar == null) {
            _cars[loc.getFloor()][loc.getRow()][loc.getPlace()] = res;
            res.setLocation(loc);
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
                    if (getCarAt(location) == null ) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public Location getFirstPassSpot() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if(getCarAt(location) != null) {
                        if(getCarAt(location).getColor() == Color.BLACK) {
                            removeCarAt(location);
                            return location;
                        }
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

    private class CarParkView {

        private GraphicsContext _graphicsContext;

        public CarParkView(Canvas canvas) {
            _graphicsContext = canvas.getGraphicsContext2D();
        }

        public void update() {
            for (int floor = 0; floor <getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        Color color = (car == null ? Color.WHITE : car.getColor());
                        drawParkingSpot(location, color);
                    }
                }
            }

        }

        private void drawParkingSpot(Location location, Color color) {
            _graphicsContext.setFill(color);
            _graphicsContext.fillRect(
                    location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }
    }
}