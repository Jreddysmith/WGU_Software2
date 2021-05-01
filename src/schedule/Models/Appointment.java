package schedule.Models;

import javafx.scene.control.Alert;
import schedule.exceptions.ValidationException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
    private LocalDate date;
    private Date start;
    private Date end;
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
                       String location, String contact, String type, String url, Date start, Date end) {
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

    public Appointment(int customerId, int userId, String titleField, String descriptionField, String locationField, String contactField,
                       String typeField, String urlField, Date outputStart, Date outputEnd, String user) {
        this.customerId = String.valueOf(customerId);
        this.userId = String.valueOf(userId);
        this.title = titleField;
        this.description = descriptionField;
        this.location = locationField;
        this.contact = contactField;
        this.type = typeField;
        this.url = urlField;
        this.start = outputStart;
        this.end = outputEnd;
        this.activeUser = user;

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
//        return ZonedDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault()).toString();
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault()).format(start);
    }

    public String getFormattedEndTime() {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault()).format(end);
    }



    public void validate() throws ValidationException {
        if (getTitle().isEmpty() || getTitle().length() < 4) {
            throw new ValidationException("Title field can not be empty or less then 4 Characters");
        }
        if(getDescription().isEmpty() || getDescription().length() < 4) {
            throw new ValidationException("Description field can not be empty or less then 4 Characters");
        }
        if(getLocation().isEmpty() || getLocation().length() < 5) {
            throw new ValidationException("Location field can not be empty or less then 5 Characters");
        }
        if(getContact().isEmpty() || getContact().length() < 5) {
            throw new ValidationException("Contact field can not be empty or less then 5 Characters");
        }
        if(getType().isEmpty() || getType().length() < 4) {
            throw new ValidationException("Type field can not be empty or less then 4 Characters");
        }
        if(getUrl().isEmpty() || getUrl().length() < 5) {
            throw new ValidationException("Url field can not be empty or less then 5 Characters");
        }
    }
}
