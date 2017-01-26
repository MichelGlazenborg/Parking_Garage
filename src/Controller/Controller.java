package Controller;

import Models.Location;
import Models.Simulator;
import Models.SimulatorView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;

import java.util.Optional;
import java.util.Random;

public class Controller {

	//make simulator object
	private Simulator sim;
    private SimulatorView simView;

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
        simView = sim.getSimulatorView();
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
    private void makePassHolderPlaces(){
        simView.makePassHolderPlaces();
    }

    @FXML
    private void makeReservationsAt(){
        Random rand = new Random();
        int floor = rand.nextInt(2);
        if(floor == 0) {
            floor = 1;
        }
        simView.makeReservationsAt(new Location(floor,rand.nextInt(2),rand.nextInt(30)));
        //simView.makeReservationsAt(new Location(2,2,30));
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

            setText("I should be running for" + ticks + "now");

        timeline.play();
        timeline.setOnFinished(e -> disableButtons(false));
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

    private void disableButtons(boolean doDisable) {
        button_operate1.setDisable(doDisable);
        button_operate2.setDisable(doDisable);
        button_operate3.setDisable(doDisable);
        button_operate4.setDisable(doDisable);
    }

}
