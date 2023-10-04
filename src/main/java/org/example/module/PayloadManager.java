package org.example.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.payload.Booking;
import org.example.payload.Bookingdates;


public class PayloadManager {

    public static ObjectMapper objectMapper;

    public static String createPayload() throws JsonProcessingException {
        Booking booking = new Booking();
        booking.setFirstname("Arshie");
        booking.setLastname("Brown");
        booking.setTotalprice(100);
        booking.setDepositpaid((true));
        booking.setAdditionalneeds("Breakfast");
        Bookingdates bookingDates = new Bookingdates();
        bookingDates.setCheckin("2019-01-01");
        bookingDates.setCheckout("2019-06-05");
        booking.setBookingdates(bookingDates);
        System.out.println("POJO PAYLOAD------");
        objectMapper = new ObjectMapper();
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }

    public static String updatePayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Danika");
        booking.setLastname("Moretti");
        booking.setTotalprice(199);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Breakfast, lunch");
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2020-10-01");
        bookingdates.setCheckout("2020-10-01");
        booking.setBookingdates(bookingdates);
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }

    public String updatedPayload(){
        return null;
    };
}
