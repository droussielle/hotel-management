package com.hotelmanager.data;

public class Receipt {
    private int receiptId;
    private ReceiptDetails receiptDetails;

    public Receipt(int receiptId, ReceiptDetails receiptDetails) {
        this.receiptId = receiptId;
        this.receiptDetails = receiptDetails;
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