package models;

import javafx.scene.paint.Color;

/**
 * This abstract class is used by several car type classes. It contains several
 * methods that can be used by these car type classes.
 * @author  MATA
 * @since   1.0
 */
public abstract class Car {

    /**
     * This property refers to the location of the car.
     */
    private Location _location;

    /**
     * How many minutes is the car supposed to stay parked in our beloved garage?
     */
    private int _stayMinutes;

    /**
     * How much time (in minutes) remains until the car leaves.
     */
    private double _minutesLeft;

    /**
     * Is the car currently paying?
     */
    private boolean _isPaying;

    /**
     * Determines if the car has to pay
     */
    private boolean _hasToPay;

    /**
     * If the car has a reservation, this should be set to true.
     */
    private boolean _hasReservation;

    /**
     * If the car is currently in the entrance queue, this should be set to true.
     */
    private boolean _readyToEnter;

    /**
     * Constructor for objects of class controllers.Car
     */
    public Car() {
        _readyToEnter = false;
    }

    /**
     * Returns the location of a car
     * @return  Location    Location of the car
     */
    public Location getLocation() {
        return _location;
    }

    /**
     * Sets the location of a car.
     * @param   location    The location of a car
     */
    public void setLocation(Location location) {
        _location = location;
    }

    /**
     * Used for determining whether or not the car is ready to enter.
     * @return  boolean
     */
    public boolean returnReadyToEnter() {
        return _readyToEnter;
    }

    /**
     * Sets the value of _readyToEnter
     * @param   isReady   Should be true if the car is ready to enter, false if it's not
     */
    public void set_readyToEnter(boolean isReady) {
        _readyToEnter = isReady;
    }

    /**
     * Returns the number of minutes left of parking
     * @return  time    Left in minutes
     */
    public double getMinutesLeft() {
        return _minutesLeft;
    }

    /**
     * Sets the number of minutes a car has left
     * @param   minutesLeft   Time left in minutes
     */
    public void setMinutesLeft(double minutesLeft) {
        _minutesLeft = minutesLeft;
    }

    /**
     * Returns if the car is paying
     * @return  boolean     True/false depending on whether the car is paying or not
     */
    public boolean getIsPaying() {
        return _isPaying;
    }

    /**
     * Sets if the car is paying
     * @param   isPaying    Is the car paying
     */
    public void setIsPaying(boolean isPaying) {
        _isPaying = isPaying;
    }

    /**
     * Returns the value of _stayMinutes
     * @return  int     The time in minutes the car has left
     */
    public int getStayMinutes(){
        return _stayMinutes;
    }

    /**
     * Sets the value of _stayMinutes
     * @param   time    The amount of time (in minutes) the car gets to stay.
     */
    public void setStayMinutes(int time) {
        _stayMinutes = time;
    }

    /**
     * Return if the car has to pay
     * @return boolean car has to pay
     */
    public boolean getHasToPay() {
        return _hasToPay;
    }

    /**
     * Gets the value of _hasReservation
     * @return  boolean     The value of _hasReservation
     */
    public boolean getHasReservation() {
        return  _hasReservation;
    }

    /**
     * Sets the value of _hasReservation
     * @param   hasReservation  True/false depending on whether the car's owner has a reservation
     */
    public void setHasReservation(boolean hasReservation) {
        _hasReservation = hasReservation;
    }

    /**
     * Sets if the car has to pay
     * @param   hasToPay    Does the car need to pay
     */
    public void setHasToPay(boolean hasToPay) {
        _hasToPay = hasToPay;
    }

    /**
     * Reduces the minutes left by one.
     */
    public void tick() {
        _minutesLeft--;
    }

    /**
     * All car classes that extend this class should definitely have this method.
     */
    public abstract Color getColor();
}