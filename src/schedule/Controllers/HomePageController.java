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
import schedule.Models.Appointment;
import schedule.Models.Appointments;
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
    private TableView<Appointment> appointment_table;

    @FXML
    private TableColumn<Appointment, String> appointment_id;

    @FXML
    private TableColumn<Appointment, String> appointment_user_id;

    @FXML
    private TableColumn<Appointment, String> appointment_customer_id;

    @FXML
    private TableColumn<Appointment, String> appointment_title;

    @FXML
    private TableColumn<Appointment, String> appointment_start;

    @FXML
    private TableColumn<Appointment, String> appointment_end;


    @FXML
    public void updateCustomerList() {customer_table.setItems(Customers.getCustomers());}

    @FXML
    public void updateAppointmentList() {appointment_table.setItems(Appointments.getAppointments());}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customer_id.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerId"));
        customer_name.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        customer_address_id.setCellValueFactory(new PropertyValueFactory<Customer, String>("addressId"));
        customer_active.setCellValueFactory(new PropertyValueFactory<Customer, String>("active"));
        System.out.println("In the initialize for customer Table");
        updateCustomerList();

        appointment_id.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentId"));
        appointment_user_id.setCellValueFactory(new PropertyValueFactory<Appointment, String>("userId"));
        appointment_customer_id.setCellValueFactory(new PropertyValueFactory<Appointment, String>("customerId"));
        appointment_title.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        appointment_start.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
        appointment_end.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
        System.out.println("In the initialize for appointment Table");
        updateAppointmentList();

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

        updateCustomerList();

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
            updateCustomerList();
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
