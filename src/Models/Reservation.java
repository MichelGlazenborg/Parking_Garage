package Models;

import javafx.scene.paint.Color;

/**
 * Created by Jelmer on 23-Jan-17.
 */
public class Reservation extends Car {
    public static final Color COLOR = Color.GREEN;

    public Reservation() {

        int stayMinutes = (200);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    /**
     * Although the COLOR constant is public, we still need a getter since we're extending the Car class.
     * @todo Remove this method
     */
    public Color getColor() {return COLOR;}
}
