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
}
