package org.grsl.services;

import org.grsl.models.DeviceRuntime;
import org.grsl.repositories.DeviceRepository;
import org.grsl.repositories.DeviceRuntimeRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceRuntimeService {

    private final DeviceRuntimeRepository deviceRuntimeRepository;
    private final DeviceRepository deviceRepository;

    public DeviceRuntimeService(DeviceRuntimeRepository deviceRuntimeRepository,
                                DeviceRepository deviceRepository) {
        this.deviceRuntimeRepository = deviceRuntimeRepository;
        this.deviceRepository = deviceRepository;
    }

    public void createDeviceRuntime(long deviceId) {
        if (!this.deviceRepository.existsById(deviceId)) {
            throw new DeviceRepository.DeviceNotFoundException();
        }
        DeviceRuntime existDeviceRuntime = this.deviceRuntimeRepository.getDeviceRuntimeByDeviceId(deviceId);
        if (existDeviceRuntime != null) {
            throw new DeviceRuntimeRepository.DeviceRuntimeIsExistException();
        }
        DeviceRuntime newDeviceRuntime = new DeviceRuntime();
        newDeviceRuntime.setDeviceId(deviceId);
        newDeviceRuntime.setBrightness(0);
        newDeviceRuntime.setStatus(0);
        this.deviceRuntimeRepository.save(newDeviceRuntime);
    }

    public void deleteDeviceRuntime(long deviceId) {
        DeviceRuntime deviceRuntime = this.getDeviceRuntime(deviceId);
        this.deviceRuntimeRepository.delete(deviceRuntime);
    }

    public DeviceRuntime getDeviceRuntime(long deviceId) {
        DeviceRuntime runtime =  this.deviceRuntimeRepository.getDeviceRuntimeByDeviceId(deviceId);
        if (runtime == null) {
            throw new DeviceRuntimeRepository.DeviceRuntimeNotFoundException();
        }
        return runtime;
    }

    public Iterable<DeviceRuntime> getAllDeviceRuntime() {
        return this.deviceRuntimeRepository.findAll();
    }


    public void turnOffDevice(long deviceId) {
        DeviceRuntime deviceRuntime = this.getDeviceRuntime(deviceId);
        if (deviceRuntime.getStatus().equals(0)) {
            throw new ControlInvalidException();
        }
        deviceRuntime.setBrightness(0);
        deviceRuntime.setStatus(0);
        this.deviceRuntimeRepository.save(deviceRuntime);
    }

    public void turnOnDevice(long deviceId) {
        DeviceRuntime deviceRuntime = this.getDeviceRuntime(deviceId);
        if (deviceRuntime.getStatus().equals(1)) {
            throw new ControlInvalidException();
        }
        deviceRuntime.setBrightness(100);
        deviceRuntime.setStatus(1);
        this.deviceRuntimeRepository.save(deviceRuntime);
    }

    public void adjustBrightness(long deviceId, int brightness) {

        if (brightness > 100 || brightness < 0) {
            throw new ControlInvalidException();
        }
        DeviceRuntime deviceRuntime = this.getDeviceRuntime(deviceId);
        if (!deviceRuntime.getStatus().equals(1)) {
            throw new ControlInvalidException();
        }
        deviceRuntime.setBrightness(brightness);
        this.deviceRuntimeRepository.save(deviceRuntime);
    }

    public Integer getStatusOnDeviceCount() {
        return this.deviceRuntimeRepository.getStatusOnDeviceCount();
    }

    public class ControlInvalidException extends RuntimeException {
        @Override
        public String toString() {
            return "your control is invalid.";
        }
    }
}
