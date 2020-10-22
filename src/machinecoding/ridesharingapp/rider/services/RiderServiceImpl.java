package machinecoding.ridesharingapp.rider.services;


import machinecoding.ridesharingapp.rider.models.Rider;
import machinecoding.ridesharingapp.storage.IStorageService;

public class RiderServiceImpl implements IRiderService {
    private IStorageService storageService;

    public RiderServiceImpl(IStorageService storageService) {
        this.storageService = storageService;
    }

    public Boolean register(Rider rider) {
        this.storageService.saveRider(rider);
        return true;
    }
}
