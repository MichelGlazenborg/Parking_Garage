package Models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimulatorView {

    private int _numberOfFloors;
    private int _numberOfRows;
    private int _numberOfPlaces;
    private int _numberOfOpenSpots;
    private int _numberOfReservations;
    private int _numberOfPassHolderSpots;
    private Car[][][] _cars;

    private CarParkView _carParkView;

    private int _currentPassHolders;
    private int _currentAdHoc;
    // Replace the two properties below. The second is redundant, while the first one should be renamed.
    private int _currentCarsWithReservation;
    private int _currentReservationsWithoutCars;

    public SimulatorView(Canvas canvas, int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        _numberOfFloors = numberOfFloors;
        _numberOfRows = numberOfRows;
        _numberOfPlaces = numberOfPlaces;
        _numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        _numberOfPassHolderSpots = -1;
        _cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        _carParkView = new CarParkView(canvas);

        _currentPassHolders = 0;
        _currentAdHoc = 0;
        _currentCarsWithReservation = 0;
        _currentReservationsWithoutCars = 0;
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

    public int getNumberOfAdHoc() {
        return _currentAdHoc;
    }

    public int getNumberOfPassHolders() {
        return _currentPassHolders;
    }

    public int getNumberOfCarsWithReservation() {
        return _currentCarsWithReservation;
    }

    public int getNumberOfReservationsWithoutCars() {
        return _currentReservationsWithoutCars;
    }

    public int getNumberOfOpenSpots() {
        return _numberOfOpenSpots;
    }

    public void removeCarFromCount(String type) {
        switch (type) {
            case "AdHocCar":
                _currentAdHoc--;
                break;
            case "CarWithReservedSpot":
                _currentCarsWithReservation--;
                break;
            case "ParkingPassCar":
                _currentPassHolders--;
                break;
        }
    }

    public void addOneCarToCount(String type) {
        switch (type) {
            case "AdHocCar":
                _currentAdHoc++;
                break;
            case "CarWithReservedSpot":
                _currentCarsWithReservation++;
                break;
            case "ParkingPassCar":
                _currentPassHolders++;
                break;
        }
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

    public void reset() {
        for (int i = 0; i < getNumberOfFloors(); i++) {
            for (int j = 0; j < getNumberOfRows(); j++) {
                for (int k = 0; k < getNumberOfPlaces(); k++) {
                    Location location = new Location(i,j,k);
                    removeCarAt(location);
                    updateView();
                }
            }
        }
    }

    public void makePassHolderPlaces() {
        for(int i=0; i<30; i++) {
            setPassHolderSpace(new Location(0, 0, i), new PassHolderSpace());
            setPassHolderSpace(new Location(0, 1, i), new PassHolderSpace());
        }
        updateView();
    }

    public void makePassHolderSpots(int numberOfSpots) {
        reset();
        _numberOfPassHolderSpots = numberOfSpots;
        int x,z,y;
        x=0;
        z=0;
        y=0;
        for (int i=0; i<numberOfSpots; i++) {
            if(z==30) {
                if(x==5) {
                    y++;
                    x=0;
                    z=0;
                } else {
                    x+=1;
                    z=0;
                }
            }
            setPassHolderSpace(new Location(y, x, z), new PassHolderSpace());
            z++;
        }
        updateView();
    }

    public int getPassHolderSpots() {
        return _numberOfPassHolderSpots;
    }

    public void makeReservationsAt(Location loc , int minute, int hour) {
        Reservation res = new Reservation(minute, hour);
        setReservation(loc, res);
        _numberOfReservations++;

        addOneCarToCount("Reservation");
        updateView();
    }

    public int getNumberOfReservations() {
        return _numberOfReservations;
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

    public Location getFirstReservation(int[] cTime) {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if(getCarAt(location) != null) {
                        if(getCarAt(location).getColor() == Reservation.COLOR) {
                            Reservation car = (Reservation) getCarAt(location);
                            if(car.checkReadyToEnter(cTime[0],cTime[1])) {
                                removeCarAt(location);
                                return location;
                            }
                        }
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
                        if(getCarAt(location).getColor() == PassHolderSpace.COLOR) {
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

                        // If this results in slowing down the simulator, remove the two lines below
                        if (car != null)
                            clearParkingSpot(location, color);

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

        private void clearParkingSpot(Location location, Color color) {
            _graphicsContext.clearRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1
            );
        }
    }
}