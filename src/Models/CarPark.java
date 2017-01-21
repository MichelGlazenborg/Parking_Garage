package Models;

import View.CarParkView;
import javafx.scene.canvas.GraphicsContext;

public class CarPark {

    private CarParkView _carParkView;
    private GraphicsContext _graphicsContext;

    public CarPark() {
        _carParkView = new CarParkView();
        //_graphicsContext = graphicsContext;
    }

    public void update() {
        _carParkView.updateView();
    }
}