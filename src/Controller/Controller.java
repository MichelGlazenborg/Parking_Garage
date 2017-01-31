package Controller;

import Models.Location;
import Models.Simulator;
import Models.SimulatorView;
import Models.StatsPie;
import View.StatsGraph;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Optional;

public class Controller {


    //make simulator object
	private Simulator sim;
    private SimulatorView simView;

    private StatsGraph _statsGraph;
    private StatsPie _statsPie;

    @FXML
    private VBox _sidebarRight;

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
    private Button button_operate5;

    @FXML
    private Button button_operate6;

    @FXML
    private TextArea textTarget;

    @FXML
    private Timeline timeline;

    public void initialize() {
        sim = new Simulator(_canvas);
        simView = sim.getSimulatorView();

        _statsPie = new StatsPie();
        _statsGraph = new StatsGraph(_statsPie);

        _statsGraph.setData();
        _statsGraph.generate();
        _sidebarRight.getChildren().add(_statsGraph.getChart());
    }

    @FXML
    private void closeApp(ActionEvent e) {
        System.exit(0);
    }

    @FXML
    private void tick1() {
        //call the simulator object to run for 1 tick
        tickFor(1);
    }

    @FXML
    private void tick50() {
        //call simulator object to run for 50 ticks
        tickFor(50);
    }

    @FXML
    private void tick1000() {
        //call the simulator object to run for 1000 ticks
        tickFor(1000);
    }

