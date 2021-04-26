package schedule.Models;

import javafx.scene.control.Alert;
import schedule.exceptions.ValidationException;

public class Customer {

    private String customerId;
    private String customerName;
    private String customerAddressId;
    private String address;
    private String address2;
    private String city;
    private String country;
    private String postalCode;
    private String phone;
    private String active;
    private String countryId;
    private String cityId;
    private String addressId;
    private String activeUser;
    public Customer() {

    }

    public Customer(String customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public Customer(String customerId, String customerName, String customerAddressId, String address, String address2,
                    String city, String country, String postalCode, String phone, String active, String countryId, String cityId, String addressId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddressId = customerAddressId;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.active = active;
        this.countryId = countryId;
        this.cityId = cityId;
        this.addressId = addressId;
    }

    public Customer(String customerId, String customerName, String address, String address2, String city, String country, String postalCode, String phone, String active, String activeUser) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.active = active;
        this.activeUser = activeUser;
    }

    public Customer(String customerId, String customerName, String customerAddressId, String active) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddressId = customerAddressId;
        this.active = active;
    }

    public Customer(String customerName, String customerAddress1, String customerAddress2, String customerCity,
                    String customerCountry, String customerZipcode, String customerNumber, int customerActive, String user) {
        this.customerName = customerName;
        this.address = customerAddress1;
        this.address2 = customerAddress2;
        this.city = customerCity;
        this.country = customerCountry;
        this.postalCode = customerZipcode;
        this.phone = customerNumber;
        this.active = String.valueOf(customerActive);
        this.activeUser = user;


    }

    public Customer(int customerId, String customerName, String customerAddress1, String customerAddress2, String customerCity,
                    String customerCountry, String customerZipcode, String customerNumber, int customerActive, int customerCityId,
                    int customerCountryId, String user) {
        this.customerId = String.valueOf(customerId);
        this.customerName = customerName;
        this.address = customerAddress1;
        this.address2 = customerAddress2;
        this.city = customerCity;
        this.country = customerCountry;
        this.postalCode = customerZipcode;
        this.phone = customerNumber;
        this.active = String.valueOf(customerActive);
        this.activeUser = user;
        this.countryId = String.valueOf(customerCountryId);
        this.cityId = String.valueOf(customerCityId);

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(String customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(String activeUser) {
        this.activeUser = activeUser;
    }

    public void validate() throws ValidationException {
        if(getCustomerName().isEmpty() || getCustomerName().length() < 5){
            throw new ValidationException("Name can not be empty or less than 5 Characters");
        }
        if(getAddress().isEmpty() || getAddress().length() < 5){
            throw new ValidationException("Address address line 1 can not be empty or less than 5 Characters");
        }
        if (getCity().isEmpty() || getCity().length() < 5){
            throw new ValidationException("City can not be empty or less than 5 Characters");
        }
        if(getCountry().isEmpty() || getCountry().length() < 2) {
            throw new ValidationException("Country can not be empty or less than 2 Characters");
        }
        if(getPostalCode().isEmpty() || getPostalCode().length() < 5) {
            throw new ValidationException("Zipcode can not be empty or less than 5 Characters");
        }
        if(getPhone().isEmpty() || getPhone().length() < 10) {
            throw new ValidationException("Phone number can not be empty or less than 10 Characters");
        }
    }
}
