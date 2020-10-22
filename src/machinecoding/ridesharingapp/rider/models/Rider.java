package machinecoding.ridesharingapp.rider.models;

import java.util.List;

public class Rider extends PersonalInfo {
    private List<String> bookingHistory;        // List of historical booking IDs

    public List<String> getBookingHistory() {
        return bookingHistory;
    }

    public void setBookingHistory(List<String> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }
}
