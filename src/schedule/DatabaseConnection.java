package schedule;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "U05wjs";
        String databaseUser = "U05wjs";
        String databasePassword = "53688629103";
        String url = "jdbc:mysql://wgudb.ucertify.com?preserveInstants=true&connectionTimeZone=SERVER";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
//            System.out.println("Connection was good, Dude!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
