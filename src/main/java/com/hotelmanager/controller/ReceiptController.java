package com.hotelmanager.controller;

import com.hotelmanager.data.BookedRoom;
import com.hotelmanager.data.Extra;

import java.util.List;

public class ReceiptController
{
    public static class ReceiptDetails {
        private BookedRoom bookedRoom;
        private List<Extra> extra;

        public ReceiptDetails(BookedRoom bookedRoom, List<Extra> extra) {
            this.bookedRoom = bookedRoom;
            this.extra = extra;
        }

        public BookedRoom getBookedRoom() {
            return bookedRoom;
        }

        public List<Extra> getExtra() {
            return extra;
        }

        public int calculatePrice() {
            int result = bookedRoom.getRoom().getPrice();
            for (Extra i : extra) {
                result += (i.getPrice() * i.getQuantity());
            }
            return result;
        }
    }
}
