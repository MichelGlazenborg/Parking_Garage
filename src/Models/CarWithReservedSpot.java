package Models;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Created by Jelmer on 26-Jan-17.
 */
public class CarWithReservedSpot extends Car {
    private int entryCode;
    private static final Color COLOR = Color.GREEN;

    public CarWithReservedSpot(int entryCode) {
        this.entryCode = entryCode;
        Random rand = new Random();
        int stayMinutes = (int) (20 + rand.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    public Color getColor(){
        return COLOR;
    }

    public int getEntryCode(){
        return entryCode;
    }
}
