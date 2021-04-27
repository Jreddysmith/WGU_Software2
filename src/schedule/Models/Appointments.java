package schedule.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedule.DatabaseConnection;

import java.sql.*;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Appointments {

    public static ObservableList<Appointment> appointmentsMonthly() {
        ObservableList<Appointment> monthList = FXCollections.observableArrayList();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

//        String appointmentQuery = "select * from U05wjs.appointment where start> now() - INTERVAL 12 month";
        String appointmentQuery = "select * from U05wjs.appointment where YEAR(start) = YEAR(NOW()) AND MONTH(start)=MONTH(NOW())";

        try{
            Statement appointmentListStatement = connectDB.createStatement();
            ResultSet appointmentListQuery = appointmentListStatement.executeQuery(appointmentQuery);


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
                Timestamp start = appointmentListQuery.getTimestamp(10);
                Timestamp end = appointmentListQuery.getTimestamp(11);

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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant2 = timestamp.toInstant();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

//        String appointmentQuery = "select * from U05wjs.appointment where start> now() - INTERVAL 52 week";
        String appointmentQuery = "select * from U05wjs.appointment Where WEEKOFYEAR(start) = WEEKOFYEAR(NOW())";

        try{
            Statement appointmentListStatement = connectDB.createStatement();
            ResultSet appointmentListQuery = appointmentListStatement.executeQuery(appointmentQuery);


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
                Timestamp start = appointmentListQuery.getTimestamp(10);
                Timestamp end = appointmentListQuery.getTimestamp(11);

                weekList.add(new Appointment(appointmentId, customerId, userId, title, description, location, contact,
                        type, url, start, end));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weekList;
    }

    public void addAppointment(int customerId, int userId, String title, String description, String location, String contact,
                               String type, String url, String start, String end, String activeUser) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insertAppointment = "insert into U05wjs.appointment (customerId, userId, title, description, location, contact, type, " +
                "url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate()," +
                " ?, current_timestamp(), ?)";

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
            appointmentStatement.setString(9, start);
            appointmentStatement.setString(10, end);
            appointmentStatement.setString(11, activeUser);
            appointmentStatement.setString(12, activeUser);
            appointmentStatement.executeUpdate();
            ResultSet appointmentOutput = appointmentStatement.getGeneratedKeys();

            appointmentOutput.next();
            int appointmentId = appointmentOutput.getInt(1);
//            System.out.println("this is the appointment ID: " + appointmentId);

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
                String appointmentId = Integer.toString(appointmentListQuery.getInt(1));
                String customerId = Integer.toString(appointmentListQuery.getInt(2));
                String userId = Integer.toString(appointmentListQuery.getInt(3));
                String title = appointmentListQuery.getString(4);
                String description = appointmentListQuery.getString(5);
                String location = appointmentListQuery.getString(6);
                String contact = appointmentListQuery.getString(7);
                String type = appointmentListQuery.getString(8);
                String url = appointmentListQuery.getString(9);
                Timestamp start = appointmentListQuery.getTimestamp(10);
                Timestamp end = appointmentListQuery.getTimestamp(11);

                appointmentsList.add(new Appointment(appointmentId, customerId, userId, title, description, location, contact,
                        type, url, start, end));

//                System.out.println("Just got all the appointments!!!!");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentsList;
    }

    public static ObservableList<Appointment> getAppointmentsMonthly(String month) {
//        System.out.println("lets see if we can get the months" + month);

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
//                System.out.println("seee values" + type + count);

                appointmentsListByMonth.add(new Appointment(type, count));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("this is the results back from the model" + appointmentsListByMonth);
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
                Timestamp start = rs.getTimestamp(10);
                Timestamp end = rs.getTimestamp(11);

                matchedConsultant.add(new Appointment(appointmentId, customerId, userId, title, description, location, contact,
                        type, url, start, end));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchedConsultant;
    }

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
    public void updateAppointment(int appointmentId, int customerId, int userId, String title, String description, String location, String contact,
                                         String type, String url, String start, String end, String activeUser) {

        System.out.println("lets see if the appointment Id goes here" + appointmentId);
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String formatDateTime = now.format(format);


        String updateCustomerTable = "update U05wjs.appointment set customerId=?, userId=?, title=?, description=?, location=?, contact=?," +
                "type=?, url=?, start=?, end=?, lastUpdate=?, lastUpdateBy=? where appointmentId=?";


        try {
            PreparedStatement customerTableStmt = connectDB.prepareStatement(updateCustomerTable);
            customerTableStmt.setInt(1, customerId);
            customerTableStmt.setInt(2, userId);
            customerTableStmt.setString(3, title);
            customerTableStmt.setString(4, description);
            customerTableStmt.setString(5, location);
            customerTableStmt.setString(6, contact);
            customerTableStmt.setString(7, type);
            customerTableStmt.setString(8, url);
            customerTableStmt.setString(9, start);
            customerTableStmt.setString(10, end);
            customerTableStmt.setTimestamp(11, Timestamp.valueOf(formatDateTime));
            customerTableStmt.setString(12, activeUser);
            customerTableStmt.setInt(13, appointmentId);
            customerTableStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getOverLappingDate(LocalDateTime start, LocalDateTime end) {
        Timestamp localStartToTimestamp = Timestamp.valueOf(start);
        Timestamp localEndToTimestamp = Timestamp.valueOf(end);


        DatabaseConnection connNow = new DatabaseConnection();
        Connection connDB = connNow.getConnection();

        String overLapQuery = "SELECT COUNT(1) as Count FROM U05wjs.appointment WHERE " +
                "(? >= start and ? <= end) or " +
                "(? <= start and ? >= start) or " +
                "(? <= end and ? >= end) or " +
                "(? <= start and ? >= end)";

        try{
            PreparedStatement overLapDates = connDB.prepareStatement(overLapQuery);
            overLapDates.setTimestamp(1, localStartToTimestamp);
            overLapDates.setTimestamp(2, localEndToTimestamp);
            overLapDates.setTimestamp(3, localStartToTimestamp);
            overLapDates.setTimestamp(4, localEndToTimestamp);
            overLapDates.setTimestamp(5, localStartToTimestamp);
            overLapDates.setTimestamp(6, localEndToTimestamp);
            overLapDates.setTimestamp(7, localStartToTimestamp);
            overLapDates.setTimestamp(8, localEndToTimestamp);
            ResultSet rs = overLapDates.executeQuery();
            if(rs.next()) {
                int count = rs.getInt("Count");
                if(count >= 1){
                    return 1;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
