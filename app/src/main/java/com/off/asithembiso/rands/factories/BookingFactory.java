package com.off.asithembiso.rands.factories;


import com.off.asithembiso.rands.domain.Booking;

/**
 * Created by asithembiso on 2016/09/02.
 */
public class BookingFactory {

    public static Booking createBooking(String fullName, String tableSize, String date){
        return new Booking.Builder()
                .fullName(fullName)
                .bookingDate(date)
                .tableSize(tableSize)
                .build();
    }
}
