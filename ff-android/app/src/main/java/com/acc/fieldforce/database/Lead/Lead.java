package com.acc.fieldforce.database.Lead;

public class Lead {

    String companyName;
    String companyAddress;
    String contactPerson;
    String contactNumber;

    public Lead (){

    }

    public Lead(String companyName, String companyAddress, String contactPerson, String contactNumber) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.contactPerson = contactPerson;
        this.contactNumber = contactNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
