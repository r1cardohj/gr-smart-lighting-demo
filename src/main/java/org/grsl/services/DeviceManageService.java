package org.grsl.services;

import org.grsl.models.Device;
import org.grsl.repositories.DeviceRepository;
import org.grsl.utils.Pager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Pager pager = new Pager(page, perPage);
        return this.deviceRespository.findAllDeviceByPage(pager.getLimit(), pager.getOffset());
    }


    public void createDevice(Device device) {
        if (this.deviceRespository.existsById(device.getId()))
            throw new DeviceRepository.DeviceExistException();
        this.deviceRespository.save(device);
    }

    public void updateDevice(Device device) {
        Device baseDevice = this.findDeviceById(device.getId());
        Device mergedDevice = this.getMergedDevice(baseDevice, device);
        if (mergedDevice.equals(baseDevice))
            return;
        this.deviceRespository.save(mergedDevice);
    }

    public void deleteDeviceById(long id) {
        if (!this.deviceRespository.existsById(id))
            throw new DeviceRepository.DeviceNotFoundException();
        this.deviceRespository.deleteById(id);
    }

    public Device getMergedDevice(Device d1, Device d2) {
        Device mergedDevice = new Device();
        mergedDevice.setId(d2.getId());
        mergedDevice.setName(Optional.ofNullable(d2.getName()).orElse(d1.getName()));
        mergedDevice.setDeviceCode(Optional.ofNullable(d2.getDeviceCode()).orElse(d1.getDeviceCode()));
        mergedDevice.setSpecifications(Optional.ofNullable(d2.getSpecifications()).orElse(d1.getSpecifications()));
        mergedDevice.setPosition(Optional.ofNullable(d2.getPosition()).orElse(d1.getPosition()));
        mergedDevice.setLightingType(Optional.ofNullable(d2.getLightingType()).orElse(d1.getLightingType()));
        mergedDevice.setIsOnline(Optional.ofNullable(d2.getIsOnline()).orElse(d1.getIsOnline()));
        mergedDevice.setSerialNumber(Optional.ofNullable(d2.getSerialNumber()).orElse(d1.getSerialNumber()));
        mergedDevice.setChargeBy(Optional.ofNullable(d2.getChargeBy()).orElse(d1.getChargeBy()));
        mergedDevice.setExFactoryDate(Optional.ofNullable(d2.getExFactoryDate()).orElse(d1.getExFactoryDate()));
        mergedDevice.setExpiredDate(Optional.ofNullable(d2.getExpiredDate()).orElse(d1.getExpiredDate()));
        return mergedDevice;
    }
}
