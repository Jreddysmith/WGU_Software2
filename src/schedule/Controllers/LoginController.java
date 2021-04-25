package schedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import schedule.DatabaseConnection;
import schedule.Models.User;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

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

//        String verifyLogin = "SELECT count(1) FROM U05wjs.user WHERE userName = ? AND password = ?";
        String verifyLogin = "SELECT * FROM U05wjs.user WHERE userName = ? AND password = ?";

        String checkAppointments = "select * from U05wjs.appointment";

        try {
            PreparedStatement userLoginStmt = connectDB.prepareStatement(verifyLogin);
            userLoginStmt.setString(1, userName);
            userLoginStmt.setString(2, userPassword);
            ResultSet userResult = userLoginStmt.executeQuery();

            PreparedStatement appointmentStmt = connectDB.prepareStatement(checkAppointments);
            ResultSet appointmentResult = appointmentStmt.executeQuery();
            if (userResult.next() && userName.equals(userResult.getString("userName")) && userPassword.equals(userResult.getString("password"))) {
//                    if (userName.equals(userResult.getString("userName")) && userPassword.equals(userResult.getString("password"))) {
                        while (appointmentResult.next()) {
                            Instant timeNow = Instant.now();
                            Instant time15FromNow = timeNow.plus(15, ChronoUnit.MINUTES);
                            Timestamp startTime = appointmentResult.getTimestamp("start");
                            if (startTime.toInstant().isAfter(timeNow) && startTime.toInstant().isBefore(time15FromNow)) {
//                                System.out.println("there is a time we have");
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Alert");
                                alert.setHeaderText("Appointment Alert");
                                alert.setContentText("You have an appointment within 15 minutes!");
                                alert.showAndWait();
                            }

                        }
                        //Log Writer
                        FileWriter logFile = new FileWriter("logFile.txt", true);
                        logFile.write("\n**** " + userName + " " + LocalDateTime.now() + " ****");
                        logFile.close();

                        String userId = userResult.getString(1);
                        String userNameResponse = userResult.getString(2);
                        User activeUser = new User(userId, userNameResponse);

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
                        Parent root = loader.load();
                        HomePageController mpc = loader.getController();
                        mpc.getActiveUser(activeUser);

                        Stage stage;
                        stage = (Stage) login_button.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
//                    } else {
//                        System.out.println("Invalid Login");
//                    }
            } else {
//                System.out.println("Not a user");
                try {
//                  Locale.setDefault(new Locale("es", "ES"));
                    System.out.println(Locale.getDefault().getLanguage());
                    if (Locale.getDefault().getLanguage().equals("es")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("error de inicio de sesión");
                        alert.setContentText("Le nom d'utilisateur et le mot de passe ne correspondent pas");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("Login Error");
                        alert.setContentText("The username or password did not match.");
                        alert.showAndWait();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
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
