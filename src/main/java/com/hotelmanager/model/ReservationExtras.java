package com.hotelmanager.model;

public class ReservationExtras extends Extra
{
    private int quantity;
    public ReservationExtras(int id, String type, String name, int price, int quantity)
    {
        super(id, type, name, price);
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return this.quantity;
    }
    public void printExtra()
    {
        System.out.println(getID() + "\t" + getType() + "\t" + getName() + "\t" + getPrice() + "\t" + getQuantity());
    }
}
