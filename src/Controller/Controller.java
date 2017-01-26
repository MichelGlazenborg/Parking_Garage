package Controller;

import Models.Simulator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

public class Controller {

	//make simulator object
	private Simulator sim;

    @FXML
    private Canvas _canvas;

    @FXML
    private Button button_operate1;

    @FXML
	private Button button_operate2;

	@FXML
	private Button button_operate3;

	@FXML
	private Button button_operate4;

    @FXML
    private TextArea textTarget;

    public void initialize() {
        sim = new Simulator(_canvas);
    }

    @FXML
    private void closeApp(ActionEvent e) {
        System.exit(0);
    }

    @FXML
    private void tick1() {
        //call the simulator object to run for 1 tick
        disableButtons(true);
        sim.tick();
        setText("I should be running for 1 tick now");
        disableButtons(false);
    }

    @FXML
    private void tick50() {
        //call simulator object to run for 50 ticks
        setText("I should be running for 50 ticks now");

        disableButtons(true);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(50);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> sim.tick()));

        timeline.play();
		timeline.setOnFinished(e -> disableButtons(false));
    }

    @FXML
    private void tick1000() {
        //call the simulator object to run for 1000 ticks
        setText("I should be running for 1000 ticks now");

        disableButtons(true);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1000);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> sim.tick()));

        timeline.play();
        timeline.setOnFinished(e -> disableButtons(false));
    }

    @FXML
    private void tickFor(int ticks) {
        disableButtons(true);

		Timeline timeline = new Timeline();
		timeline.setCycleCount(ticks);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> sim.tick()));

        setText("I should be running for"+ticks+"now");

		timeline.play();
		timeline.setOnFinished(e -> disableButtons(false));
    }

    @FXML
    private void submit() {
        setText("I should be opening a popup window now.");
    }

    @FXML
    private void showAbout() {
        //show about information
        setText("Parking Simulator is a program that lets city parking Groningen see how some changes to their Parking Garage might affect business.");
    }

    private void setText(String txt) {
        textTarget.setText(txt);
    }

    private void disableButtons(boolean doDisable) {
        button_operate1.setDisable(doDisable);
        button_operate2.setDisable(doDisable);
        button_operate3.setDisable(doDisable);
        button_operate4.setDisable(doDisable);
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
