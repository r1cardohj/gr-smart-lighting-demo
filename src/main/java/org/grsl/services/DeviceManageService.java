package org.grsl.services;

import org.grsl.models.Device;
import org.grsl.repositories.DeviceRepository;
import org.grsl.utils.Pagger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceManageService {
    private final DeviceRepository deviceRespository;

    public DeviceManageService(DeviceRepository deviceRespository) {
        this.deviceRespository = deviceRespository;
    }

    public Device findDeviceById(long id) {
        return this.deviceRespository.findById(id).orElseThrow(DeviceRepository.DeviceNotFoundException::new);
    }

    public List<Device> findDeviceByPage(Integer page, Integer perPage) {
        Pagger pagger = new Pagger(page, perPage);
        return this.deviceRespository.findAllDeviceByPage(pagger.getLimit(), pagger.getOffset());
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
