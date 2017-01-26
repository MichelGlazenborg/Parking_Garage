package Controller;

import Models.Simulator;
import Models.SimulatorView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class Controller {

    @FXML
    private Canvas _canvas;

    //make simulator object
    private Simulator sim;
    private SimulatorView simView;

    @FXML
    private TextArea textTarget;

    public void initialize() {
        sim = new Simulator(_canvas);
        simView = sim.getSimulatorView();
    }

    @FXML
    private void closeApp(ActionEvent e) {
        System.exit(0);
    }

    @FXML
    private void tick1() {
        //call the simulator object to run for 1 tick
        sim.tick();
        setText("I should be running for 1 tick now");
    }

    @FXML
    private void makeReservations(){
        simView.makeReservations();
    }

    @FXML
    private void tick50() {
        //call simulator object to run for 50 ticks
        setText("I should be running for 50 ticks now");

        Timeline timeline = new Timeline();
        timeline.setCycleCount(50);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> {
            sim.tick();
        }));

        timeline.play();
    }

    @FXML
    private void tick1000() {
        //call the simulator object to run for 1000 ticks
        setText("I should be running for 1000 ticks now");

        Timeline timeline = new Timeline();
        timeline.setCycleCount(1000);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> {
            sim.tick();
        }));

        timeline.play();
    }

    @FXML
    private void tickFor(int ticks) {
        // call the simulator to run for an prompted amount of ticks
        setText("I should be running for "+ticks+" ticks now");

        Timeline timeline = new Timeline();
        timeline.setCycleCount(ticks);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> {
            sim.tick();
        }));

        timeline.play();

    }

    @FXML
    private void submit() {
        // Opening a pop-up dialog window to ask for the amount of ticks, converting it to integer and calling on tickFor
        setText("I should be opening a popup window now.");

        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle("Number Input Dialog");
        dialog.setContentText("Number of ticks:");
        Optional<String> result = dialog.showAndWait();

        // Checking if something was filled in. No answer does nothing.
        if (result.isPresent()){
            // Turns Optional<String> into a normal String
            String result2 = result.get();
            // Parses a integer from a String and tries to catch errors.
            int ticksAmount = -1;
            try {
                ticksAmount = Integer.parseInt(result2);
            } catch(NumberFormatException exception) {
                setText("Please enter an positive whole number!");
            } finally {
                if(ticksAmount < 1) {
                    setText("Please enter an positive whole number!");
                } else {
                    tickFor(ticksAmount);
                }
            }
        }
    }

    @FXML
    private void showAbout() {
        //show about information
        setText("Parking Simulator is a program that lets city parking Groningen see how some changes to their Parking Garage might affect business.");
    }

    private void setText(String txt) {
        textTarget.setText(txt);
    }


    /*private Rectangle car(Object car) {
        int floor;
        int row;
        int place;
        int x=0;
        int y=0;
        if(car.getClass().isInstance(AdHocCar.class)) {
            Location loc = ((AdHocCar) car).getLocation();
            floor = loc.getFloor();
            row = loc.getRow();
            place = loc.getPlace();
            Color col = ((AdHocCar) car).getColor();
            for(int i=0; i<sim.getNumberOfFloors(); i++) {
                if(floor == i) {
                    y = 75 * i;
                    //draw side rectangle at y t/m y + 40
                }
            }
            for(int i=0; i<sim.getNumberOfRows(); i++) {
                if(row == i) {
                    x = 100 + (100 * i);
                    // starting point x is between x and x + 50
                }
            }
            for(int i=0; i<sim.getNumberOfPlaces(); i++) {
                if(place == i) {
                    if(i%2 == 0) {
                        x += 50;
                        // draw roof rectangle at x t/m x + 50
                    }
                }
            }
        } else if(car.getClass().isInstance(ParkingPassCar.class)) {
            Location loc = ((ParkingPassCar) car).getLocation();
            floor = loc.getFloor();
            row = loc.getRow();
            place = loc.getPlace();
            Color col = ((ParkingPassCar) car).getColor();
            for(int i=0; i<sim.getNumberOfFloors(); i++) {
                if(floor == i) {
                    y = 75 * i;
                    //set side rectangle at y t/m y + 40
                }
            }
            for(int i=0; i<sim.getNumberOfRows(); i++) {
                if(row == i) {
                    x = 100 + (100 * i);
                    // starting point x is between x and x + 50

                }
            }
            for(int i=0; i<sim.getNumberOfPlaces(); i++) {
                if(place == i) {
                    if(i%2 == 0) {
                        x += 50;
                        // set roof rectangle at x t/m x + 50
                    }
                }
            }

        }
        return null;
    }*/
}
