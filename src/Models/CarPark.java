package Models;

import View.CarParkView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class CarPark {

    private CarParkView _carParkView;
    private GraphicsContext _graphicsContext;

    public CarPark(Canvas canvas) {
        _graphicsContext = canvas.getGraphicsContext2D();
        _carParkView = new CarParkView(_graphicsContext);
    }

    public void update() {
        _carParkView.updateView();
    }
}