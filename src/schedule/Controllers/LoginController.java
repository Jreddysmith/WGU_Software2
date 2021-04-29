package schedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import schedule.DatabaseConnection;
import schedule.Models.User;
import schedule.Models.Users;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField user_name_field;

    @FXML
    private TextField password_field;

    @FXML
    private Button exit_button;

    @FXML
    private Button login_button;

    @FXML
    private Label login_label;

    @FXML
    private Label username_label;

    @FXML
    private Label password_label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Locale.setDefault(new Locale("es", "ES"));
//        System.out.println(Locale.getDefault());
        setLanguage();
    }


    public void loginButton(ActionEvent event) {
        String userName = user_name_field.getText();
        String userPassword = password_field.getText();
        if (userName.isEmpty() && Locale.getDefault().getLanguage().equals("en")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Username Field");
            alert.setContentText("Username can not be empty");
            alert.showAndWait();
        }

        if (userPassword.isEmpty() && Locale.getDefault().getLanguage().equals("en")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Password Field");
            alert.setContentText("Password can not be empty");
            alert.showAndWait();
        }

        if(userName.isEmpty() && Locale.getDefault().getLanguage().equals("es")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("Campo de nombre de usuario");
            alert.setContentText("El nombre de usuario no puede estar vacío");
            alert.showAndWait();
        }

        if(userPassword.isEmpty() && Locale.getDefault().getLanguage().equals("es")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("Campo de contraseña");
            alert.setContentText("La contraseña no puede estar vacía");
            alert.showAndWait();
        }

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


//        String verifyLogin = "SELECT count(1) FROM U05wjs.user WHERE userName = ? AND password = ?";
        String verifyLogin = "SELECT * FROM U05wjs.user WHERE userName = ? AND password = ?";

        String checkAppointments = "select * from U05wjs.appointment";

        if((!userName.isEmpty()) && (!userPassword.isEmpty())) {
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

                    User activeUser = Users.getSingleUser(Integer.parseInt(userId));

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
                    Parent root = loader.load();
                    User.currentUser = activeUser;

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
                            alert.setContentText("Le nom d'utilisateur et le mot\nde passe ne correspondent pas");
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
    }

    public void setLanguage() {
        ResourceBundle rb = ResourceBundle.getBundle("schedule/Resources/Login", Locale.getDefault());
        login_label.setText(rb.getString("login_label"));
        username_label.setText(rb.getString("username_label"));
        password_label.setText(rb.getString("password_label"));
        login_button.setText(rb.getString("login_button"));
        exit_button.setText(rb.getString("exit_button"));

    }


    public void exitButton(ActionEvent event) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }


}
