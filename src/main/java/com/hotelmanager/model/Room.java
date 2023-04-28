package com.hotelmanager.model;

public class Room
{
    private int id;
    private String type;
    private int price;

    public Room(int id, String type, int price)
    {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public int getID()
    {
        return this.id;
    }

    // public void setId(int id) {
    // this.id = id;
    // }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public String getType()
    {
        return this.type;
    }

    public void printRoom()
    {
        System.out.println(getID() + "\t" + getType() + "\t" + getPrice());
    }

}
