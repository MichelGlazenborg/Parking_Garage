import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/Parkeergarage.fxml"));
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Parking simulator");
        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.setScene(scene);
        scene.getStylesheets().add("style.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
