package controller;

import java.util.Optional;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import models.DailyCarsChart;
import models.Garage;
import models.OccupationChart;
import models.Simulator;

import view.DailyCarsChartView;
import view.OccupationChartView;

public class Controller {

    private static final String version = "1.0";

	private Simulator _sim;              //makes the central simulator object
    private Garage _garage;      //makes the central simulatorView object

    private OccupationChartView _occupationChartView;     //makes the statistics graph
    private OccupationChart _statsPie;         //makes the pie graph

    private DailyCarsChart _dailyCarsChart;
    private DailyCarsChartView _dailyCarsChartView;

    private boolean _willShowStats;

    private double _speed = 1;

    private int _remainingTicks;

    private boolean _boolRev = true;
    private boolean _boolGraph = true;
    private boolean _boolTime = true;

    private PieChart _object;

    private Timeline _timeline;          //makes the timeline object
    private Timeline _timelineGraphs;

    @FXML private VBox statistics;

    @FXML private VBox sidebarRight;         //makes the sidebar on the right

    @FXML private Canvas canvas;             //makes the canvas where the garage will be drawn

    @FXML private Button button_operate1;     //makes button 1

    @FXML private Button button_operate2;     //makes button 2

    @FXML private Button button_operate3;     //makes button 3

    @FXML private Button button_operate4;     //makes button 4

    @FXML private Button button_operate5;     //makes button 5

    @FXML private Button button_operate6;     //makes button 6
  
    @FXML private Label titleTime;

    @FXML private Label date;                 //makes the label with the week and day

    @FXML private Label clock;                //makes the label with the hours and minutes
  
    @FXML private Label revenue;              //makes the label with the total revenue

    @FXML private Label dayRevenue;           //makes the label with the total day revenue

    @FXML private Label titleRev;

    /**
     * Initializes all the attributes
     */
    public void initialize() {
        _sim = new Simulator(canvas);
        _garage = _sim.getGarage();

        _statsPie = new OccupationChart();
        _dailyCarsChart = new DailyCarsChart();

        _occupationChartView = new OccupationChartView(_statsPie);
        _dailyCarsChartView = new DailyCarsChartView(_dailyCarsChart);

        _occupationChartView.setData();
        _dailyCarsChartView.setData();

        _object = _occupationChartView.getChart();

        sidebarRight.getChildren().add(_object);

        statistics.getChildren().add(_dailyCarsChartView.getChart());

        getDate();
        clock();
        getRevenue();
        getDayRevenue();
        _garage.setSpeed(_speed);
    }

    /**
     * Closes the app
     */
    @FXML
    private void closeApp() {
        System.exit(0);
    }

    /**
     * Makes the simulator tick once
     * Uses tickFor method
     */
    @FXML
    private void tick1() {
        //call the simulator object to run for 1 tick
        tickFor(1);
    }

    /**
     * Makes the simulator tick 60 times (1 hour)
     */
    @FXML
    private void tick60() {
        //call simulator object to run for 60 ticks
        tickFor(60);
    }

    /**
     * Makes the simulator tick 1440 times (1 day)
     */
    @FXML
    private void tickDay() {
        //call the simulator object to run for 1440 ticks
        tickFor(1440);
    }

    /**
     * Makes the simulator tick for any number of ticks
     * @param ticks The number of ticks the simulation should do
     */
    @FXML
    private void tickFor(int ticks) {
        disableButtons(true);
        _remainingTicks = ticks;
        _timeline = new Timeline();
        _timelineGraphs = new Timeline();
        _timeline.setCycleCount(ticks);
        _timelineGraphs.setCycleCount(ticks);
        _timeline.getKeyFrames().add(new KeyFrame(Duration.millis(Math.round(100 / _speed)), e -> {
            _sim.tick();
            clock();
            _remainingTicks = _remainingTicks - 1;
        }));

        _timelineGraphs.getKeyFrames().add(new KeyFrame(Duration.millis(Math.round(500)), e -> {
            update();
        }));

        _timeline.play();
        _timelineGraphs.play();

        _timeline.setOnFinished(e -> {
            _timelineGraphs.stop();
            update();

            if (_willShowStats)
                showStats();

            disableButtons(false);
        });
    }

