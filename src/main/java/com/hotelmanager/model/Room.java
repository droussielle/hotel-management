package com.hotelmanager.model;

public class Room extends Property
{
    private int status;

    public Room(int id, String type, int price)
    {
        this.id = id;
        this.type = type;
        this.price = price;
        this.status = 1;
    }
    public Room(int id, String type, int price, int status)
    {
        this.id = id;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    public int getID()
    {
        return id;
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
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
