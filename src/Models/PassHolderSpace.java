package Models;

import javafx.scene.paint.Color;

/**
 * Created by Jelmer on 26-Jan-17.
 */
public class PassHolderSpace extends Car{
    private static final Color COLOR = Color.BLACK;

    public PassHolderSpace() {

        int stayMinutes = (10000);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    public Color getColor() {return COLOR;}

}
