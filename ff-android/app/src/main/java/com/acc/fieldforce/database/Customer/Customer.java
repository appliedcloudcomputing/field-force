package com.acc.fieldforce.database.Customer;

public class Customer {
    String customerName;
    String customerAddress;
    String companyName;
    String contactNumber;

    public Customer(){

    }

    public Customer(String customerName, String customerAddress, String companyName, String contactNumber) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.companyName = companyName;
        this.contactNumber = contactNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
