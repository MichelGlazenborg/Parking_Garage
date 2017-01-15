package views;

import controllers.Car;
import controllers.Location;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

import java.awt.*;
import java.awt.Color;

public class SimulatorView extends GUI {

    private CarParkView _carParkView;
    private int _numberOfFloors;
    private int _numberOfRows;
    private int _numberOfPlaces;
    private int _numberOfOpenSpots;
    private Car[][][] _cars;
    private BorderPane _pane;

    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        _numberOfFloors = numberOfFloors;
        _numberOfRows = numberOfRows;
        _numberOfPlaces = numberOfPlaces;
        _numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        _cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        _carParkView = new CarParkView();
        _carParkView.updateView();

        _pane = new BorderPane();

        super.setCenter(_pane);
    }

    public void updateView() {
        _carParkView.updateView();
    }
    
	public int getNumberOfFloors() {
        return _numberOfFloors;
    }

    public int getNumberOfRows() {
        return _numberOfRows;
    }

    public int getNumberOfPlaces() {
        return _numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
    	return _numberOfOpenSpots;
    }
    
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return _cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            _cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            _numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        _cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        _numberOfOpenSpots++;
        return car;
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= _numberOfFloors || row < 0 || row > _numberOfRows || place < 0 || place > _numberOfPlaces) {
            return false;
        }
        return true;
    }
    
    private class CarParkView {
        
        private Dimension _size;
        private Canvas _canvas;
        private Image _image;
        private GraphicsContext _graphicsContext;
    
        /**
         * Constructor for objects of class CarPark
         */
        public CarParkView() {
            _size = new Dimension(0, 0);
            _graphicsContext = _canvas.getGraphicsContext2D();
        }
    
        /**
         * Overridden. Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(800, 500);
        }
    
        /**
         * Overriden. The car park view component needs to be redisplayed. Copy the
         * internal image to screen.
         *
        public void paintComponent(Graphics g) {
            if (_canvas == null) {
                return;
            }

            Dimension currentSize = super.getDimensions();
            if (_size.equals(currentSize)) {
                _graphicsContext.drawImage(_graphicsContext, 0, 0, null);
            }
            else {
                // Rescale the previous image.
                _graphicsContext.drawImage(_canvas, 0, 0, currentSize.width, currentSize.height, null);
            }
        }*/
    
        public void updateView() {/*
            // Create a new car park image if the size has changed.
            if (!_size.equals(super.getDimensions())) {
                _canvas = new Canvas(super.getWidth(), super.getHeight());
            }
            Graphics graphics = _canvas.getGraphics();
            for(int floor = 0; floor < getNumberOfFloors(); floor++) {
                for(int row = 0; row < getNumberOfRows(); row++) {
                    for(int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        Color color = car == null ? Color.white : car.getColor();
                        drawPlace(graphics, location, color);
                    }
                }
            }
            repaint();*/
        }
    
        /**
         * Paint a place on this car park view in a given color.
         */
        private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }
    }
}