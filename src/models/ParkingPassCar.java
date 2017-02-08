package models;

import javafx.scene.paint.Color;

import java.util.Random;

public class ParkingPassCar extends Car {

	private static final Color COLOR = Color.BLUE;  // The color of the car

    /**
     * Constructor for the parkingpass car, sets the amount of time the car will be in the parking garage.
     */
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (120 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    /**
     * Get the color of the car
     * @return The color of the car
     */
    public Color getColor(){
    	return COLOR;
    }
}
