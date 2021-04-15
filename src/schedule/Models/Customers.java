package schedule.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedule.DatabaseConnection;

import java.io.IOException;
import java.sql.*;

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
                System.out.println(customerId + customerName + customerAddressId + customerActive);
                System.out.println("trying this");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customerList;
    }

    public static void deleteCustomer (Customer customer) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String deleteQuery = "delete from U05wjs.customer Where customerId = ?";
        try {
            PreparedStatement customerStatement = connectDB.prepareStatement(deleteQuery);
            customerStatement.setString(1, customer.getCustomerId());
            customerStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getModifyCustomer(Customer customer) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

//        String customerQuery = "select c.customerId as customerId, cu.countryId as countryId,  ci.cityId as cityId, " +
//                "a.addressId as addressId from U05wjs.customer as c join U05wjs.country cu on c.addressId=";

        String customerQuery = "select U";
        try {
            Statement getCustomerStatement= connectDB.createStatement();
            ResultSet rs = getCustomerStatement.executeQuery(customerQuery);

            while (rs.next()) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
