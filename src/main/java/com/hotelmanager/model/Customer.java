package com.hotelmanager.model;
public class Customer {
    // private int customerId;
    private String name;
    private String phoneNumber;
    private String governmentId;

    
    public Customer(String name, String phoneNumber, String governmentId) {
        // this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.governmentId = governmentId;
    }

    // public int getCustomerId() {
    // return customerId;
    // }

    // public void setCustomerId(int customerId) {
    // this.customerId = customerId;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(String governmentId) {
        this.governmentId = governmentId;
    }
}
