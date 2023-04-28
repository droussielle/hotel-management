package com.hotelmanager.model;

public abstract class Property
{
    int id;
    String type;
    int price;
    public abstract int getID();
    public abstract void setID(int id);
    public abstract String getType();
    public abstract void setType(String type);
    public abstract int getPrice();
    public abstract void setPrice(int price);
}
