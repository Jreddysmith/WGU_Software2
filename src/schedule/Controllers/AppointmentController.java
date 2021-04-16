package schedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import schedule.Models.Appointment;
import schedule.Models.Appointments;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    @FXML
    private TextField customer_id;

    @FXML
    private TextField user_id;

    @FXML
    private TextField title;

    @FXML
    private TextField description;

    @FXML
    private TextField location;

    @FXML
    private TextField contact;

    @FXML
    private TextField type;

    @FXML
    private TextField url;

    @FXML
    private TextField  start;

    @FXML
    private TextField end;

    @FXML
    private TextField date;

    @FXML
    private Button save_button;

    @FXML
    private Button close_button;

    @FXML
    private Label main_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void saveButton(ActionEvent event) throws IOException {

        int customerId = Integer.parseInt(customer_id.getText());
        int userId = Integer.parseInt(user_id.getText());
        String titleField = title.getText();
        String descriptionField = description.getText();
        String locationField = location.getText();
        String contactField = contact.getText();
        String typeField = type.getText();
        String urlField = url.getText();
        String startField = start.getText();
        String endField = end.getText();


//
        new Appointments().addAppointment(customerId, userId, titleField, descriptionField, locationField, contactField,
                typeField, urlField, startField, endField);

        System.out.println("back to controller after appointment save");

        Stage stage;
        stage = (Stage)save_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void closeButton(ActionEvent event) throws IOException {
        Stage stage;
        stage = (Stage)close_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void getAppointment(Appointment appointment) {
        main_label.setText("Modify Appointment");

        System.out.println(appointment.getAppointmentId());
        System.out.println(appointment.getCustomerId());
        System.out.println(appointment.getUserId());


        customer_id.setText(appointment.getCustomerId());
        user_id.setText(appointment.getUserId());
        title.setText(appointment.getTitle());
        description.setText(appointment.getDescription());
        location.setText(appointment.getLocation());
        contact.setText(appointment.getContact());
        type.setText(appointment.getType());
        url.setText(appointment.getUrl());
        start.setText(appointment.getStart());
        end.setText(appointment.getEnd());
    }
}
