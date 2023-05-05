package com.hotelmanager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation
{
    private int id;
    private String name;
    private String phoneNumber;
    private String paymentMethod;
    private String cardNumber;
    private int roomID;
    private int duration;
    private LocalDateTime time;
    private int status;

    public Reservation(int id, String name, String phoneNumber, String paymentMethod, String cardNumber,
                       int roomID, int duration, long time, int status)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.roomID = roomID;
        this.duration = duration;
        this.time = LocalDateTime.ofEpochSecond(time, 0, OffsetDateTime.now().getOffset());
        this.status = status;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public int getRoomID()
    {
        return roomID;
    }

    public void setRoomID(int roomID)
    {
        this.roomID = roomID;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public LocalDateTime getTime()
    {
        return time;
    }

    public String getTimeString()
    {
        return this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    public void setTime(LocalDateTime time)
    {
        this.time = time;
    }

    public int getStatus()
    {
        return status;
    }

    public String getStatusString()
    {
        String s;
        return s = (status == 0) ? "Staying" : "Checked out";
    }
    public void setStatus(int status)
    {
        this.status = status;
    }

    public void printReservation()
    {
        System.out.println(getId() + "\t" + getName() + "\t" + getPhoneNumber() + "\t" + getPaymentMethod()
                + "\t" + getCardNumber() + "\t" + getRoomID() + "\t" + getDuration() + "\t" + getTimeString() + "\t" + getStatus());
    }

    public boolean equals(Reservation rs) throws Exception
    {
        boolean ret = false;
        try
        {
            ret = getName().equals(rs.getName()) && getPhoneNumber().equals(rs.getPhoneNumber()) &&
                    getPaymentMethod().equals(rs.getPaymentMethod()) && getCardNumber().equals(rs.getCardNumber()) &&
                    getRoomID() == rs.getRoomID() && getDuration() == rs.getDuration();
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
        return ret;
    }
//    public int getRecreiptId() {
//        return receiptId;
//    }
//
//    public void setRecreiptId(int receiptId) {
//        this.receiptId = receiptId;
//    }
//
//    public ReservationController.ReceiptDetails getRecreiptDetails() {
//        return receiptDetails;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("***********************************\n");
//        sb.append("*                                 *\n");
//        sb.append("*              INVOICE            *\n");
//        sb.append("*                                 *\n");
//        sb.append("***********************************\n");
//        sb.append("\n");
//        sb.append("Hotel Name: ").append(hotelName).append("\n");
//        sb.append("\n");
//        sb.append("Date: ").append(format.format(date)).append("\n");
//        sb.append("\n");
//        sb.append("Room No.   \tCheckIn Date\tCheckOut Date\tPrice\n");
//        sb.append("-------------------------------------------------\n");
//        sb.append(receiptDetails.getBookedRoom().getRoom().getName()).append("\t")
//                .append(format.format(receiptDetails.getBookedRoom().getCheckInDate())).append("\t")
//                .append(format.format(receiptDetails.getBookedRoom().getCheckOutDate())).append("\t")
//                .append("$").append(receiptDetails.getBookedRoom().getRoom().getPrice()).append("\n");
//        sb.append("Extra Name \tQuantity \tDate buy \tPrice\n");
//        for (Extra extra : receiptDetails.getExtra()) {
//            sb.append(extra.getName()).append("\t")
//                    .append(format.format(extra.getDateBuy())).append("\t")
//                    .append("VND").append(extra.getPrice()).append("\n");
//        }
//        sb.append("-------------------------------------------------\n");
//        sb.append("Total:\t\t\t\t\t").append("VND").append(receiptDetails.calculatePrice()).append("\n");
//        return sb.toString();
//    }
    // public void printReceipt(int receiptId, ReceiptDetails receiptDetails) {
    // System.out.println("ID: " + receiptId);
    // System.out.println("Name: " +
    // receiptDetails.getBookedRoom().getCustomer().getName());
    // System.out.println("Room: " +
    // receiptDetails.getBookedRoom().getRoom().getName());
    // System.out.println("Price: " +
    // receiptDetails.getBookedRoom().getRoom().getPrice());
    // System.out.println("Extra:");
    // for (Extra i : receiptDetails.getExtra()) {
    // System.out.println(i.getName() + " " + i.getQuantity() + " " + i.getPrice() *
    // i.getQuantity());
    // }
    // System.out.println("Receipt Total: " + receiptDetails.calculatePrice());
    // }
}