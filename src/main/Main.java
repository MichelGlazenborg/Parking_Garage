package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Creates the stage for the program
     * @param primaryStage = the stage the program is build on
     * @throws Exception = makes sure the program starts up safely
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/ParkingGarage.fxml"));

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Parking simulator");

        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.setScene(scene);
        scene.getStylesheets().add("style.css");

        primaryStage.show();
    }


    /**
     * The main starting point for the program
     */
    public static void main(String[] args) {
        launch(args);
    }
}
