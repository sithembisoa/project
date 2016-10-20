package com.off.asithembiso.rands.factories;



import com.off.asithembiso.rands.domain.Events;

import java.util.Date;

/**
 * Created by asithembiso on 2016/08/26.
 */
public class EventFactory {

    public static Events createEvent(Date eventDate, String eventTitle, Double price){
        return new Events.Builder()
                .eventDate(eventDate)
                .eventTitle(eventTitle)
                .price(price)
                .build();
    }
}
