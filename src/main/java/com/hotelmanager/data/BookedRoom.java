package com.hotelmanager.data;
public class BookedRoom {
    private Customer customer;
    private Room room;
    private boolean checkAvailable = false;

    public BookedRoom(Customer customer, Room room) {
        this.customer = customer;
        this.room = room;
        checkAvailable = false;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }
}