    @FXML
    private void tickFor(int ticks) {
        setText("I should be running for " + ticks + " ticks now");
        disableButtons(true);

        timeline = new Timeline();
        timeline.setCycleCount(ticks);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> sim.tick()));

        timeline.play();
        timeline.setOnFinished(e -> {
            updateGraph();
            disableButtons(false);
        });
    }

    @FXML
    private void makePassHolderPlaces(){
        simView.makePassHolderPlaces();
    }

    @FXML
    private void MakePassHolderRows() {
        //setText("I should be opening a popup window now.");

        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle("Number Input Dialog");
        dialog.setContentText("Number of rows:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String result2 = result.get();
            // Parses a integer from a String and tries to catch errors.
            int rowAmount = 0;
            try {
                rowAmount = Integer.parseInt(result2);
            } catch(NumberFormatException exception) {
                setText("Please enter an positive whole number!");
            } finally {
                if(rowAmount < 1) {
                    setText("Please enter an positive whole number!");
                } else {
                    simView.makePassHolderRows(rowAmount);
                }
            }
        }
    }

    @FXML
    private void makeReservationsAt() {
        // make reservations at a prompted location
        int floor = insertFloor();
        int row = insertRow();
        int place = insertPlace();

        // illegal answers return -1
        if (floor == -1 || row == -1 || place == -1) {
            setText("One or more arguments were not filled in correctly!");
        } else {
            simView.makeReservationsAt(new Location(floor, row, place));
        }
    }
    @FXML
    private void setTime() {
        int week = givinWeek();
        int day = givinDay();
        int hour = givinHour();
        int minute = givinMinute();

        if(week == -1 || day == -1 || hour == -1 || minute == -1) {
            setText("Please enter positive numbers.");
        } else {
            sim.setTime(week,day,hour,minute);
        }
    }

    private  int givinWeek() {
        int week = -1;

        TextInputDialog WeekDialog = new TextInputDialog("0");
        WeekDialog.setTitle("Week Input Dialog");
        WeekDialog.setHeaderText("Please enter any week number Between 1 and 52");
        WeekDialog.setContentText("Week:");
        Optional<String> WeekResult = WeekDialog.showAndWait();
        if (WeekResult.isPresent()) {
            // Turns Optional<String> into a normal String
            String WeekResult2 = WeekResult.get();
            // Parses a integer from a String and tries to catch errors.
            try {
                week = Integer.parseInt(WeekResult2);
            } catch (NumberFormatException exception) {
                setText("Please enter an positive whole number!");
            } finally {
                if (week <= 0) {
                    setText("Please enter an positive whole number bigger than 0.");
                } else {
                    // check if the entered integer is between bounds
                    if (week <= 52) {
                        return (week - 1);
                    } else {
                        return (-1);
                    }
                }
            }
        }
        // if no acceptable input was found, this will return -1 and stop the method
        return (week);
    }

    private  int givinDay() {
        int day = -1;

        TextInputDialog DayDialog = new TextInputDialog("0");
        DayDialog.setTitle("Day Input Dialog");
        DayDialog.setHeaderText("Please enter any day number Between 1 and 7");
        DayDialog.setContentText("Day:");
        Optional<String> DayResult = DayDialog.showAndWait();
        if (DayResult.isPresent()) {
            // Turns Optional<String> into a normal String
            String DayResult2 = DayResult.get();
            // Parses a integer from a String and tries to catch errors.
            try {
                day = Integer.parseInt(DayResult2);
            } catch (NumberFormatException exception) {
                setText("Please enter an positive whole number!");
            } finally {
                if (day <= 0) {
                    setText("Please enter an positive whole number bigger than 0.");
                } else {
                    // check if the entered integer is between bounds
                    if (day <= 7) {
                        return(day - 1);
                    } else {
                        return(-1);
                    }
                }
            }
        }
        // if no acceptable input was found, this will return -1 and stop the method
        return(day);
    }

    private  int givinHour() {
        int hour = -1;

        TextInputDialog HourDialog = new TextInputDialog("0");
        HourDialog.setTitle("Hour Input Dialog");
        HourDialog.setHeaderText("Please enter any Hour between 1 and 24");
        HourDialog.setContentText("Hour:");
        Optional<String> HourResult = HourDialog.showAndWait();
        if (HourResult.isPresent()) {
            // Turns Optional<String> into a normal String
            String HourResult2 = HourResult.get();
            // Parses a integer from a String and tries to catch errors.
            try {
                hour = Integer.parseInt(HourResult2);
            } catch (NumberFormatException exception) {
                setText("Please enter an positive whole number!");
            } finally {
                if (hour <= 0) {
                    setText("Please enter an positive whole number bigger than 0.");
                } else {
                    // check if the entered integer is between bounds
                    if (hour <= 24) {
                        return(hour - 1);
                    } else {
                        return(-1);
                    }
                }
            }
        }
        // if no acceptable input was found, this will return -1 and stop the method
        return(hour);
    }

    private  int givinMinute() {
        int minute = -1;

        TextInputDialog MinuteDialog = new TextInputDialog("0");
        MinuteDialog.setTitle("Minute Input Dialog");
        MinuteDialog.setHeaderText("Please enter any minute between 1 and 60");
        MinuteDialog.setContentText("Minute:");
        Optional<String> MinuteResult = MinuteDialog.showAndWait();
        if (MinuteResult.isPresent()) {
            // Turns Optional<String> into a normal String
            String MinuteResult2 = MinuteResult.get();
            // Parses a integer from a String and tries to catch errors.
            try {
                minute = Integer.parseInt(MinuteResult2);
            } catch (NumberFormatException exception) {
                setText("Please enter an positive whole number!");
            } finally {
                if (minute <= 0) {
                    setText("Please enter an positive whole number bigger than 0.");
                } else {
                    // check if the entered integer is between bounds
                    if (minute <= 60) {
                        return(minute - 1);
                    } else {
                        return(-1);
                    }
                }
            }
        }
        // if no acceptable input was found, this will return -1 and stop the method
        return(minute);
    }


    private int insertFloor() {
        // input a floor
        int floor = -1;

        TextInputDialog floorDialog = new TextInputDialog("0");
        floorDialog.setTitle("Floor Input Dialog");
        floorDialog.setHeaderText("Please enter the floor number for your reservation below. Between 0 and " + (simView.getNumberOfFloors() - 1));
        floorDialog.setContentText("Floor:");
        Optional<String> floorResult = floorDialog.showAndWait();

        if (floorResult.isPresent()) {
            // Turns Optional<String> into a normal String
            String floorResult2 = floorResult.get();
            // Parses a integer from a String and tries to catch errors.
            try {
                floor = Integer.parseInt(floorResult2);
            } catch (NumberFormatException exception) {
                setText("Please enter an positive whole number!");
            } finally {
                if (floor < 0) {
                    setText("Please enter an positive whole number!");
                } else {
                    // check if the entered integer is actually in this garage
                    if (floor < simView.getNumberOfFloors()) {
                        return(floor);
                    } else {
                        return(-1);
                    }
                }
            }
        }
        // if no acceptable input was found, this will return -1 and stop the method
        return(floor);
    }

    private int insertRow() {
        // input a row
        int row = -1;

        TextInputDialog rowDialog = new TextInputDialog("0");
        rowDialog.setTitle("Row Input Dialog");
        rowDialog.setHeaderText("Please enter the row number for your reservation below. Between 0 and " + (simView.getNumberOfRows() - 1));
        rowDialog.setContentText("Row:");
        Optional<String> rowResult = rowDialog.showAndWait();

        if (rowResult.isPresent()) {
            // Turns Optional<String> into a normal String
            String rowResult2 = rowResult.get();
            // Parses a integer from a String and tries to catch errors.
            try {
                row = Integer.parseInt(rowResult2);
            } catch (NumberFormatException exception) {
                setText("Please enter an positive whole number!");
            } finally {
                if (row < 0) {
                    setText("Please enter an positive whole number!");
                } else {
                    // check if the entered integer is actually in this garage
                    if (row < simView.getNumberOfRows()) {
                        return(row);
                    } else {
                        return(-1);
                    }
                }
            }
        }
        // if no acceptable input was found, this will return -1 and stop the method
        return(row);
    }



    private int insertPlace() {
        // input a place
        int place = -1;

        TextInputDialog placeDialog = new TextInputDialog("0");
        placeDialog.setTitle("Place Input Dialog");
        placeDialog.setHeaderText("Please enter the floor number for your reservation below. Between 0 and " + (simView.getNumberOfPlaces() - 1));
        placeDialog.setContentText("Place:");
        Optional<String> placeResult = placeDialog.showAndWait();

        if (placeResult.isPresent()) {
            // Turns Optional<String> into a normal String
            String placeResult2 = placeResult.get();
            // Parses a integer from a String and tries to catch errors.
            try {
                place = Integer.parseInt(placeResult2);
            } catch (NumberFormatException exception) {
                setText("Please enter an positive whole number!");
            } finally {
                if (place < 0) {
                    setText("Please enter an positive whole number!");
                } else {
                    // check if the entered integer is actually in this garage
                    if (place < simView.getNumberOfPlaces()) {
                        return (place);
                    } else {
                        return (-1);
                    }
                }
            }
        }
        // if no acceptable input was found, this will return -1 and stop the method
        return (place);
    }

    @FXML
    private void submit() {
        // Opening a pop-up dialog window to ask for the amount of ticks, converting it to integer and calling on tickFor
        setText("I should be opening a popup window now.");

        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle("Number Input Dialog");
        dialog.setHeaderText("Please enter the amount of ticks this program should be running for below.");
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
    private void getTime(){
        int[] time = sim.getTime();
        String day = "";
        switch (time[2]) {
            case 0 : {
                day = "Monday";
                break;
            }
            case 1 : {
                day = "Thuesday";
                break;
            }
            case 2 : {
                day = "Wednesday";
                break;
            }
            case 3 : {
                day = "Thursday";
                break;
            }
            case 4 : {
                day = "Friday";
                break;
            }
            case 5 : {
                day = "Saturday";
                break;
            }
            case 6 : {
                day = "Zondag";
                break;
            }
        }
        setText("Week " + time[3] + " " + day + " Hour " + time[1] + " Minute " + time[0] );
    }



    @FXML
    private void reset() {
        // resets all parking spots to empty on click
        setText("I should be removing cars now.");
        sim.resetRevenue();
        sim.resetTime();
        _statsPie.reset();
        simView.reset();
        setText("All cars should be gone now");
        button_operate6.setDisable(true);
    }

    @FXML
    private void getRevenue(){
        setText("The total revenue since the start is: €" + sim.getRevenue());
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
        button_operate5.setDisable(!doDisable);
        button_operate6.setDisable(doDisable);
    }

    @FXML
    private void stop() {
        if (timeline != null) {
            timeline.stop();
            disableButtons(false);
        }
    }

    private void updateGraph() {
        _statsPie.update(
            simView.getNumberOfFloors() * simView.getNumberOfRows() * simView.getNumberOfPlaces(),
            simView.getNumberOfOpenSpots(),
            simView.getNumberOfPassHolders(),
            simView.getNumberOfAdHoc(),
            simView.getNumberOfCarsWithReservation()
        );

        _statsGraph.setData();
    }
}
