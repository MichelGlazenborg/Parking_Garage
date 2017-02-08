package models;

import javafx.scene.paint.Color;

public class PassHolderSpace extends Car{

    public static final Color COLOR = Color.BLACK;      //The color of the car

    /**
     * The constructor for the passholderspot, This "car" will stay indefinitely
     */
    public PassHolderSpace() {
        double stayMinutes = Double.POSITIVE_INFINITY;
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    /**
     * Gets the color of the car
     * @Return The color of the car/
     */
    public Color getColor(){
        return COLOR;
    }
}
