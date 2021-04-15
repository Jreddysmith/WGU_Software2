package schedule.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedule.Models.Customer;
import schedule.Models.Customers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {


    @FXML
    private Button customer_add;

    @FXML
    private Button appointment_add;

    @FXML Button customer_modify;

    @FXML
    private TableView<Customer> customer_table;

    @FXML
    private TableColumn<Customer, String> customer_id;

    @FXML
    private TableColumn<Customer, String> customer_name;

    @FXML
    private TableColumn<Customer, String> customer_address_id;

    @FXML
    private TableColumn<Customer, String> customer_active;

    ObservableList<Customer> customerListView;

    @FXML
    public void updateList() {customer_table.setItems(Customers.getCustomers());}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customer_id.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerId"));
        customer_name.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        customer_address_id.setCellValueFactory(new PropertyValueFactory<Customer, String>("addressId"));
        customer_active.setCellValueFactory(new PropertyValueFactory<Customer, String>("active"));
        System.out.println("in the initialize");
        updateList();


    }



    @FXML
    public void customerAdd() throws IOException {
        Stage stage = new Stage();
        stage = (Stage)customer_add.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/schedule/Views/addCustomer.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        updateList();

    }

    @FXML
    public void  customerModify() throws IOException {
        Customer customer = customer_table.getSelectionModel().getSelectedItem();

        if(customer == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Modify Error");
            alert.setContentText("You have to have an item to be able to modify it");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/schedule/Views/addCustomer.fxml"));
            Parent root = loader.load();

            CustomerController mpc = loader.getController();
            mpc.getCustomer(customer);

            Stage stage;
            stage = (Stage)customer_modify.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    public void customerDelete() {
        Customer customer = customer_table.getSelectionModel().getSelectedItem();

        if(customer != null) {
            Customers.deleteCustomer(customer);
            updateList();
            System.out.println("Customer Deleted successfully");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Delete Error");
            alert.setContentText("You have to have an item to be able to delete it");
            alert.showAndWait();
        }

    }

    @FXML
    public void appointmentAdd() throws IOException {
        Stage stage = new Stage();
        stage = (Stage)appointment_add.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/schedule/Views/addAppointment.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void appointmentModify() {

    }

    @FXML
    public void appointmentDelete(){

    }

    @FXML
    public void viewAppointments(){

    }

    @FXML
    public void appointmentTypesReport(){

    }

    @FXML
    public void consultantScheduleReport(){

    }

    @FXML
    public void appointmentCostumersReport(){

    }

    @FXML
    public void logout() {

    }



}
