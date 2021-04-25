package schedule.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedule.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.sql.Timestamp;

public class Customers {

    public void addCustomer(String name, String address1, String address2, String city, String country, String zipcode, String number, int active) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insertCountry = "insert into U05wjs.country (country, createDate, createdBy, lastUpdate, lastUpdateBy) " +
                            "values (?, sysdate(), 'nobody', current_timestamp() , 'nobody')";

        String insertAddress = "insert into U05wjs.address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, LastUpdateBy)" +
                "values (?, ?, ?, ?, ?, sysdate() , ?, current_timestamp(), ?)";

        String insertCity = "insert into U05wjs.city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "value (?, ?, sysdate(), ?, current_timestamp(), ?)";

        String insertCustomer = "insert into U05wjs.customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) " +
                "value (?, ?, ?, sysdate(), ?, current_timestamp(), ?)";

        try {

            // For Country query.
            PreparedStatement countryStatement = connectDB.prepareStatement(insertCountry, Statement.RETURN_GENERATED_KEYS);
            countryStatement.setString(1, country);
            countryStatement.executeUpdate();
            ResultSet queryOutput = countryStatement.getGeneratedKeys();
            queryOutput.next();
            int countryId = queryOutput.getInt(1);
            System.out.println("country ID: " + countryId);

            //City Query

            PreparedStatement cityStatement = connectDB.prepareStatement(insertCity, Statement.RETURN_GENERATED_KEYS);
            cityStatement.setString(1, city);
            cityStatement.setInt(2, countryId);
            cityStatement.setString(3, "me");
            cityStatement.setString(4, "me");
            cityStatement.executeUpdate();
            ResultSet cityQueryOutput = cityStatement.getGeneratedKeys();

            cityQueryOutput.next();
            int cityId = cityQueryOutput.getInt(1);
            System.out.println("city ID: " + cityId);


            // Address Query
            PreparedStatement addressStatement = connectDB.prepareStatement(insertAddress, Statement.RETURN_GENERATED_KEYS);
            addressStatement.setString(1, address1);
            addressStatement.setString(2, address2);
            addressStatement.setInt(3, cityId);
            addressStatement.setString(4, zipcode);
            addressStatement.setString(5, number);
            addressStatement.setString(6, "Me");
            addressStatement.setString(7, "ME");
            addressStatement.executeUpdate();
            ResultSet addressQueryOutput = addressStatement.getGeneratedKeys();
            addressQueryOutput.next();
            int addressId = addressQueryOutput.getInt(1);
            System.out.println("Address ID: " + addressId);

            // Customer Query
            PreparedStatement customerStatement = connectDB.prepareStatement(insertCustomer, Statement.RETURN_GENERATED_KEYS);
            customerStatement.setString(1, name);
            customerStatement.setInt(2, addressId);
            customerStatement.setInt(3, active);
            customerStatement.setString(4, "me");
            customerStatement.setString(5, "me");
            customerStatement.executeUpdate();
            ResultSet customerQueryOutput = customerStatement.getGeneratedKeys();
            customerQueryOutput.next();
            int customerId = customerQueryOutput.getInt(1);
            System.out.println("Customer ID: " + customerId);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Customer getSingleCustomer(int custId) {

        DatabaseConnection connNow = new DatabaseConnection();
        Connection connDB = connNow.getConnection();

        String customerIdQuery = "select * from U05wjs.customer where customerId = ?";

        try{
            PreparedStatement singCust = connDB.prepareStatement(customerIdQuery);
            singCust.setInt(1, custId);
            ResultSet rs = singCust.executeQuery();

            if(rs.next()) {
                String customerId = Integer.toString(rs.getInt("customerId"));
                String customerName = rs.getString("customerName");

                Customer singleCustomer = new Customer(customerId, customerName);
                return singleCustomer;
            }
            throw new RuntimeException("Customer not found: " + custId);


        } catch (Exception e) {
           throw new RuntimeException(e);
        }


    }

    public static ObservableList<Customer> getCustomers() {

        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String customerQuery = "select * from U05wjs.customer as c " +
                "join U05wjs.address a on a.addressId=c.addressId " +
                "join U05wjs.city ci on ci.cityId=a.cityId " +
                "join U05wjs.country cu on cu.countryId=ci.countryId";
        try{
            Statement customerListStatement = connectDB.createStatement();
            ResultSet customerListQuery = customerListStatement.executeQuery(customerQuery);

            while (customerListQuery.next()) {

                String customerId = Integer.toString(customerListQuery.getInt("customerId"));
                String customerName = customerListQuery.getString("customerName");
                String customerAddressId = Integer.toString(customerListQuery.getInt("addressId"));
                String customerActive = Integer.toString(customerListQuery.getInt("active"));
                String countryId = Integer.toString(customerListQuery.getInt("countryId"));
                String cityId = Integer.toString(customerListQuery.getInt("cityId"));
                String addressId = Integer.toString(customerListQuery.getInt("addressId"));
                String address = customerListQuery.getString("address");
                String address2 = customerListQuery.getString("address2");
                String postalCode = customerListQuery.getString("postalCode");
                String phone = customerListQuery.getString("phone");
                String city = customerListQuery.getString("city");
                String country = customerListQuery.getString("country");

                customerList.add(new Customer(customerId, customerName, customerAddressId, address, address2, city, country,
                        postalCode, phone, customerActive, countryId, cityId, addressId));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customerList;
    }

    public static void deleteCustomer (Customer customer) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String deleteCustomerTable = "delete from U05wjs.customer Where customerId = ?";

        String deleteAddressTable = "delete from U05wjs.address where addressId = ?";

//        String deleteCityTable = "delete from U0wjs.city where cityId = ?";

//        String deleteCountryTable = "delete from U0wjs.country where country = ?";

        try {
            PreparedStatement customerStatement = connectDB.prepareStatement(deleteCustomerTable);
            customerStatement.setString(1, customer.getCustomerId());
            System.out.println(customer.getCustomerId());
            customerStatement.executeUpdate();

            PreparedStatement addressTable = connectDB.prepareStatement(deleteAddressTable);
            addressTable.setInt(1, Integer.parseInt(customer.getAddressId()));
            System.out.println(customer.getAddressId());
            addressTable.executeUpdate();

//            PreparedStatement cityTable = connectDB.prepareStatement(deleteCityTable);
//            cityTable.setInt(1, Integer.parseInt(customer.getCityId()));
//            System.out.println(customer.getCityId());
//            cityTable.executeUpdate();

//            PreparedStatement countryTable = connectDB.prepareStatement(deleteCountryTable);
//            countryTable.setInt(1, Integer.parseInt(customer.getCountryId()));
//            System.out.println(customer.getCountryId());
//            countryTable.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public static ObservableList<Customer> getCustomerCountInMonth(String month) {
        ObservableList<Customer> customersListMonthCount = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String customerQuery = "SELECT DISTINCT customerId, customerName FROM U05wjs.customer WHERE monthname(createDate) = ?";

        try{
            PreparedStatement customerStatement = connectDB.prepareStatement(customerQuery);
            customerStatement.setString(1, month);
            ResultSet customerResult = customerStatement.executeQuery();

            while (customerResult.next()) {
                String customerId = Integer.toString(customerResult.getInt("customerId"));
                String customerName = customerResult.getString("customerName");
                customersListMonthCount.add(new Customer(customerId, customerName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersListMonthCount;
    }


    public static void updateCustomer(int customerId, int addressId, String customerName, String customerAddress1, String customerAddress2,
                                      String customerCity, String customerCountry, String customerZipcode, String customerNumber,
                                      int customerActive, int cityId, int countryId) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String formatDateTime = now.format(format);


        String updateCustomerTable = "update U05wjs.customer set customerName=?, active=?, lastUpdate=?, lastUpdateBy=?" +
                " where customerId=?";

        String updateAddressTable = "update U05wjs.address set address=?, address2=?, postalCode=?, phone=?, " +
                "lastUpdate=?, lastUpdateBy=? where addressId=?";

        String updateCityTable = "update U05wjs.city set city=?, lastUpdate=?, lastUpdateBy=? where cityId=?";

        String updateCountryTable = "update U05wjs.country set country=?, lastUpdate=?, lastUpdateBy=? where countryId=?";

        try {
            PreparedStatement customerTableStmt = connectDB.prepareStatement(updateCustomerTable);
            customerTableStmt.setString(1, customerName);
            customerTableStmt.setInt(2, customerActive);
            customerTableStmt.setTimestamp(3, Timestamp.valueOf(formatDateTime));
            customerTableStmt.setString(4, "Super Loser");
            customerTableStmt.setInt(5, customerId);
            customerTableStmt.executeUpdate();

            PreparedStatement addressTableStmt = connectDB.prepareStatement(updateAddressTable);
            addressTableStmt.setString(1, customerAddress1);
            addressTableStmt.setString(2, customerAddress2);
            addressTableStmt.setString(3, customerZipcode);
            addressTableStmt.setString(4, customerNumber);
            addressTableStmt.setTimestamp(5, Timestamp.valueOf(formatDateTime));
            addressTableStmt.setString(6, "mee");
            addressTableStmt.setInt(7, addressId);
            addressTableStmt.executeUpdate();

            PreparedStatement cityTableStmt = connectDB.prepareStatement(updateCityTable);
            cityTableStmt.setString(1, customerCity);
            cityTableStmt.setTimestamp(2, Timestamp.valueOf(formatDateTime));
            cityTableStmt.setString(3, "meeee");
            cityTableStmt.setInt(4, cityId);
            cityTableStmt.executeUpdate();

            PreparedStatement countryTableStmt = connectDB.prepareStatement(updateCountryTable);
            countryTableStmt.setString(1, customerCountry);
            countryTableStmt.setTimestamp(2, Timestamp.valueOf(formatDateTime));
            countryTableStmt.setString(3, "youngman");
            countryTableStmt.setInt(4, countryId);
            countryTableStmt.executeUpdate();

            System.out.println("lets hope this updates");




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
