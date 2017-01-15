package views;

import controllers.Simulator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;

public class GUI extends Application {

    private Scene _mainScene;
    private BorderPane _mainLayout;
    private Simulator _simulator;
    private MenuBar _menu;

    public GUI() {
        NavBar navBar = new NavBar(this);
        _menu = navBar.generate();
        _simulator = new Simulator();
    }

    public static void GUI(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        _mainLayout = new BorderPane();

        _mainScene = new Scene(_mainLayout, 800, 500);

        _mainLayout.setTop(_menu);

        window.setScene(_mainScene);
        window.setTitle("Parkeergaragesimulator");
        window.show();
    }

    public void setCenter(BorderPane pane) {
        _mainLayout.setCenter(pane);
    }

    public int getWidth() {
        return (int) _mainScene.getWidth();
    }

    public int getHeight() {
        return (int) _mainScene.getHeight();
    }

    /**
     * Get the dimensions, in a separate method for readability purposes.
     * @return Dimension
     */
    public Dimension getDimensions() {
        return new Dimension(getWidth(), getHeight());
    }
}