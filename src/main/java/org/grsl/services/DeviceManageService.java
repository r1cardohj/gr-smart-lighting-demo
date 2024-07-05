package org.grsl.services;

import org.grsl.models.Device;
import org.grsl.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceManageService {
    private DeviceRepository deviceRespository;

    public DeviceManageService(DeviceRepository deviceRespository) {
        this.deviceRespository = deviceRespository;
    }

    public Device findDeviceById(long id) {
        return this.deviceRespository.findById(id).orElseThrow(DeviceRepository.DeviceNotFoundException::new);
    }

    public void createDevice(Device device) {
        if (this.deviceRespository.existsById(device.getId()))
            throw new DeviceRepository.DeviceExistException();
        this.deviceRespository.save(device);
    }

    public void updateDevice(Device device) {
        if (!this.deviceRespository.existsById(device.getId()))
            throw new DeviceRepository.DeviceNotFoundException();
        this.deviceRespository.save(device);
    }

    public void deleteDeviceById(long id) {
        if (!this.deviceRespository.existsById(id))
            throw new DeviceRepository.DeviceNotFoundException();
        this.deviceRespository.deleteById(id);
    }
}
