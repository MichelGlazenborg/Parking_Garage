package models;

import javafx.scene.paint.Color;

public class Reservation extends Car {

    public static final Color COLOR = Color.GREEN;

    private int _madeAtMinute;      //The minute the reservation was created
    private int _madeAtHour;        //The hour the reservation was created

    /**
     * Constructor for a reservation
     * @param madeAtMinute  The minute the reservation was created
     * @param madeAtHour    The hour the reservation was created
     */
    public Reservation(int madeAtMinute, int madeAtHour) {
        set_readyToEnter(false);

        _madeAtMinute = madeAtMinute;
        _madeAtHour = madeAtHour;

        double stayMinutes = (Double.POSITIVE_INFINITY);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    /**
     * Although the COLOR constant is public, we still need a getter since we're extending the Car class.
     */
    public Color getColor() {
        return COLOR;
    }

    /**
     * Checks whether 30 minutes has past since the reservation has been made
     * @param cMinute   The minute the reservation was created
     * @param cHour     The hour the reservation was created
     * @return  True if the car is ready to enter, False if it isn't ready
     */
    public boolean checkReadyToEnter(int cMinute, int cHour) {
        if((_madeAtMinute > 29 && cHour > _madeAtHour) || (cMinute >= (_madeAtMinute + 30)))
            set_readyToEnter(true);

        return super.returnReadyToEnter();
    }
}