    @FXML
    private void setSpeed(){
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Set Simulator Speed");
        dialog.setContentText("Speed of the simulation: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String result2 = result.get();
            // Parses a integer from a String and tries to catch errors.
            int a = 0;
            try {
                a = Integer.parseInt(result2);
            }
            catch(NumberFormatException exception) {
                showError();
            }
            finally {
                if (a <= 0) {
                    _speed = 1;
                    _garage.setSpeed(1);
                } else if (a > 100) {
                    _speed = 100;
                    _garage.setSpeed(100);
                } else {
                    _speed = a;
                    _garage.setSpeed(a);
                }
                //stop();
                //tickFor(_remainingTicks);
            }
        }
    }

    @FXML
    private void resetSpeed(){
        _speed = 1;
        _garage.setSpeed(1);
        //stop();
        //tickFor(_remainingTicks);
    }

    public static void showError() {
        Alert error = new Alert(Alert.AlertType.WARNING);
        error.setTitle("Input error");
        error.setHeaderText(null);
        error.setContentText("Please enter the correct information!");
        error.showAndWait();
    }

    /**
     * Opens a dialog that lets you enter an integer which corresponds to the number of placeholder spots that will be assigned
     */
    @FXML
    private void makePassHolderSpots() {
        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle("Number Input Dialog");
        dialog.setContentText("Number of spots:");
        Optional<String> result = dialog.showAndWait();

        boolean exceptionOccurred = false;
        if (result.isPresent()){
            String result2 = result.get();
            // Parses a integer from a String and tries to catch errors.
            int spotAmount = 0;
            try {
                spotAmount = Integer.parseInt(result2);
            }
            catch(NumberFormatException exception) {
                showError();
                exceptionOccurred = true;
            }
            finally {
                if(spotAmount < 1 && !exceptionOccurred)
                    showError();
                else {
                    stop();
                    reset();
                    _garage.makePassHolderSpots(spotAmount);
                }
            }
        }
    }

    /**
     * Opens a dialog which lets you enter a double. The price per minute of the parking garage will be set to that double
     */
    @FXML
    private void setPricePerMinute() {
        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle("Number Input Dialog");
        dialog.setContentText("Price per minute:");
        Optional<String> result = dialog.showAndWait();

        boolean exceptionOccurred = false;
        if (result.isPresent()){
            String result2 = result.get();
            // Parses a integer from a String and tries to catch errors.
            double priceAmount = 0;
            try {
                priceAmount = Double.parseDouble(result2);
            }
            catch(NumberFormatException exception) {
                showError();
                exceptionOccurred = true;
            }
            finally {
                if(priceAmount <= 0 && !exceptionOccurred)
                    showError();
                else
                    _sim.setCost(priceAmount);
            }
        }
    }

    /**
     * Sets the time by letting the user specify the week, day, hour and minute
     * uses givenWeek(), givenDay(), givenHour() and givenMinute()
     */
    @FXML
    private void setTime() {
        int[] dateAndTime = getTimeInput();

        // If the value of this is -1, it means the user pressed the close button
        if (dateAndTime[0] == -1)
            return;

        dateAndTime[1]--;

        if(dateAndTime[0] == -1 || dateAndTime[1] == -1 || dateAndTime[2] == -1 || dateAndTime[3] == -1)
            showError();
        else {
            _sim.setTime(dateAndTime[0], dateAndTime[1], dateAndTime[2], dateAndTime[3]);
            getDate();
            clock();
        }
    }

    private int[] getTimeInput() {
        TimeInputPopupController popup = new TimeInputPopupController();
        popup.show();

        return popup.getInput();
    }

