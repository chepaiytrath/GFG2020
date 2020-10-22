package machinecoding.ridesharingapp.driver.services;

import machinecoding.ridesharingapp.driver.models.Driver;
import machinecoding.ridesharingapp.storage.IStorageService;

public class DriverServiceImpl implements IDriverService {
    private IStorageService storageService;

    public DriverServiceImpl(IStorageService storageService) {
        this.storageService = storageService;
    }

    public Boolean register(Driver driver) {
        this.storageService.saveDriver(driver);
        return true;
    }
}
