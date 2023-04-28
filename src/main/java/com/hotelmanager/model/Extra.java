package com.hotelmanager.model;

import java.time.LocalDate;

public class Extra extends Property
{
    private String name;

    public Extra(int id, String type, String name, int price)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public int getID()
    {
        return this.id;
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public void printExtra()
    {
        System.out.println(getID() + "\t" + getName() + "\t" + getPrice());
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    // public void setProperty(ArrayList<Property> property) {
    // this.property=property;
    // }
    // public void addExtra(int id, String name, String type, int price) {
    // property.add(new Property(id, name, type, price));
    // }

    // public void editExtra(int id, String name, String type, int price) {
    // for (Property i : property) {
    // if (i.getId() == id) {
    // i.setName(name);
    // i.setPrice(price);
    // i.setType(type);
    // }
    // }
    // }

    // public void removeExtra(int id) {
    // for (Property i : property) {
    // if (i.getId() == id) {
    // property.remove(i);
    // }
    // }
    // }
}
