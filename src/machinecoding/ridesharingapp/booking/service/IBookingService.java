package machinecoding.ridesharingapp.booking.service;

import machinecoding.ridesharingapp.booking.models.Booking;

import java.util.List;

public interface IBookingService {
    Booking book(String riderUserId, Double lat, Double lon);

    List<Booking> history(String riderUserId);

    Boolean endTrip(Long timeStamp, String bookingId);
}
