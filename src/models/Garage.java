package models;

import java.io.File;

import javafx.scene.canvas.Canvas;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

import view.GarageView;

/**
 * This class is used for the garage itself
 * @author  MATA
 * @since   1.0
 */
public class Garage {

    /**
     * Numbers of floors, rows, places and open spots. There's also a property
     * for the amount of pass holder spots, referring to the amount of spots
     * reserved for pass holders' cars.
     */
    private int _numberOfFloors;
    private int _numberOfRows;
    private int _numberOfPlaces;
    private int _numberOfOpenSpots;
    private int _numberOfPassHolderSpots;

    /**
     * Two simulator settings; the speed of the simulation and whether or not
     * the program should play sounds.
     */
    private double _speed;
    private boolean _playSound = true;

    /**
     * This array is used for the car spots.
     */
    private Car[][][] _cars;

    /**
     * An object of the GarageView class. This is used as the view.
     */
    private GarageView _garageView;

    /**
     * The number of pass holders, ad hoc cars and cars whose owners have a reservation.
     */
    private int _currentPassHolders;
    private int _currentAdHoc;
    private int _currentCarsWithReservation;

    /**
     * Creates a new garage
     * @param   canvas              The main canvas. This will be used for drawing on.
     * @param   numberOfFloors      The number of floors
     * @param   numberOfRows        The number of rows
     * @param   numberOfPlaces      The number of places
     */
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

    /**
     * Sets the simulation speed
     * @param   speed   How fast the simulator should run
     */
    public void setSpeed(double speed) {
        _speed = speed;
    }

    /**
     * @return  boolean     Whether or not the program will play sounds.
     */
    public boolean getPlaySound() {
        return _playSound;
    }

    /**
     * Sets the value of _playSound
     * @param   a   Whether or not the program should play sounds
     */
    public void setPlaySound(boolean a) {
        _playSound = a;
    }

    /**
     * Updates the view
     */
    public void updateView() {
        _garageView.update();
    }

    /**
     * Removes one car from the respective car count
     * @param   type    The name of the class
     */
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

    /**
     * Adds one car to the respective car count
     * @param   type    The name of the class
     */
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

    /**
     * Returns a car object
     * @param   location    The car's location
     * @return  car         The car currently parked at the given location
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location))
            return null;

        return _cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Erases the canvas
     */
    public void eraseCanvas() {
        _garageView.eraseCanvas();
    }

    /**
     * Plays the sound
     */
    public void playSound() {
        String musicFile = "src/assets/ping.mp3";

        try {
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
        catch (MediaException e) {
            setPlaySound(false);
            System.out.println("Could not play audio file.");
        }
    }

    /**
     * Adds a new car to the given location
     * @param   location        The location
     * @param   car             The car that should be added
     * @return  boolean         Whether or not the car could be added
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }

        // Can't park a car if there's already a car in the given spot
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

    /**
     * Resets the simulation.
     */
    public void reset() {
        // Reset the cars
        for (int i = 0; i < getNumberOfFloors(); i++) {
            for (int j = 0; j < getNumberOfRows(); j++) {
                for (int k = 0; k < getNumberOfPlaces(); k++) {
                    Location location = new Location(i,j,k);
                    removeCarAt(location);
                    updateView();
                }
            }
        }

        // Reset the counters
        _numberOfPassHolderSpots = 0;
        _currentAdHoc = 0;
        _currentPassHolders = 0;
        _currentCarsWithReservation = 0;
    }

    /**
     * Makes passHolder spots
     * @param   numberOfSpots   The number of passHolder spots you want
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

    /**
     * Creates a new reservation
     * @param   loc         The location
     * @param   minute      The minute the reservation was made
     * @param   hour        The hour the reservation was made
     */
    public void makeReservationsAt(Location loc , int minute, int hour) {
        Reservation res = new Reservation(minute, hour);
        setReservation(loc, res);

        addOneCarToCount("Reservation");
        updateView();
    }

    /**
     * Sets a pass holder space
     * @param   loc         The location
     * @param   phs         The pass holder space
     * @return  boolean     Whether or not the action was successful
     */
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

    /**
     * Sets a new reservation
     * @param   loc     The car's location
     * @param   res     An object of the reservation class
     * @return  boolean Whether or not the action was succesful
     */
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

    /**
     * Removes a car from the specified location
     * @param   location    The location
     * @return  car         The car that was removed
     */
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

    /**
     * Gets the first free location.
     * @return  location    The parking spot that is currently unoccupied.
     */
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

    /**
     * Gets the first free reservation.
     * @param   cTime       When is the car supposed to enter?
     * @return  location    The location of a reservation
     */
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

    /**
     * Gets the first free parking pass spot.
     * @return  location    The location
     */
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


    /**
     * Gets the first leaving car
     * @return  car     The car that is about to leave.
     */
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

    /**
     * Runs one tick (i.e. one second)
     */
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

    /**
     * Determines whether or not the location is valid
     * @param   location    The location
     * @return  boolean     Whether or not the location is valid
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        return !(floor < 0 || floor >= _numberOfFloors || row < 0 || row > _numberOfRows || place < 0 || place > _numberOfPlaces);
    }

    /**
     * @return  int     The number of floors
     */
    public int getNumberOfFloors() {
        return _numberOfFloors;
    }

    /**
     * Sets the number of floors
     * @param   floors  The number of floors
     */
    public void set_numberOfFloors(int floors) {
        _numberOfFloors = floors;
    }

    /**
     * @return  int     The number of rows
     */
    public int getNumberOfRows() {
        return _numberOfRows;
    }

    /**
     * Sets the number of rows
     * @param   rows    The number of rows
     */
    public void set_numberOfRows(int rows) {
        _numberOfRows = rows;
    }

    /**
     * @return  int     The number of parking spots per row.
     */
    public int getNumberOfPlaces() {
        return _numberOfPlaces;
    }

    /**
     * Sets the number of parking spots per row.
     * @param places
     */
    public void set_numberOfPlaces(int places) {
        _numberOfPlaces = places;
    }

    /**
     * @return  int     The number of ad hoc cars currently in the garage
     */
    public int getNumberOfAdHoc() {
        return _currentAdHoc;
    }

    /**
     * @return  int     The number of pass holder cars currently in the garage
     */
    public int getNumberOfPassHolders() {
        return _currentPassHolders;
    }

    /**
     * @return  int     The number of cars with a reservation that are currently in the garage.
     */
    public int getNumberOfCarsWithReservation() {
        return _currentCarsWithReservation;
    }

    /**
     * @return  int     The number of open spots
     */
    public int getNumberOfOpenSpots() {
        return _numberOfOpenSpots;
    }

    /**
     * @return  int     The number of pass holder spots
     */
    public int getNumberOfPassHolderSpots() {
        return _numberOfPassHolderSpots;
    }

    /**
     * @return  int     The number of pass holder spots
     */
    public int getPassHolderSpots() {
        return _numberOfPassHolderSpots;
    }
}