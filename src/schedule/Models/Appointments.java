package schedule.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedule.DatabaseConnection;

import java.sql.*;
import java.text.SimpleDateFormat;

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
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

            while (appointmentListQuery.next()) {
                String appointmentId = Integer.toString(appointmentListQuery.getInt(1));
                String customerId = Integer.toString(appointmentListQuery.getInt(2));
                String userId = Integer.toString(appointmentListQuery.getInt(3));
                String title = appointmentListQuery.getString(4);
                String description = appointmentListQuery.getString(5);
                String location = appointmentListQuery.getString(6);
                String contact = appointmentListQuery.getString(7);
                String type = appointmentListQuery.getString(8);
                String url = appointmentListQuery.getString(9);
                String start = formatter.format(appointmentListQuery.getDate(10));
                String end = formatter.format(appointmentListQuery.getDate(11));

                appointmentsList.add(new Appointment(appointmentId, customerId, userId, title, description, location, contact,
                        type, url, start, end));

                System.out.println("Just got all the appointments!!!!");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentsList;
    }
}