    /**
     * Opens up a dialog that lets the user enter an integer to choose the amount of ticks the simulator must run
     */
    @FXML
    private void tickForDialog() {
        // Opening a pop-up dialog window to ask for the amount of ticks, converting it to integer and calling on tickFor
        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle("Minute Input Dialog");
        dialog.setHeaderText("Please enter the amount of minutes this program should be running for below.");
        dialog.setContentText("Number of minutes:");
        Optional<String> result = dialog.showAndWait();

        // Checking if something was filled in. No answer does nothing.
        boolean exceptionOccurred = false;
        if (result.isPresent()){
            // Turns Optional<String> into a normal String
            String result2 = result.get();
            // Parses a integer from a String and tries to catch errors.
            int ticksAmount = -1;
            try {
                ticksAmount = Integer.parseInt(result2);
            }
            catch (NumberFormatException exception) {
                showError();
                exceptionOccurred = true;
            }
            finally {
                if (ticksAmount < 1 && !exceptionOccurred)
                    showError();
                else if (!exceptionOccurred)
                    tickFor(ticksAmount);
            }
        }
    }

    /**
     * gets the current time from simulator, assigns the right day name to the day number and displays the date
     */
    @FXML
    private void getDate(){
        int[] time = _sim.getTime();
        String day = null;
        switch (time[2]) {
            case 0:
                day = "Monday";
                break;
            case 1:
                day = "Tuesday";
                break;
            case 2:
                day = "Wednesday";
                break;
            case 3:
                day = "Thursday";
                break;
            case 4:
                day = "Friday";
                break;
            case 5:
                day = "Saturday";
                break;
            case 6:
                day = "Sunday";
                break;
        }
        showDate("Week " + time[3] + "\nDay: " + day);
    }

    @FXML
    private String getStatistics() {
        int[] stats = _sim.getStatistics();
        return ("Number of regular cars: " + stats[1] +
                "\nNumber of cars with a reservation: " + stats[0] +
                "\nNumber of pass holder cars: " + stats[2] + "\n");
    }

    @FXML
    private String getQueueStats() {
        int[] queues = _sim.getQueues();
        return("\nNumber of cars in regular entrance queue: " + queues[0] +
                         "\nNumber of cars in pass holder entrance queue: " + queues[1] +
                         "\nNumber of cars in reservations entrance queue: " + queues[2] +
                         "\nNumber of cars in exit queue: " + queues[3] +
                         "\nNumber of cars in the payment queue: " + queues[4]);
    }

    private void showStats() {
        Alert stats = new Alert(Alert.AlertType.INFORMATION);
        stats.setWidth(500);
        stats.setHeight(750);
        stats.setHeaderText(null);
        stats.setTitle("Detailed statistics");
        stats.setContentText(getStatistics() + getQueueStats());

        stats.show();
    }

    @FXML
    private void setWillShowStats() {
        if (_willShowStats) {
           _willShowStats = false;
        } else {
            _willShowStats = true;
        }
    }

    /**
     * gets the current date from the simulator and displays the hours and minutes in digital clock form
     */
    @FXML
    private void clock(){
        int time[] = _sim.getTime();
        String hours;
        String minutes;

        if (time[1] < 10)
            hours = "0" + time[1];
        else
            hours = "" + time[1];

        if (time[0] < 10)
            minutes = "0" + time[0];
        else
            minutes = "" +  time[0];

        clock.setText("CLOCK" + "\n" + hours + " " + minutes);
    }

    /**
     * Resets the simulation
     */
    @FXML
    private void reset() {
        // resets all parking spots to empty on click
        _sim.resetRevenue();
        _sim.resetStats();
        _sim.resetTime();

        _statsPie.reset();
        _dailyCarsChart.reset();
        _garage.reset();

        _sim.resetArrivalCounter();
        updateGraph();

        getDate();
        clock();
        getRevenue();
        getDayRevenue();
        getStatistics();
        getQueueStats();
        button_operate6.setDisable(true);
    }

    /**
     * Gets the total revenue from the simulation and displays it
     */
    @FXML
    private void getRevenue(){
        showRevenue("The total revenue since the start is:\n€" + _sim.getRevenue() + "\n\n" +
                    "The expected revenue of all the cars\n still in the garage is:\n€" + _sim.getExpectedRevenue() + "\n");
    }

