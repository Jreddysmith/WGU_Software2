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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedule.Models.Appointment;
import schedule.Models.Appointments;
import schedule.Models.Customer;

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

    @FXML
    public void allAppointmentsButton() { appointment_table.setItems(Appointments.getAppointments());}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointment_id.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentId"));
        user_id.setCellValueFactory(new PropertyValueFactory<Appointment, String>("userId"));
        customer_id.setCellValueFactory(new PropertyValueFactory<Appointment, String>("customerId"));
        title.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        description.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        location.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        contact.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contact"));
        start.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
        end.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
        allAppointmentsButton();

    }

    @FXML
    public void allAppointments() throws IOException {
        allAppointmentsButton();
    }

    @FXML
    public void weeklyAppointments() throws IOException {

    }

    @FXML
    public void monthlyAppointments() throws IOException {

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
