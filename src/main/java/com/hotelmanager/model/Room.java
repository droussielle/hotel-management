package com.hotelmanager.model;

public class Room {
    // private int id;
    private String name;
    private RoomType type;
    private int price;

    public enum RoomType {
        Single, Double
    };

    public Room(String name, RoomType type, int price) {
        // this.id = id;
        this.type = type;
        this.name = name;
        this.type = type;
    }

    // public int getId() {
    // return id;
    // }

    // public void setId(int id) {
    // this.id = id;
    // }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
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

}