    @FXML
    private void getDayRevenue(){
        showDayRevenue("The total daily revenue of yesterday is:\n€" + _sim.getDayRevenue() + "\n\n");
    }

    /**
     * Opens up a text dialog that displays program information to the user
     */
    @FXML
    private void showAbout() {
        //show about information
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setWidth(100);
        alert.setHeaderText(null);
        alert.setTitle("About Parking Garage");
        alert.setContentText("Parking Simulator is a program that lets city parking Groningen see how some changes to their Parking Garage might affect business.\n\n" +
                             "It was developed by: Robert Monden, Job Wilts, Michel Glazenborg, Willem Slager en Jelmer Haarman.\n\n" +
                             "Version " + version);

        alert.showAndWait();
    }

    /**
     * sets the text in the label date
     * @param dateAsString:    A string that will be shown in date
     */
    private void showDate(String dateAsString) {
        date.setText(dateAsString);
    }

    /**
     * sets the text in the label revenue
     * @param r:    A string that will be shown in revenue
     */
    private void showRevenue(String r) {
        revenue.setText(r);
    }

    private void showDayRevenue(String r) {
        dayRevenue.setText(r);
    }

    /**
     * Disables or enables the buttons
     * @param doDisable:    A boolean that dictates to whether dis- or enable a button
     */
    private void disableButtons(boolean doDisable) {
        button_operate1.setDisable(doDisable);
        button_operate2.setDisable(doDisable);
        button_operate3.setDisable(doDisable);
        button_operate4.setDisable(doDisable);
        button_operate5.setDisable(!doDisable);
        button_operate6.setDisable(doDisable);
    }

    /**
     * Stops the current running simulation.
     */
    @FXML
    private void stop() {
        if (_timeline != null || _timelineGraphs != null) {
            _timeline.stop();
            _timelineGraphs.stop();
            update();
            if (_willShowStats) {
                showStats();
            }
            disableButtons(false);
        }
    }

    /**
     * updates the statistics graph
     */
    private void updateGraph() {
        _statsPie.update(
            _garage.getNumberOfFloors() * _garage.getNumberOfRows() * _garage.getNumberOfPlaces(),
            _garage.getNumberOfOpenSpots(),
            _garage.getNumberOfPassHolders(),
            _garage.getNumberOfAdHoc(),
            _garage.getNumberOfCarsWithReservation()
        );

        _dailyCarsChart.update(
            _sim.getArrivalsOnMonday(),
            _sim.getArrivalsOnTuesday(),
            _sim.getArrivalsOnWednesday(),
            _sim.getArrivalsOnThursday(),
            _sim.getArrivalsOnFriday(),
            _sim.getArrivalsOnSaturday(),
            _sim.getArrivalsOnSunday()
        );

        _occupationChartView.update();
        _dailyCarsChartView.update();
    }

    private void update() {
        getDate();
        getRevenue();
        getDayRevenue();
        getQueueStats();
        getStatistics();
        updateGraph();
    }

    @FXML
    private void setManageGraph() {
        statistics.setManaged(!_boolGraph);
        statistics.setVisible(!_boolGraph);
        this._boolGraph = !_boolGraph;
    }

    @FXML
    private void setManageRevenue() {
        setVisible(titleRev, _boolRev);
        setVisible(dayRevenue, _boolRev);
        setVisible(revenue, _boolRev);
        this._boolRev = !_boolRev;
    }

    @FXML
    private void setManageChart() {
        if (sidebarRight.getChildren().contains(_object))
            sidebarRight.getChildren().remove(_object);
        else
            sidebarRight.getChildren().add(_object);
    }

    @FXML
    private void setManageTime() {
        setVisible(titleTime, _boolTime);
        setVisible(date, _boolTime);
        setVisible(clock, _boolTime);
        this._boolTime = !_boolTime;
    }

    @FXML
    private void playSound() {
        if (_garage.getPlaySound()) {
            _garage.setPlaySound(false);
        } else {
            _garage.setPlaySound(true);
        }
    }

    private void setVisible(Node node, boolean bool) {
        node.setManaged(!bool);
        node.setVisible(!bool);
    }
}
