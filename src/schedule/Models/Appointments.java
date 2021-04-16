package schedule.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedule.DatabaseConnection;

import java.sql.*;

public class Appointments {

    public void addAppointment(int customerId, int userId, String title, String description, String location, String contact,
                               String type, String url, String start, String end ) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insertAppointment = "insert into U05wjs.appointment (customerId, userId, title, description, location, contact, type, " +
                "url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) values (?, ?, ?, ?, ?, ?, ?, ?, sysdate(), sysdate(), sysdate()," +
                " 'hummm', current_timestamp(), 'nobody')";

        try {
            PreparedStatement appointmentStatement = connectDB.prepareStatement(insertAppointment, Statement.RETURN_GENERATED_KEYS);
            appointmentStatement.setInt(1, customerId);
            appointmentStatement.setInt(2, userId);
            appointmentStatement.setString(3, title);
            appointmentStatement.setString(4, description);
            appointmentStatement.setString(5, location);
            appointmentStatement.setString(6, contact);
            appointmentStatement.setString(7, type);
            appointmentStatement.setString(8, url);
//            appointmentStatement.setString(9, start);
//            appointmentStatement.setString(10, end);
            appointmentStatement.executeUpdate();
            ResultSet appointmentOutput = appointmentStatement.getGeneratedKeys();

            appointmentOutput.next();
            int appointmentId = appointmentOutput.getInt(1);
            System.out.println("this is the appointment ID: " + appointmentId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Appointment> getAppointments() {

        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String appointmentQuery = "select * from U05wjs.appointment";

        try{
            Statement appointmentListStatement = connectDB.createStatement();
            ResultSet appointmentListQuery = appointmentListStatement.executeQuery(appointmentQuery);

            while (appointmentListQuery.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentsList;
    }
}
