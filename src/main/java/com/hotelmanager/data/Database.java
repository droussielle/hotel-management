package com.hotelmanager.data;

import java.util.List;

public class Database {
    private List<Customer> customerList;
    private List<FreeRoom> freeRoomList;
    private List<BookedRoom> bookedRoomList;
    private List<Extra> extraList;
    // private Map<Integer, Receipt> receiptMap;

    public Database(List<Customer> customerList, List<FreeRoom> freeRoomList, List<Extra> extraList,
            List<BookedRoom> bookedRoomList) {
        this.customerList = customerList;
        this.freeRoomList = freeRoomList;
        this.extraList = extraList;
        this.bookedRoomList = bookedRoomList;
    }

    // Customer
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void editCustomer(int index, Customer customer) {
        customerList.set(index, customer);
    }

    public void removeCustomer(Customer customer) {
        customerList.remove(customer);
    }

    public Customer getCustomer(int index) {
        if (index >= customerList.size()) {
            return null;
        }
        return customerList.get(index);
    }

    public Customer searchCustomer(String name) {
        for (Customer customer : customerList) {
            if (name.equals(customer.getName())) {
                return customer;
            }
        }
        return null;
    }

    // FreeRoom
    public List<FreeRoom> getFreeRoomList() {
        return freeRoomList;
    }

    public void addFreeRoom(FreeRoom room) {
        freeRoomList.add(room);
    }

    public void editFreeRoom(int index, FreeRoom room) {
        freeRoomList.set(index, room);
    }

    public void removeFreeRoom(Room room) {
        for (FreeRoom i : freeRoomList) {
            if (i.getRoom().equals(room)) {
                freeRoomList.remove(i);
            }
        }
    }

    public FreeRoom getFreeRoom(int index) {
        if (index >= freeRoomList.size()) {
            return null;
        }
        return freeRoomList.get(index);
    }

    public Room searchFreeRoom(String name) {
        for (FreeRoom room : freeRoomList) {
            if (name.equals(room.getRoom().getName())) {
                return room.getRoom();
            }
        }
        return null;
    }

    // Extra
    public List<Extra> getExtraList() {
        return extraList;
    }

    public void addExtra(Extra extra) {
        extraList.add(extra);
    }

    public void editExtra(int index, Extra extra) {
        extraList.set(index, extra);
    }

    public void removeExtra(Extra extra) {
        extraList.remove(extra);
    }

    public Extra getExtra(int index) {
        if (index >= extraList.size()) {
            return null;
        }
        return extraList.get(index);
    }

    public Extra searchExtra(String name) {
        for (Extra extra : extraList) {
            if (name.equals(extra.getName())) {
                return extra;
            }
        }
        return null;
    }

    // Receipt
    // public Map<Integer, Receipt> getReceiptMap() {
    // return receiptMap;
    // }

    // public void addReceipt(Receipt receipt) {
    // receiptMap.put(receipt);
    // }

    // public void editReceipt(int id, Receipt receipt) {
    // receiptMap.put(id, receipt);
    // }

    // public void removeReceipt(Receipt receipt) {
    // receiptMap.remove(receipt.getReceiptId());
    // }

    // public Receipt getReceipt(int id) {
    // return receiptMap.get(id);
    // }

    public List<BookedRoom> getBookedRoomList() {
        return bookedRoomList;
    }

    public void addBookedRoom(BookedRoom bookedRoom) {
        bookedRoomList.add(bookedRoom);
    }

    public void editBookedRoom(int index, BookedRoom bookedRoom) {
        bookedRoomList.set(index, bookedRoom);
    }

    public void removeBookedRoom(Room room) {
        for (BookedRoom i : bookedRoomList) {
            if (i.getRoom().equals(room)) {
                bookedRoomList.remove(i);
            }
        }
    }

    public BookedRoom getBookedRoom(int index) {
        if (index >= bookedRoomList.size()) {
            return null;
        }
        return bookedRoomList.get(index);
    }

    public BookedRoom searchBookedRoom(String roomID) {
        for (BookedRoom BookedRoom : bookedRoomList) {
            if (roomID.equals(BookedRoom.getRoom().getName())) {
                return BookedRoom;
            }
        }
        return null;
    }
}