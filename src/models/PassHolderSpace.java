package models;

import javafx.scene.paint.Color;

/**
 * @author Jelmer Haarman
 */
@SuppressWarnings("DefaultFileTemplate")
public class PassHolderSpace extends Car{

    public static final Color COLOR = Color.BLACK;

    public PassHolderSpace() {
        double stayMinutes = Double.POSITIVE_INFINITY;
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    /**
     * Although the COLOR constant is public, we still need a getter since we're extending the Car class.
     */
    public Color getColor(){
        return COLOR;
    }
}
