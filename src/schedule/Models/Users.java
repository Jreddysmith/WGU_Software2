package schedule.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedule.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Locale;

public class Users {

    public static ObservableList<User> getAllUsers() {
        ObservableList<User> userList = FXCollections.observableArrayList();

        DatabaseConnection conn = new DatabaseConnection();
        Connection connDB = conn.getConnection();

        String query = "SELECT * FROM U05wjs.user";

        try {
            PreparedStatement statement = connDB.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault());

            while (rs.next()) {
                String userId = Integer.toString(rs.getInt(1));
                String userName = rs.getString(2);
                String userPassword = rs.getString(3);
                String userActive = Integer.toString(rs.getInt(4));

                userList.add(new User(userId, userName, userPassword, userActive));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static User getSingleUser(int userId) {

        DatabaseConnection connNow = new DatabaseConnection();
        Connection connDB = connNow.getConnection();

        String userIdQuery = "select * from U05wjs.user where userId = ?";

        try{
            PreparedStatement singUser = connDB.prepareStatement(userIdQuery);
            singUser.setInt(1, userId);
            ResultSet rs = singUser.executeQuery();

            if(rs.next()) {
                String customerId = Integer.toString(rs.getInt("userId"));
                String customerName = rs.getString("userName");

                User singleUser = new User(customerId, customerName);
                return singleUser;
            }
            throw new RuntimeException("Customer not found: " + singUser);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
