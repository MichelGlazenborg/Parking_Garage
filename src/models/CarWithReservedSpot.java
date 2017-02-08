package models;

import java.util.Random;

import javafx.scene.paint.Color;

/**
 * This class is used for storing cars with a reservation
 * @author  MATA
 * @since   1.0
 */
public class CarWithReservedSpot extends Car {

    /**
     * This color will be used by the simulator to display the car.
     */
    private static final Color COLOR = Color.YELLOW;

    /**
     * Creates a new car object
     */
    public CarWithReservedSpot() {
        Random rand = new Random();
        int stayMinutes = (int) (80 + rand.nextFloat() * 3 * 60);

        this.setStayMinutes(stayMinutes);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
        this.setHasReservation(true);
    }

    /**
     * @return  color   The car color
     */
    public Color getColor(){
        return COLOR;
    }
}
