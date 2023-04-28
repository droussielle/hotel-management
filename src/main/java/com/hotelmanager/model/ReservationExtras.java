package com.hotelmanager.model;

public class ReservationExtras extends Extra
{
    private int quantity;
    public ReservationExtras(int id, String type, String name, int price, int quantity)
    {
        super(id, type, name, price);
        this.quantity = quantity;
    }
}
