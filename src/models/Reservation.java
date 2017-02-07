package models;

import javafx.scene.paint.Color;

public class Reservation extends Car {

    public static final Color COLOR = Color.GREEN;

    private int _madeAtMinute;
    private int _madeAtHour;

    public Reservation(int madeAtMinute, int madeAtHour) {
        set_readyToEnter(false);

        _madeAtMinute = madeAtMinute;
        _madeAtHour = madeAtHour;

        int stayMinutes = (60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    /**
     * Although the COLOR constant is public, we still need a getter since we're extending the Car class.
     */
    public Color getColor() {
        return COLOR;
    }

    public boolean checkReadyToEnter(int cMinute, int cHour) {
        if((_madeAtMinute > 29 && cHour > _madeAtHour) || (cMinute >= (_madeAtMinute + 30)))
            set_readyToEnter(true);

        return super.returnReadyToEnter();
    }
}
