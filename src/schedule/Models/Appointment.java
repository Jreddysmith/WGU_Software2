package schedule.Models;

import javafx.scene.control.Alert;
import schedule.exceptions.ValidationException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class Appointment {

    private String appointmentId;
    private String customerId;
    private String userId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String url;
    private String date;
    private Timestamp start;
    private Timestamp end;
    private String count;
    private String activeUser;

    public Appointment() {
    }


    public Appointment(String type, String count) {
        this.type = type;
        this.count = count;
    }

    //look into this
    public Appointment(String customerId, String count, String appointmentId) {
        this.customerId = customerId;
        this.count = count;
        this.appointmentId = appointmentId;
    }

    public Appointment(String appointmentId, String customerId, String userId, String title, String description,
                       String location, String contact, String type, String url, Timestamp start, Timestamp end) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.start = start;
        this.end = end;
    }

    public Appointment(String appointmentId, String customerId, String userId, String title, String description,
                       String location, String contact, String type, String url, Timestamp start, Timestamp end, String activeUser) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.start = start;
        this.end = end;
        this.activeUser = activeUser;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(String activeUser) {
        this.activeUser = activeUser;
    }

    public String getFormattedStartTime() {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault()).format(start);
    }

    public String getFormattedEndTime() {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault()).format(end);
    }


    public void validate() throws ValidationException {
        if (getTitle().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Save Error");
            alert.setContentText("Title field can not be empty");
            alert.showAndWait();
            throw new ValidationException("Title field can not be empty");

        }
    }
}
