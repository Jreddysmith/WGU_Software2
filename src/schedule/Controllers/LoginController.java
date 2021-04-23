package schedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import schedule.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private TextField user_name_field;

    @FXML
    private TextField password_field;

    @FXML
    private Button exit_button;

    @FXML
    private Button login_button;


    public void loginButton(ActionEvent event) {
        String userName = user_name_field.getText();
        String userPassword = password_field.getText();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM U05wjs.user WHERE userName = ? AND password = ?";

        try {
            PreparedStatement userLoginStmt = connectDB.prepareStatement(verifyLogin);
            userLoginStmt.setString(1, userName);
            userLoginStmt.setString(2, userPassword);
            ResultSet userResult = userLoginStmt.executeQuery();

            while (userResult.next()) {
                if(userResult.getInt(1) == 1) {


                    System.out.println("Congrats");

                    Stage stage = new Stage();
                    stage = (Stage)login_button.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
                    Parent root = loader.load();
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    System.out.println("Invalid Login");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void exitButton(ActionEvent event) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }


}
