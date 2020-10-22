package machinecoding.ridesharingapp.vehicle.models;

public class Vehicle {
    private String carNumber;
    private Double lat;
    private Double lon;
    private String type;
    private Boolean isAvailable;
    private String driverId;

    public Vehicle() {
    }

    public Vehicle(String carNumber, Double lat, Double lon, String type, Boolean isAvailable, String driverId) {
        this.carNumber = carNumber;
        this.lat = lat;
        this.lon = lon;
        this.type = type;
        this.isAvailable = isAvailable;
        this.driverId = driverId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "carNumber='" + carNumber + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", type='" + type + '\'' +
                ", isAvailable=" + isAvailable +
                ", driverId='" + driverId + '\'' +
                '}';
    }
}
