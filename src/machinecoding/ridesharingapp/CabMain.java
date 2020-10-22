package machinecoding.ridesharingapp;

import machinecoding.ridesharingapp.booking.models.Booking;
import machinecoding.ridesharingapp.booking.service.BookingServiceImpl;
import machinecoding.ridesharingapp.booking.service.IBookingService;
import machinecoding.ridesharingapp.driver.models.Driver;
import machinecoding.ridesharingapp.driver.services.DriverServiceImpl;
import machinecoding.ridesharingapp.driver.services.IDriverService;
import machinecoding.ridesharingapp.rider.models.Rider;
import machinecoding.ridesharingapp.rider.services.IRiderService;
import machinecoding.ridesharingapp.rider.services.RiderServiceImpl;
import machinecoding.ridesharingapp.storage.IStorageService;
import machinecoding.ridesharingapp.storage.StorageServiceImpl;
import machinecoding.ridesharingapp.vehicle.models.Vehicle;
import machinecoding.ridesharingapp.vehicle.services.IVehicleService;
import machinecoding.ridesharingapp.vehicle.services.VehicleServiceImpl;

import java.util.List;


// Features offered :
// Register rider, driver, vehicle
// Search cab in 100m radius(example)
// Book cab
// Ride History
// End Trip


// StorageService : Contains maps - riderStorage, driverStorage, vehicleStorage, bookingStorage
// StorageService : saveRider, saveDriver, saveVehicle, updateLocation, book, find, rideHistory, endTrip
// BookingService : book, history, endTrip
// VehicleService : find, updateLocation, registerVehicle
// DriverService : registerDriver
// RiderService : registerRider
public class CabMain {
    private static IStorageService storageService = new StorageServiceImpl();
    private static IRiderService riderService = new RiderServiceImpl(storageService);
    private static IDriverService driverService = new DriverServiceImpl(storageService);
    private static IVehicleService vehicleService = new VehicleServiceImpl(storageService);
    private static IBookingService bookingService = new BookingServiceImpl(vehicleService, storageService);

    public static void main(String args[]) {
        Rider rider = new Rider();
        rider.setName("harsh");
        rider.setCountryCode("+91");
        rider.setPhoneNumber("910");
        riderService.register(rider);

        Driver driver = new Driver();
        driver.setName("harsh Driver");
        driver.setCountryCode("+91");
        driver.setPhoneNumber("9431");
        driverService.register(driver);

        Vehicle vehicle = new Vehicle();
        vehicle.setCarNumber("KA01HK");
        vehicle.setLat(1D);
        vehicle.setLon(1D);
        vehicleService.registerVehicle(vehicle);

        vehicle.setLat(2D);
        vehicle.setLon(2D);
        vehicleService.updateLocation(vehicle);

        bookingService.book("+91910", 1D, 2D);

        List<Booking> bookingHistory = bookingService.history("+91910");
        System.out.println("bookingHistory" + bookingHistory);
    }
}