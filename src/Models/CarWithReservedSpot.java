package Models;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Created by Jelmer on 26-Jan-17.
 */
public class CarWithReservedSpot extends Car {
    private Location reservedSpot;
    private static final Color COLOR = Color.GREEN;

    public CarWithReservedSpot(Location reservation) {
        reservedSpot = reservation;
        Random rand = new Random();
        int stayMinutes = (int) (20 + rand.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    public Color getColor(){
        return COLOR;
    }

    public Location getReservedSpot(){
        return reservedSpot;
    }
}
