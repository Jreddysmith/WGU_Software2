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
import javafx.stage.Stage;
import javafx.util.Callback;
import schedule.Models.*;
import schedule.exceptions.ValidationException;

import java.io.IOException;
import java.net.URL;
import java.text.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppointmentController implements Initializable {

    @FXML
    private ComboBox<Integer> customer_id_box;
    @FXML
    private ComboBox<Integer> user_id_box;
//    @FXML
//    private ComboBox<String> start_period;
    @FXML
    private ComboBox<String> start_hour;
    @FXML
    private ComboBox<String> start_min;
//    @FXML
//    private ComboBox<String> end_period;
    @FXML
    private ComboBox<String> end_hour;
    @FXML
    private ComboBox<String> end_min;

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
    private Button update_button;

    @FXML
    private Label main_label;

    @FXML
    private TextField update_box;

//    @FXML
//    public void populateCustomerBox() {customer_id_box.setItems(Customers.getCustomers());}

//    @FXML
//    public void populateUserBox() {user_id_box.setItems(Users.getAllUsers());}

    private User activeUser = User.currentUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Users.getAllUsers().forEach(user -> {
            user_id_box.getItems().add(Integer.valueOf(user.getUserId()));
        });

        Customers.getCustomers().forEach(customer -> {
            customer_id_box.getItems().add(Integer.valueOf(customer.getCustomerId()));
        });

        start_hour.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
        "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
        end_hour.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
        start_min.getItems().addAll("15", "30", "45");
        end_min.getItems().addAll("15", "30", "45");
//        start_period.getItems().addAll("AM", "PM");
//        end_period.getItems().addAll("AM", "PM");


    }
    public void validateDate(LocalDate pickingDate) throws IOException, ValidationException {
        if(pickingDate == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Error");
            alert.setContentText("You must pick a date");
            alert.showAndWait();
            throw new ValidationException("You have to pick a date");
        }
    }

    public void validateTime(String time) throws IOException, ValidationException {
        if(time == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Error");
            alert.setContentText("You have to pick a start and end time");
            alert.showAndWait();
            throw new ValidationException("You have to pick a start and end time");
        }
    }

    public void validateUserId(Integer userId) throws IOException, ValidationException{
        if(userId == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Error");
            alert.setContentText("You must pick a User ID");
            alert.showAndWait();
            throw new ValidationException("You have to pick a User ID");
        }
    }

    public void validateCustomerId(Integer customerId) throws IOException, ValidationException{
        if(customerId == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Error");
            alert.setContentText("You must pick a Customer ID");
            alert.showAndWait();
            throw new ValidationException("You have to pick a Customer ID");
        }
    }





    @FXML
    public void saveButton(ActionEvent event) throws IOException, ValidationException {
        LocalDate date = date_picker.getValue();
        validateDate(date);
        validateUserId(user_id_box.getValue());
        validateCustomerId(customer_id_box.getValue());
        validateTime(start_hour.getValue());
        validateTime(end_hour.getValue());
        validateTime(start_min.getValue());
        validateTime(end_min.getValue());

        int userId = Integer.parseInt(String.valueOf(user_id_box.getValue()));
        int customerId = Integer.parseInt(String.valueOf(customer_id_box.getValue()));
        String titleField = title.getText();
        String descriptionField = description.getText();
        String locationField = location.getText();
        String contactField = contact.getText();
        String typeField = type.getText();
        String urlField = url.getText();
        String user = activeUser.getUserName();
        String startHour = start_hour.getValue();
        String startMin = start_min.getValue();
//        String startPeriod = start_period.getValue();
        String endHour = end_hour.getValue();
        String endMin = end_min.getValue();
//        String endPeriod = end_period.getValue();

        StringBuilder startString = new StringBuilder(startHour + ":" + startMin + ":" + "00");

        DateTimeFormatter storedAppointment = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        LocalDateTime startDate = LocalDateTime.of(date, LocalTime.parse(startString));

        String formattedStartDate = storedAppointment.format(startDate);
        System.out.println(formattedStartDate);

        StringBuilder endString = new StringBuilder(endHour + ":" + endMin + ":" + "00");

        LocalDateTime endDate = LocalDateTime.of(date, LocalTime.parse(endString));

        String formattedEndDate = storedAppointment.format(endDate);
        System.out.println(formattedStartDate);
        System.out.println(formattedEndDate);


        Appointment newAppointment = new Appointment(customerId, userId, titleField, descriptionField, locationField, contactField,
                typeField, urlField, formattedStartDate, formattedEndDate, user);

        try{
            newAppointment.validate();
            new Appointments().addAppointment(customerId, userId, titleField, descriptionField, locationField, contactField,
                    typeField, urlField, formattedStartDate, formattedEndDate, user);

            Stage stage;
            stage = (Stage)save_button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();

        } catch(ValidationException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
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
        save_button.setVisible(false);
        update_button.setVisible(true);

        SimpleDateFormat dateForPicker = new SimpleDateFormat("yyyy-MM-dd");
        String stringForDatePicker = dateForPicker.format(appointment.getStart());
//        System.out.println(stringForDatePicker);

        SimpleDateFormat startHour = new SimpleDateFormat("hh");
        String stringStartHour = startHour.format(appointment.getStart());
//        System.out.println(stringStartHour);

        SimpleDateFormat startMin = new SimpleDateFormat("mm");
        String stringStartMin = startMin.format(appointment.getStart());
//        System.out.println(stringStartMin);

        SimpleDateFormat startPeriod = new SimpleDateFormat("aa");
        String stringStartPeriod = startPeriod.format(appointment.getStart());
//        System.out.println(stringStartPeriod);

        SimpleDateFormat endHour = new SimpleDateFormat("hh");
        String stringEndHour = endHour.format(appointment.getEnd());
//        System.out.println(stringEndHour);

        SimpleDateFormat endMin = new SimpleDateFormat("mm");
        String stringEndMin = endMin.format(appointment.getEnd());
//        System.out.println(stringEndMin);

        SimpleDateFormat endPeriod = new SimpleDateFormat("aa");
        String stringEndPeriod = endPeriod.format(appointment.getEnd());
//        System.out.println(stringEndPeriod);

        Customer singCustomer = Customers.getSingleCustomer(Integer.parseInt(appointment.getCustomerId()));
        customer_id_box.getItems().forEach(e -> {
            if(e == Integer.parseInt(singCustomer.getCustomerId())){

                customer_id_box.setValue(e);
            }
        });
//        System.out.println(customer_id_box.getItems());


//        customer_id_box.setValue(appointment.getCustomerId());
//        user_id.setText(appointment.getUserId());
        title.setText(appointment.getTitle());
        description.setText(appointment.getDescription());
        location.setText(appointment.getLocation());
        contact.setText(appointment.getContact());
        type.setText(appointment.getType());
        url.setText(appointment.getUrl());
        date_picker.setValue(LocalDate.parse(stringForDatePicker));
//        start_hour.setValue(Integer.valueOf(stringStartHour));
//        start_min.setValue(Integer.valueOf(stringStartMin));
//        start_period.setValue(stringStartPeriod);
//        end_hour.setValue(Integer.valueOf(stringEndHour));
//        end_min.setValue(Integer.valueOf(stringEndMin));
//        end_period.setValue(stringEndPeriod);
    }

    @FXML
    public void updateButton(ActionEvent event){

    }

    public void setButtonType(int i) {
        if(i == 0){
            save_button.setVisible(true);
            update_button.setVisible(false);
        }
        else{
            save_button.setVisible(false);
            update_button.setVisible(true);
        }
    }
}