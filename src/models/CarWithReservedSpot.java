package models;

import javafx.scene.paint.Color;
import java.util.Random;

public class CarWithReservedSpot extends Car {

    private static final Color COLOR = Color.YELLOW;

    public CarWithReservedSpot() {
        Random rand = new Random();
        int stayMinutes = (int) (80 + rand.nextFloat() * 3 * 60);

        this.setStayMinutes(stayMinutes);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
        this.setHasReservation(true);
    }

    public Color getColor(){
        return COLOR;
    }
}
