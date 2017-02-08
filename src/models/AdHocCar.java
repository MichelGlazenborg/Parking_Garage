package models;

import java.util.Random;

import javafx.scene.paint.Color;

/**
 * This class is used for storing regular cars (i.e. cars not owned by pass holders).
 * @author  MATA
 * @since   1.0
 */
public class AdHocCar extends Car {

    /**
     * The car color
     */
    private static final Color COLOR = Color.RED;

    /**
     * This class creates a new AdHoc car object.
     */
    public AdHocCar() {
    	Random random = new Random();
    	int time = ((random.nextInt(10 - 2) + 1 ) + 2) * 60;

    	this.setStayMinutes(time);
        this.setMinutesLeft(time);
        this.setHasToPay(true);
        this.setHasReservation(false);
    }

    /**
     * This getter returns the value of COLOR. Although this method is not used anywhere, we still
     * need this since we're extending the Car class, which requires us to have it in here.
     * @return  Color   The value of COLOR
     */
    public Color getColor(){
    	return COLOR;
    }
}
