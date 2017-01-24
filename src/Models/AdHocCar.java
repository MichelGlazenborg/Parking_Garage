package Models;

import javafx.scene.paint.Color;

import java.util.Random;

public class AdHocCar extends Car {

	private static final Color COLOR = Color.RED;
	
    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }
    
    public Color getColor(){
    	return COLOR;
    }
}
