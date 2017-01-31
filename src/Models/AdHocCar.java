package Models;

import javafx.scene.paint.Color;

import java.util.Random;

public class AdHocCar extends Car {

	private static final Color COLOR = Color.RED;

	
    public AdHocCar() {
    	Random random = new Random();
    	int time = (int) (15 + random.nextFloat() * 3 * 60);
    	this.setStayMinutes(time);
        this.setMinutesLeft(time);
        this.setHasToPay(true);
        this.setHasReservation(false);

    }
    
    public Color getColor(){
    	return COLOR;
    }


}
