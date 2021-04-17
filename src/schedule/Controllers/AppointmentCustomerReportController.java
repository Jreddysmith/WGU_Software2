package schedule.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    private TableColumn<Customer, String> count;

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
    public void getAllCustomers() {app_cus_rep_table.setItems(Customers.getCustomers());}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customer_id.setCellValueFactory((new PropertyValueFactory<Customer, String>("customerId")));
        getAllCustomers();

    }

    @FXML
    void aprilButton(ActionEvent event) {

    }

    @FXML
    void augustButton(ActionEvent event) {

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

    @FXML
    void decemberButton(ActionEvent event) {

    }

    @FXML
    void februaryButton(ActionEvent event) {

    }

    @FXML
    void januaryButton(ActionEvent event) {

    }

    @FXML
    void julyButton(ActionEvent event) {

    }

    @FXML
    void juneButton(ActionEvent event) {

    }

    @FXML
    void marchButton(ActionEvent event) {

    }

    @FXML
    void mayButton(ActionEvent event) {

    }

    @FXML
    void novemberButton(ActionEvent event) {

    }

    @FXML
    void octoberButton(ActionEvent event) {

    }

    @FXML
    void septemberButton(ActionEvent event) {

    }

}

