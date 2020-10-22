package machinecoding.ridesharingapp.booking.models;

public class Booking {
    private String bookingId;
    private String riderUserId;
    private String carNumber;
    private long startTime;
    private long endTime;
    private String status;

    public Booking() {
    }

    public Booking(String bookingId, String riderUserId, String carNumber, long startTime, long endTime, String status) {
        this.bookingId = bookingId;
        this.riderUserId = riderUserId;
        this.carNumber = carNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getRiderUserId() {
        return riderUserId;
    }

    public void setRiderUserId(String riderUserId) {
        this.riderUserId = riderUserId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", riderUserId='" + riderUserId + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                '}';
    }
}
