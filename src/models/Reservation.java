package models;

import javafx.scene.paint.Color;

/**
 * Created by Jelmer on 23-Jan-17.
 */
public class Reservation extends Car {
    public static final Color COLOR = Color.GREEN;
    private int madeAtMinute;
    private int madeAtHour;

    public Reservation(int madeAtMinute, int madeAtHour) {
        set_readyToEnter(false);
        this.madeAtMinute = madeAtMinute;
        this.madeAtHour = madeAtHour;
        int stayMinutes = (60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    /**
     * Although the COLOR constant is public, we still need a getter since we're extending the Car class.
     * @todo Remove this method
     */
    public Color getColor() {return COLOR;}

    public boolean checkReadyToEnter(int cMinute, int cHour) {

        if(madeAtMinute > 29) {
            if(cHour > madeAtHour) {
                set_readyToEnter(true);
            }
        } else {
            int timeTaken = madeAtMinute + 30;
            if (cMinute >= timeTaken) {
                    set_readyToEnter(true);
            }

        }
        return super.returnReadyToEnter();
    }
}
