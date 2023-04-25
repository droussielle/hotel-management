package com.hotelmanager.data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Receipt {
    private String hotelName = "Khach san";
    private LocalDate date;
    private int receiptId;
    private ReceiptDetails receiptDetails;
    private SimpleDateFormat format;

    public Receipt(int receiptId, ReceiptDetails receiptDetails, SimpleDateFormat format) {
        this.receiptId = receiptId;
        this.receiptDetails = receiptDetails;
        this.date = LocalDate.now();
        this.format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public int getRecreiptId() {
        return receiptId;
    }

    public void setRecreiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public ReceiptDetails getRecreiptDetails() {
        return receiptDetails;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***********************************\n");
        sb.append("*                                 *\n");
        sb.append("*              INVOICE            *\n");
        sb.append("*                                 *\n");
        sb.append("***********************************\n");
        sb.append("\n");
        sb.append("Hotel Name: ").append(hotelName).append("\n");
        sb.append("\n");
        sb.append("Date: ").append(format.format(date)).append("\n");
        sb.append("\n");
        sb.append("Room No.   \tCheckIn Date\tCheckOut Date\tPrice\n");
        sb.append("-------------------------------------------------\n");
        sb.append(receiptDetails.getBookedRoom().getRoom().getName()).append("\t")
                .append(format.format(receiptDetails.getBookedRoom().getCheckInDate())).append("\t")
                .append(format.format(receiptDetails.getBookedRoom().getCheckOutDate())).append("\t")
                .append("$").append(receiptDetails.getBookedRoom().getRoom().getPrice()).append("\n");
        sb.append("Extra Name \tQuantity \tDate buy \tPrice\n");
        for (Extra extra : receiptDetails.getExtra()) {
            sb.append(extra.getName()).append("\t")
                    .append(format.format(extra.getDateBuy())).append("\t")
                    .append("VND").append(extra.getPrice()).append("\n");
        }
        sb.append("-------------------------------------------------\n");
        sb.append("Total:\t\t\t\t\t").append("VND").append(receiptDetails.calculatePrice()).append("\n");
        return sb.toString();
    }
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