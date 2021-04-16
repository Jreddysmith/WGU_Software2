package schedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import schedule.Models.Appointment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewAppointmentController implements Initializable {

    @FXML
    private TableView<Appointment> appointment_table;

    @FXML
    private TableColumn<Appointment, String> appointment_id;

    @FXML
    private TableColumn<Appointment, String> user_id;

    @FXML
    private TableColumn<Appointment, String> customer_id;

    @FXML
    private TableColumn<Appointment, String> title;

    @FXML
    private TableColumn<Appointment, String> description;

    @FXML
    private TableColumn<Appointment, String> location;

    @FXML
    private TableColumn<Appointment, String> contact;

    @FXML
    private TableColumn<Appointment, String> start;

    @FXML
    private TableColumn<Appointment, String> end;

    @FXML
    private Button weekly_appointments;

    @FXML
    private Button monthly_appointments;

    @FXML
    private Button all_appointments;

    @FXML
    private Button cancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void weeklyAppointments() throws IOException {

    }

    @FXML
    public void monthlyAppointments() throws IOException {

    }

    @FXML
    public void allAppointments() throws IOException {

    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        Stage stage;
        stage = (Stage)cancel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
