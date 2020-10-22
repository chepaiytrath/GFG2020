package machinecoding.ridesharingapp.vehicle.services;


import machinecoding.ridesharingapp.vehicle.models.Vehicle;

public interface IVehicleService {
    Boolean registerVehicle(Vehicle vehicle);

    Boolean updateLocation(Vehicle vehicle);

    Vehicle find(Double lat, Double lon);
}
