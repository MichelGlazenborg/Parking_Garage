package controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import views.NavBar;

import java.awt.*;

public class GUI extends Application {

    private Scene _mainScene;
    private BorderPane _mainLayout;
    private Simulator _simulator;

    private NavBar _navBar;
    private MenuBar _menu;

    public GUI() {
        _mainLayout = new BorderPane();
    }

    public static void GUI(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        _mainScene = new Scene(_mainLayout, 800, 500);
        _simulator = new Simulator(this);
        _navBar = new NavBar();

        _menu = _navBar.generate();
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