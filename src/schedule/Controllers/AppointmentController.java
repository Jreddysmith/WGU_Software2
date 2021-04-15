package schedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import schedule.Models.Appointments;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class AppointmentController {

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
    private Button save_button;

    @FXML
    private Button close_button;





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

        new Appointments().addAppointment();

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




}
