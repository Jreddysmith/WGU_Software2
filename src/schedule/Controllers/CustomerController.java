package schedule.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import schedule.Models.Customer;
import schedule.Models.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private TextField customer_id;

    @FXML
    private TextField customer_name;
    @FXML
    private TextField customer_address_1;
    @FXML
    private TextField customer_address_2;
    @FXML
    private TextField customer_city;
    @FXML
    private TextField customer_country;
    @FXML
    private TextField customer_zipcode;
    @FXML
    private TextField customer_number;
    @FXML
    private TextField customer_active;
    @FXML
    private RadioButton active_yes;
    @FXML
    private RadioButton active_no;
    @FXML
    private ToggleGroup activeGroup;
    @FXML
    private Label main_label;


    @FXML
    private Button save_button;

    @FXML
    private Button update_button;

    @FXML
    private Button cancel_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    @FXML
    public void saveButton(ActionEvent event) throws IOException {
        String customerName = customer_name.getText();
        String customerAddress1 = customer_address_1.getText();
        String customerAddress2 = customer_address_2.getText();
        String customerCity = customer_city.getText();
        String customerCountry = customer_country.getText();
        String customerZipcode = customer_zipcode.getText();
        String customerNumber = customer_number.getText();
        int customerActive = 0;
        if (active_yes.isSelected()){ customerActive = 1; }
        if (active_no.isSelected()){customerActive = 0; }
        System.out.println(customerActive);


        new Customers().addCustomer(customerName, customerAddress1, customerAddress2, customerCity, customerCountry,
                                                         customerZipcode, customerNumber, customerActive);


        System.out.println("Back to controller after save");

        Stage stage;
        stage = (Stage)save_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();


    }

    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        Stage stage;
        stage = (Stage)cancel_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void getCustomer(Customer customer) {
        main_label.setText("Modify Customer");

        System.out.println(customer.getCustomerId());
        System.out.println(customer.getCustomerName());
        System.out.println(customer.getAddress());

        customer_name.setText(customer.getCustomerName());
        customer_address_1.setText(customer.getAddress());
        customer_address_2.setText(customer.getAddress2());
        customer_city.setText(customer.getCity());
        customer_country.setText(customer.getCountry());
        customer_zipcode.setText(customer.getPostalCode());
        customer_number.setText(customer.getPhone());
        if(customer.getActive() == "1") {
            active_yes.isSelected();
        }
        if(customer.getActive() == "0") {
            active_no.isSelected();
        }

        update_button.setOnAction((e) -> {
        int customerId = Integer.parseInt(customer.getCustomerId());
        String customerName = customer_name.getText();
        String customerAddress1 = customer_address_1.getText();
        String customerAddress2 = customer_address_2.getText();
        String customerCity = customer_city.getText();
        String customerCountry = customer_country.getText();
        String customerZipcode = customer_zipcode.getText();
        String customerNumber = customer_number.getText();
        int customerActive = 0;
        if (active_yes.isSelected()){ customerActive = 1; }
        if (active_no.isSelected()){customerActive = 0; }

        int customerAddressId = Integer.parseInt(customer.getAddressId());
        int customerCityId = Integer.parseInt(customer.getCityId());
        int customerCountryId = Integer.parseInt(customer.getCountryId());

        Customers.updateCustomer(customerId, customerAddressId, customerName, customerAddress1, customerAddress2, customerCity, customerCountry,
                customerZipcode, customerNumber, customerActive, customerCityId, customerCountryId);


        Stage stage;
        stage = (Stage)update_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            stage.setScene(new Scene(root));
        stage.show();

        });
    }

    @FXML
    public void updateButton(ActionEvent event) throws IOException {
//        String customerName = customer_name.getText();
//        String customerAddress1 = customer_address_1.getText();
//        String customerAddress2 = customer_address_2.getText();
//        String customerCity = customer_city.getText();
//        String customerCountry = customer_country.getText();
//        String customerZipcode = customer_zipcode.getText();
//        String customerNumber = customer_number.getText();
//        int customerActive = 0;
//        if (active_yes.isSelected()){ customerActive = 1; }
//        if (active_no.isSelected()){customerActive = 0; }
//
//
//        Stage stage;
//        stage = (Stage)update_button.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
//        Parent root = loader.load();
//        stage.setScene(new Scene(root));
//        stage.show();

    }
}
