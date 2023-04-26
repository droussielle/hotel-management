package com.hotelmanager.model;

public class Customer
{
    // private int customerId;
    private int id;
    private String name;
    private String phoneNumber;
    private String governmentId;

    public Customer(int id, String name, String phoneNumber, String governmentId)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.governmentId = governmentId;
    }

    public int getCustomerId()
    {
        return this.id;
    }

    public void setCustomerId(int customerId)
    {
        this.id = customerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getGovernmentId()
    {
        return governmentId;
    }

    public void setGovernmentId(String governmentId)
    {
        this.governmentId = governmentId;
    }
}
