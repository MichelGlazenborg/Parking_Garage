package models;

import javafx.scene.paint.Color;

import java.util.Random;

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
