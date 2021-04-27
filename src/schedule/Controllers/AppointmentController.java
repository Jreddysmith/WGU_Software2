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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
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
        "15", "16", "17", "18", "19", "20", "21", "22", "23");
        end_hour.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21", "22", "23");
        start_min.getItems().addAll("00","15", "30", "45");
        end_min.getItems().addAll("00","15", "30", "45");
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

    public void validateEndTimeBeforeStartTime(LocalDateTime start, LocalDateTime end) throws IOException, ValidationException{
        if(end.isBefore(start)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Error");
            alert.setContentText("Date/Time Error");
            alert.showAndWait();
            throw new ValidationException("You can not have end time be before start time");
        }
    }

    public void validateDateWeekend(LocalDateTime start, LocalDateTime end) throws IOException, ValidationException{
        // 6 is Saturday the 6th day of the week and 7 is Sunday the 7th day of the week.
        System.out.println("start day of week " + start.getDayOfWeek().getValue());
        System.out.println("end day of week " + end.getDayOfWeek().getValue());
        if(start.getDayOfWeek().getValue() == 6 || end.getDayOfWeek().getValue() == 7){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Error");
            alert.setContentText("You can not make an appointment on a weekend");
            alert.showAndWait();
            throw new ValidationException("You can not make an appointment on a weekend");
        }
    }
    public void validateOverlappingAppointments(LocalDateTime start, LocalDateTime end) throws IOException, ValidationException{

        if(Appointments.getOverLappingDate(start, end) == 1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Error");
            alert.setContentText("You Have to pick another date or time so you don't over lap appointments");
            alert.showAndWait();
            throw new ValidationException("You Have to pick another date or time so you don't over lap appointments");
        }
    }

    public void validateWorkHours(LocalDateTime start, LocalDateTime end) throws IOException, ValidationException{
        LocalTime startToTime = start.toLocalTime();
        LocalTime endToTime = end.toLocalTime();
        System.out.println(startToTime);
        System.out.println(endToTime);

        LocalTime openBusinessHours = LocalTime.of(8, 0);
        LocalTime closeBusinessHours = LocalTime.of(17, 0);

        if(startToTime.isBefore(openBusinessHours) || endToTime.isAfter(closeBusinessHours)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Error");
            alert.setContentText("You have to pick a time within business hours");
            alert.showAndWait();
            throw new ValidationException("You have to pick a time within business hours");
        }

        if(startToTime.equals(endToTime)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Error");
            alert.setContentText("Start Time and end time can not be the same");
            alert.showAndWait();
            throw new ValidationException("Start Time and end time can not be the same");
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
            validateEndTimeBeforeStartTime(startDate, endDate);
            validateDateWeekend(startDate, endDate);
            validateWorkHours(startDate, endDate);
//            validateOverlappingAppointments(startDate, endDate);
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

    public void getAppointment(Appointment appointment) throws IOException, ValidationException {
        main_label.setText("Modify Appointment");
        save_button.setVisible(false);
        update_button.setVisible(true);
        SimpleDateFormat dateForPicker = new SimpleDateFormat("yyyy-MM-dd");
        String stringForDatePicker = dateForPicker.format(appointment.getStart());

        SimpleDateFormat hour = new SimpleDateFormat("HH");
        String stringStartHour = hour.format(appointment.getStart());
        String stringEndHour = hour.format(appointment.getEnd());

        SimpleDateFormat min = new SimpleDateFormat("mm");
        String stringStartMin = min.format(appointment.getStart());
        String stringEndMin = min.format(appointment.getEnd());

        User singUser = Users.getSingleUser(Integer.parseInt(appointment.getUserId()));
        user_id_box.getItems().forEach(u -> {
            if(u == Integer.parseInt(singUser.getUserId())){
                user_id_box.setValue(u);
            }
        });

        Customer singCustomer = Customers.getSingleCustomer(Integer.parseInt(appointment.getCustomerId()));
        customer_id_box.getItems().forEach(c -> {
            if(c == Integer.parseInt(singCustomer.getCustomerId())){

                customer_id_box.setValue(c);
            }
        });

        user_id_box.setValue(Integer.valueOf(appointment.getUserId()));
        customer_id_box.setValue(Integer.valueOf(appointment.getCustomerId()));
        title.setText(appointment.getTitle());
        description.setText(appointment.getDescription());
        location.setText(appointment.getLocation());
        contact.setText(appointment.getContact());
        type.setText(appointment.getType());
        url.setText(appointment.getUrl());
        date_picker.setValue(LocalDate.parse(stringForDatePicker));
        start_hour.setValue(stringStartHour);
        start_min.setValue(stringStartMin);
        end_hour.setValue(stringEndHour);
        end_min.setValue(stringEndMin);
        //update button and lambda to make it easier in one method.
        update_button.setOnAction(updateEvent -> {
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
            String endHour = end_hour.getValue();
            String endMin = end_min.getValue();

            try{
                LocalDate date = date_picker.getValue();
                validateDate(date);
                validateUserId(user_id_box.getValue());
                validateCustomerId(customer_id_box.getValue());
                validateTime(start_hour.getValue());
                validateTime(end_hour.getValue());
                validateTime(start_min.getValue());
                validateTime(end_min.getValue());
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


                Appointment updateAppointment = new Appointment(customerId, userId, titleField, descriptionField, locationField, contactField,
                        typeField, urlField, formattedStartDate, formattedEndDate, user);
                int appointmentId = Integer.parseInt(appointment.getAppointmentId());
                validateEndTimeBeforeStartTime(startDate, endDate);
                validateDateWeekend(startDate, endDate);
                validateWorkHours(startDate, endDate);
//                validateOverlappingAppointments(startDate, endDate);
                updateAppointment.validate();
                new Appointments().updateAppointment(appointmentId, customerId, userId, titleField, descriptionField, locationField, contactField,
                        typeField, urlField, formattedStartDate, formattedEndDate, user);

                Stage stage;
                stage = (Stage)save_button.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/schedule/Views/homepage.fxml"));
                Parent root = loader.load();
                stage.setScene(new Scene(root));
                stage.show();

            } catch(ValidationException | IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Validation Error");
                alert.setHeaderText("Validation Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });
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