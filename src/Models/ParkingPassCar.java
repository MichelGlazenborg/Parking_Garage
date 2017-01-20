package Models;

import java.util.Random;

public class ParkingPassCar extends Car {
	private static final javafx.scene.paint.Color COLOR= javafx.scene.paint.Color.BLUE;
	
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    public javafx.scene.paint.Color getColor(){
    	return COLOR;
    }
}
