package Models;

import java.awt.*;

public abstract class Car {

    private Location _location;
    private int _minutesLeft;
    private boolean _isPaying;
    private boolean _hasToPay;

    /**
     * Constructor for objects of class controllers.Car
     */
    public Car() {

    }

    public Location getLocation() {
        return _location;
    }

    public void setLocation(Location location) {
        _location = location;
    }

    public int getMinutesLeft() {
        return _minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        _minutesLeft = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return _isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        _isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return _hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        _hasToPay = hasToPay;
    }

    public void tick() {
        _minutesLeft--;
    }
    
    public abstract Color getColor();
}