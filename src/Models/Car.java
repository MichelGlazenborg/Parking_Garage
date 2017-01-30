package Models;

public abstract class Car {

    private Location _location;
    private int _stayMinutes;
    private int _minutesLeft;
    private boolean _isPaying;
    private boolean _hasToPay;

    /**
     * Constructor for objects of class controllers.Car
     */
    public Car() {

    }

    /**
     * Returns the location of a car
     * @return Location location of the car
     */
    public Location getLocation() {
        return _location;
    }

    /**
     * Sets the location of a car
     * @param location the location of a car
     */
    public void setLocation(Location location) {
        _location = location;
    }

    /**
     * Returns the number of minutes left of parking
     * @return int time left in minutes
     */
    public int getMinutesLeft() {
        return _minutesLeft;
    }

    /**
     * Sets the number of minutes a car has left
     * @param minutesLeft time left in minutes
     */
    public void setMinutesLeft(int minutesLeft) {
        _minutesLeft = minutesLeft;
    }

    /**
     * Returns if the car is paying
     * @return boolean car is paying
     */
    public boolean getIsPaying() {
        return _isPaying;
    }

    /**
     * Sets if the car is paying
     * @param isPaying is the car paying
     */
    public void setIsPaying(boolean isPaying) {
        _isPaying = isPaying;
    }
    public int getStayMinutes(){
        return _stayMinutes;
    }

    public void setStayMinutes(int time) {_stayMinutes = time;}

    /**
     * Return if the car has to pay
     * @return boolean car has to pay
     */
    public boolean getHasToPay() {
        return _hasToPay;
    }

    /**
     * Sets if the car has to pay
     * @param hasToPay does the car need to pay
     */
    public void setHasToPay(boolean hasToPay) {
        _hasToPay = hasToPay;
    }

    public void tick() {
        _minutesLeft--;
    }
    
    public abstract javafx.scene.paint.Color getColor();
}