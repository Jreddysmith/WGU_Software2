package schedule.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedule.DatabaseConnection;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Appointments {

    public static ObservableList<Appointment> appointmentsMonthly() {
        ObservableList<Appointment> monthList = FXCollections.observableArrayList();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String appointmentQuery = "select * from U05wjs.appointment where start> now() - INTERVAL 12 month";

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

                monthList.add(new Appointment(appointmentId, customerId, userId, title, description, location, contact,
                        type, url, start, end));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthList;

    }

    public static ObservableList<Appointment> appointmentsWeekly() {

        ObservableList<Appointment> weekList = FXCollections.observableArrayList();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String appointmentQuery = "select * from U05wjs.appointment where start> now() - INTERVAL 52 week";

        try{
            Statement appointmentListStatement = connectDB.createStatement();
            ResultSet appointmentListQuery = appointmentListStatement.executeQuery(appointmentQuery);
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault());


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
                String start = formatter.format(appointmentListQuery.getTimestamp(10));
                String end = formatter.format(appointmentListQuery.getTimestamp(11));

                weekList.add(new Appointment(appointmentId, customerId, userId, title, description, location, contact,
                        type, url, start, end));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weekList;
    }

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

    public static ObservableList<Appointment> getAppointmentsMonthly(String month) {
        System.out.println("lets see if we can get the months" + month);

        ObservableList<Appointment> appointmentsListByMonth = FXCollections.observableArrayList();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String appointmentQuery = "select type, COUNT(*) from U05wjs.appointment where monthname(start) = ? GROUP BY type";

        try{
            PreparedStatement appointmentListStatement = connectDB.prepareStatement(appointmentQuery);
            appointmentListStatement.setString(1, month);
            ResultSet appointmentListQuery = appointmentListStatement.executeQuery();


            while (appointmentListQuery.next()) {
//                String appointmentId = Integer.toString(appointmentListQuery.getInt("appointmentId"));
                String type = appointmentListQuery.getString(1);
                String count = Integer.toString(appointmentListQuery.getInt(2));
                System.out.println("seee values" + type + count);

                appointmentsListByMonth.add(new Appointment(type, count));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("this is the results back from the model" + appointmentsListByMonth);
        return appointmentsListByMonth;
    }


    public static ObservableList<Appointment> getConsultantAppointments(int consultantId) {
        ObservableList<Appointment> matchedConsultant = FXCollections.observableArrayList();

        DatabaseConnection conn = new DatabaseConnection();
        Connection connDB = conn.getConnection();

        String query = "SELECT * FROM U05wjs.appointment WHERE userId = ?";

        try{
            PreparedStatement statement = connDB.prepareStatement(query);
            statement.setInt(1, consultantId);
            ResultSet rs = statement.executeQuery();
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault());

            while (rs.next()) {
                String appointmentId = Integer.toString(rs.getInt(1));
                String customerId = Integer.toString(rs.getInt(2));
                String userId = Integer.toString(rs.getInt(3));
                String title = rs.getString(4);
                String description = rs.getString(5);
                String location = rs.getString(6);
                String contact = rs.getString(7);
                String type = rs.getString(8);
                String url = rs.getString(9);
                String start = formatter.format(rs.getDate(10));
                String end = formatter.format(rs.getDate(11));

                matchedConsultant.add(new Appointment(appointmentId, customerId, userId, title, description, location, contact,
                        type, url, start, end));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchedConsultant;
    }



//    public static ObservableList<Appointment> getCustomerCountInMonth(String month) {
//        System.out.println("lets see if we can get the months" + month);
//
//        ObservableList<Appointment> customersListMonthCount = FXCollections.observableArrayList();
//
//        DatabaseConnection connectNow = new DatabaseConnection();
//        Connection connectDB = connectNow.getConnection();
//
//        String customerQuery = "select customerId, COUNT(*) from U05wjs.appointment where monthname(start) = ? GROUP BY customerId";
//
//        try{
//            PreparedStatement customerStatement = connectDB.prepareStatement(customerQuery);
//            customerStatement.setString(1, month);
//            ResultSet customerResult = customerStatement.executeQuery();
//
//
//            while (customerResult.next()) {
//                String customerId = Integer.toString(customerResult.getInt("customerId"));
//                String count = Integer.toString(customerResult.getInt(2));
//                System.out.println("seee values" + customerId + count);
//
//                customersListMonthCount.add(new Appointment(customerId, count, ""));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("this is the results back from the model" + customersListMonthCount);
//        return customersListMonthCount;
//    }

    public static void deleteAppointment(Appointment appointment){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String deleteAppointmentQuery = "delete from U05wjs.appointment where appointmentId = ?";

        try{
            PreparedStatement appointmentStatement = connectDB.prepareStatement(deleteAppointmentQuery);
            appointmentStatement.setString(1, appointment.getAppointmentId());
            appointmentStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
