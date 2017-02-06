package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import models.Car;
import models.Garage;
import models.Location;

public class GarageView {

    private GraphicsContext _graphicsContext;
    private Canvas _canvas;
    private Image _canvasBackground;
    private Garage _garage;

    public GarageView(Canvas canvas, Garage garage) {
        _canvas = canvas;
        _canvasBackground = new Image(GarageView.class.getResourceAsStream("/assets/canvasbackground.jpg"));

        _garage = garage;

        _graphicsContext = canvas.getGraphicsContext2D();
        _graphicsContext.drawImage(_canvasBackground, 0, 0, 560, 335);
    }

    public void update() {
        _graphicsContext.clearRect(0, 0, _canvas.getWidth(), _canvas.getHeight());

        for (int floor = 0; floor < _garage.getNumberOfFloors(); floor++) {
            for (int row = 0; row < _garage.getNumberOfRows(); row++) {
                for (int place = 0; place < _garage.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = _garage.getCarAt(location);
                    Color color = (car == null ? Color.WHITE : car.getColor());
                    drawParkingSpot(location, color);
                }
            }
        }
    }

    private void drawParkingSpot(Location location, Color color) {
        _graphicsContext.setFill(color);
        _graphicsContext.fillRect(
                (location.getFloor() * 200 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 55 + (location.getRow() % 2) * 20) - 50,
                30 + location.getPlace() * 10,
                20 - 1,
                10 - 1
        );
    }
}