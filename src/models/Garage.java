package models;

import javafx.scene.canvas.Canvas;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import view.GarageView;

import java.io.File;

public class Garage {

    private int _numberOfFloors;
    private int _numberOfRows;
    private int _numberOfPlaces;
    private int _numberOfOpenSpots;
    private int _numberOfPassHolderSpots;
    private double _speed;
    private boolean _playSound = true;
    private Car[][][] _cars;

    private GarageView _garageView;

    private int _currentPassHolders;
    private int _currentAdHoc;
    private int _currentCarsWithReservation;

    public Garage(Canvas canvas, int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        _numberOfFloors = numberOfFloors;
        _numberOfRows = numberOfRows;
        _numberOfPlaces = numberOfPlaces;
        _numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        _numberOfPassHolderSpots = -1;
        _cars = new Car[4][10][50];
        _garageView = new GarageView(canvas, this);

        _currentPassHolders = 0;
        _currentAdHoc = 0;
        _currentCarsWithReservation = 0;
    }

    public void setSpeed(double speed) {
        _speed = speed;
    }

    public boolean getPlaySound() {
        return _playSound;
    }

    public void setPlaySound(boolean a) {
        _playSound = a;
    }

    public void updateView() {
        _garageView.update();
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
        if (!locationIsValid(location))
            return null;

        return _cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public void eraseCanvas() {
        _garageView.eraseCanvas();
    }

    public void playSound() {
        String musicFile = "src/assets/ping.mp3";

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
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
            if (_speed < 4 && _playSound) {
                playSound();
            }

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

        _numberOfPassHolderSpots = 0;
        _currentAdHoc = 0;
        _currentPassHolders = 0;
        _currentCarsWithReservation = 0;
    }

    /**
     * Makes passHolder spots
     * @param numberOfSpots the number of passHolder spots you want
     */
    public void makePassHolderSpots(int numberOfSpots) {
        _numberOfPassHolderSpots = numberOfSpots;

        int x = 0,
            z = 0,
            y = 0;

        for (int i=0; i<numberOfSpots; i++) {
            if (z == _numberOfPlaces) {
                if (x == _numberOfRows - 1) {
                    y++;
                    x = 0;
                    z = 0;
                }
                else {
                    x += 1;
                    z = 0;
                }
            }
            setPassHolderSpace(new Location(y, x, z), new PassHolderSpace());
            z++;
        }
        updateView();
    }

    public void makeReservationsAt(Location loc , int minute, int hour) {
        Reservation res = new Reservation(minute, hour);
        setReservation(loc, res);

        addOneCarToCount("Reservation");
        updateView();
    }

    public boolean setPassHolderSpace(Location loc, PassHolderSpace phs) {
        if (!locationIsValid(loc))
            return false;

        Car oldCar = getCarAt(loc);
        if(oldCar == null) {
            _cars[loc.getFloor()][loc.getRow()][loc.getPlace()] = phs;
            phs.setLocation(loc);
            return true;
        }

        return false;
    }

    public boolean setReservation(Location loc, Reservation res) {
        if (!locationIsValid(loc))
            return false;

        Car oldCar = getCarAt(loc);
        if(oldCar == null) {
            _cars[loc.getFloor()][loc.getRow()][loc.getPlace()] = res;
            res.setLocation(loc);
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location))
            return null;

        Car car = getCarAt(location);
        if (getCarAt(location) == null)
            return null;

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
                    if (getCarAt(location) == null)
                        return location;
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
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying())
                        return car;
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
                    if (car != null)
                        car.tick();
                }
            }
        }
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        return !(floor < 0 || floor >= _numberOfFloors || row < 0 || row > _numberOfRows || place < 0 || place > _numberOfPlaces);
    }

    public int getNumberOfFloors() {
        return _numberOfFloors;
    }

    public void set_numberOfFloors(int floors) {_numberOfFloors = floors;}

    public int getNumberOfRows() {
        return _numberOfRows;
    }

    public void set_numberOfRows(int rows) {_numberOfRows = rows;}

    public int getNumberOfPlaces() {
        return _numberOfPlaces;
    }

    public void set_numberOfPlaces(int places) { _numberOfPlaces = places; }

    public int getNumberOfAdHoc() {
        return _currentAdHoc;
    }

    public int getNumberOfPassHolders() {
        return _currentPassHolders;
    }

    public int getNumberOfCarsWithReservation() {
        return _currentCarsWithReservation;
    }

    public int getNumberOfOpenSpots() {
        return _numberOfOpenSpots;
    }

    public int getNumberOfPassHolderSpots() {
        return _numberOfPassHolderSpots;
    }

    public int getPassHolderSpots() {
        return _numberOfPassHolderSpots;
    }
}