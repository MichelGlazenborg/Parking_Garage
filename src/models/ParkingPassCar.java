package models;

import java.util.Random;

import javafx.scene.paint.Color;

public class ParkingPassCar extends Car {

	private static final Color COLOR = Color.BLUE;
	
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (120 + random.nextFloat() * 3 * 60);

        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    public Color getColor(){
    	return COLOR;
    }
}
