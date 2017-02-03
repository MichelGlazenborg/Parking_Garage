package models;

import javafx.scene.paint.Color;

/**
 * Created by Jelmer on 26-Jan-17.
 */
public class PassHolderSpace extends Car{
    public static final Color COLOR = Color.BLACK;

    public PassHolderSpace() {

        int stayMinutes = (100000);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    /**
     * Although the COLOR constant is public, we still need a getter since we're extending the Car class.
     */
    public Color getColor() {return COLOR;}

}
