package Controller;

import Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {
    //make simulator object
    Simulator sim = new Simulator();

    @FXML
    private TextArea textTarget;

    public void initialize() {
        System.out.println("Test");
    }

    @FXML
    private void closeApp(ActionEvent e) {
        System.exit(0);
    }

    @FXML
    private void tick50() {
        //call simulator object to run for 50 ticks
        for(int i=0;i<50;i++) {
            sim.run();
        }
        setText("I should be running for 50 ticks now");
    }

    @FXML
    private void tick1() {
        //call the simulator object to run for 1 tick
        sim.run();
        setText("I should be running for 1 tick now");
    }

    @FXML
    private void tick1000() {
        //call the simulator object to run indefinitely
        for(int i=0;i<1000;i++) {
            sim.run();
        }
        setText("I should just be running now");
    }

    @FXML
    private void showAbout() {
        //show about information
        setText("Parking Simulator is a program that lets city parking Groningen see how some changes to their Parking Garage might affect business.");
    }

    private void setText(String txt) {
        textTarget.setText(txt);
    }

    @FXML
    private Rectangle car(Object car) {
        int floor;
        int row;
        int place;
        if(car.getClass().isInstance(AdHocCar.class)) {
            Location loc = ((AdHocCar) car).getLocation();
            floor = loc.getFloor();
            row = loc.getRow();
            place = loc.getPlace();
            Color col = ((AdHocCar) car).getColor();
        } else if(car.getClass().isInstance(ParkingPassCar.class)) {
            Location loc = ((ParkingPassCar) car).getLocation();
            floor = loc.getFloor();
            row = loc.getRow();
            place = loc.getPlace();
            Color col = ((ParkingPassCar) car).getColor();
        }
        return null;
    }
}
