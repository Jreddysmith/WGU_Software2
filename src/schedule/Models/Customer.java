package schedule.Models;

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

    public Customer(String customerId, String customerName, String address, String address2, String city, String country, String postalCode, String phone, String active) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.active = active;
    }

    public Customer(String customerId, String customerName, String customerAddressId, String active) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddressId = customerAddressId;
        this.active = active;
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
}
