package Models;

import javafx.scene.paint.*;

import java.awt.*;
import java.awt.Color;
import java.util.Random;

public class AdHocCar extends Car {
	private static final javafx.scene.paint.Color COLOR = javafx.scene.paint.Color.red;
	
    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }
    
    public javafx.scene.paint.Color getColor(){
    	return COLOR;
    }
}
