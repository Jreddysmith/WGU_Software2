package schedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class ConsultantAppointmentReportController implements Initializable {

    @FXML
    private TableView<Appointment> consultant_table;

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
    private Button cancel;

    @FXML
    private ComboBox<Customer> customers;

    @FXML
    public void getAllAppointments(){consultant_table.setItems(Appointments.getAppointments());}

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
        System.out.println("In the initialize for all Appointments table");
        getAllAppointments();

    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Stage stage;
        stage = (Stage)cancel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();

    }
}

