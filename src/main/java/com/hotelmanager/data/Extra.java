package com.hotelmanager.data;

import java.time.LocalDate;

public class Extra {
    private String name;
    private int price;
    private int quantity;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Extra(String name, int price, int quantity, LocalDate checkInDate, LocalDate checkOutDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
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
