package models;

public class Location {

    private int _floor;
    private int _row;
    private int _place;

    /**
     * Constructor for objects of class controllers.Location
     */
    public Location(int floor, int row, int place) {
        _floor = floor;
        _row = row;
        _place = place;
    }

    /**
     * Implement content equality.
     */
    public boolean equals(Object obj) {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return _floor == other.getFloor() && _row == other.getRow() && _place == other.getPlace();
        }
        else {
            return false;
        }
    }

    /**
     * Return a string of the form floor,row,place.
     * @return A string representation of the location.
     */
    public String toString() {
        return _floor + "," + _row + "," + _place;
    }

    /**
     * Use the 10 bits for each of the floor, row and place
     * values. Except for very big car parks, this should give
     * a unique hash code for each (floor, row, place) type.
     * @return A hashcode for the location.
     */
    public int hashCode() {
        return (_floor << 20) + (_row << 10) + _place;
    }

    /**
     * @return The floor.
     */
    public int getFloor() {
        return _floor;
    }

    /**
     * @return The row.
     */
    public int getRow() {
        return _row;
    }

    /**
     * @return The place.
     */
    public int getPlace() {
        return _place;
    }
}