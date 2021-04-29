package schedule;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/schedule/Views/login.fxml"));
        Scene scene = new Scene(root);
        if (Locale.getDefault().getLanguage().equals("es")) {
            primaryStage.setTitle("Aplicación de programación");
        } else {
            primaryStage.setTitle("Scheduling App");
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
