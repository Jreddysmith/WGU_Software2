package schedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedule.Models.Appointment;
import schedule.Models.Appointments;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentTypeReportController implements Initializable {

    @FXML
    private TableView<Appointment> table_appointment_type;

    @FXML
    private TableColumn<Appointment, String> appointment_type;

    @FXML
    private TableColumn<Appointment, String> count;

    @FXML
    private Button cancel;

    @FXML
    private RadioButton january;

    @FXML
    private RadioButton february;

    @FXML
    private RadioButton march;

    @FXML
    private RadioButton april;

    @FXML
    private RadioButton may;

    @FXML
    private RadioButton june;

    @FXML
    private RadioButton july;

    @FXML
    private RadioButton august;

    @FXML
    private RadioButton september;

    @FXML
    private RadioButton october;

    @FXML
    private RadioButton november;

    @FXML
    private RadioButton december;

    @FXML
    private ToggleGroup monthGroup;



    @FXML
    private void getAllAppointments() {table_appointment_type.setItems(Appointments.getAppointments());}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointment_type.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        getAllAppointments();

        january.setUserData("january");
        february.setUserData("february");
        march.setUserData("march");
        april.setUserData("april");
        may.setUserData("may");
        june.setUserData("june");
        july.setUserData("july");
        august.setUserData("august");
        september.setUserData("september");
        october.setUserData("october");
        november.setUserData("november");
        december.setUserData("december");

        monthGroup.selectedToggleProperty().addListener(((observableValue, oldToggle, newToggle) -> {
            if(monthGroup.getSelectedToggle() != null) {
                System.out.println("lets see if this works " + monthGroup.getSelectedToggle().getUserData().toString());
            }
        }));


    }
    @FXML
    void cancelButton(ActionEvent event) throws IOException{
        Stage stage;
        stage = (Stage)cancel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
}