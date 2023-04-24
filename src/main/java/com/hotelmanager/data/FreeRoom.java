package com.hotelmanager.data;
public class FreeRoom {

    private Room room;
    private boolean available;

    public FreeRoom(Room room) {
        this.room = room;
        this.available = true;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean getStatus() {
        return available;
    }

    public void setStatus(boolean available) {
        this.available = available;
    }
}
