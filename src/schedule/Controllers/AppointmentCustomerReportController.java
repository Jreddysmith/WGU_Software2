package schedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedule.Models.Appointment;
import schedule.Models.Appointments;
import schedule.Models.Customer;
import schedule.Models.Customers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentCustomerReportController implements Initializable {

    @FXML
    private TableView<Customer> app_cus_rep_table;

    @FXML
    private TableColumn<Customer, String> customer_id;

    @FXML
    private TableColumn<Customer, String> customer_name;

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
    private void getJanuaryAtStart() {app_cus_rep_table.setItems(Customers.getCustomerCountInMonth("January"));}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customer_id.setCellValueFactory((new PropertyValueFactory<Customer, String>("customerId")));
        customer_name.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        getJanuaryAtStart();

        january.setUserData("January");
        february.setUserData("February");
        march.setUserData("March");
        april.setUserData("April");
        may.setUserData("May");
        june.setUserData("June");
        july.setUserData("July");
        august.setUserData("August");
        september.setUserData("September");
        october.setUserData("October");
        november.setUserData("November");
        december.setUserData("December");

        monthGroup.selectedToggleProperty().addListener(((observableValue, oldToggle, newToggle) -> {
            if(monthGroup.getSelectedToggle() != null) {
                System.out.println("lets see if this works " + monthGroup.getSelectedToggle().getUserData().toString());
                String month = monthGroup.getSelectedToggle().getUserData().toString();
                app_cus_rep_table.setItems(Customers.getCustomerCountInMonth(month));
            }
        }));

    }

    @FXML
    void cancelButton(ActionEvent event) throws IOException {

        Stage stage;
        stage = (Stage)cancel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }



}

