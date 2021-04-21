package schedule.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.w3c.dom.Text;
import schedule.Models.Appointment;
import schedule.Models.Appointments;
import schedule.Models.Customer;
import schedule.Models.Customers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppointmentController implements Initializable {

    @FXML
    private ComboBox<Customer> customer_id_box;
    @FXML
    private ComboBox<String> start_period;
    @FXML
    private ComboBox<Integer> start_hour;
    @FXML
    private ComboBox<Integer> start_min;
    @FXML
    private ComboBox<String> end_period;
    @FXML
    private ComboBox<Integer> end_hour;
    @FXML
    private ComboBox<Integer> end_min;

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
    private DatePicker date_picker;

    @FXML
    private Button save_button;

    @FXML
    private Button close_button;

    @FXML
    private Label main_label;

    @FXML
    public void updateAppointmentList() {customer_id_box.setItems(Customers.getCustomers());}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(customer_id_box + "starttttt");
        customer_id_box.setCellFactory(customerListView -> {
            return new ListCell<>(){
                @Override
                protected void updateItem(Customer customer, boolean empty) {
                    super.updateItem(customer, empty);
                    if(customer == null || empty) {
                        setText(null);
                    } else {

                        setText(customer.getCustomerName());


                    }

                }
            };
        });

        customer_id_box.setOnAction((ActionEvent event) -> {
            System.out.println();
        });

        Callback<ListView<Integer>, ListCell<Integer>> paddedView = integerListView -> new ListCell<>() {
            @Override
            protected void updateItem(Integer integer, boolean empty) {
                super.updateItem(integer, empty);
                if (integer == null || empty) {
                    setText(null);
                } else {
                    setText(String.format("%02d", integer));
                }
            }
        };
        start_hour.setCellFactory(paddedView);
        start_min.setCellFactory(paddedView);
        end_hour.setCellFactory(paddedView);
        end_min.setCellFactory(paddedView);

        start_hour.setItems(intRange(1,12));
        start_min.setItems(intRange(0,59));
        end_hour.setItems(intRange(1,12));
        end_min.setItems(intRange(0,59));
        updateAppointmentList();

        start_period.getItems().addAll("AM", "PM");
        end_period.getItems().addAll("AM", "PM");


    }

    private ObservableList<Integer> intRange(int start, int end) {
        List<Integer> L = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
        return FXCollections.observableArrayList(L);
    }





    @FXML
    public void saveButton(ActionEvent event) throws IOException {

        LocalDate dateValue = date_picker.getValue();
        String myFormatedDate = dateValue.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        System.out.println(myFormatedDate);



        System.out.println(start_hour.getValue());
        System.out.println(start_min.getValue());
//        System.out.println(start_period);

        System.out.println(end_hour.getValue());
        System.out.println(end_min.getValue());
//        System.out.println(end_period);

        System.out.println(customer_id_box.getValue().getCustomerId());




//        int customerId = Integer.parseInt(customer_id.getText());
//        int userId = Integer.parseInt(user_id.getText());
//        String titleField = title.getText();
//        String descriptionField = description.getText();
//        String locationField = location.getText();
//        String contactField = contact.getText();
//        String typeField = type.getText();
//        String urlField = url.getText();
//        String startField = start.getText();
//        String endField = end.getText();


//
//        new Appointments().addAppointment(customerId, userId, titleField, descriptionField, locationField, contactField,
//                typeField, urlField, startField, endField);
//
//        System.out.println("back to controller after appointment save");
//
//        Stage stage;
//        stage = (Stage)save_button.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
//        Parent root = loader.load();
//        stage.setScene(new Scene(root));
//        stage.show();

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


//        customer_id.setText(appointment.getCustomerId());
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

    @FXML
    public void datePicker(ActionEvent event) {

    }
}
