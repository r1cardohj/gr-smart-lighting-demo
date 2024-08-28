package org.grsl.services;

import org.grsl.models.DeviceLog;
import org.grsl.repositories.DeviceLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceLogService {
    private final DeviceLogRepository deviceLogRepository;

    public DeviceLogService(DeviceLogRepository deviceLogRepository) {
        this.deviceLogRepository = deviceLogRepository;
    }

    public void log(DeviceLog deviceLog) {
        this.deviceLogRepository.save(deviceLog);
    }

    public List<DeviceLog> findDeviceLogsByDeviceId(long deviceId) {
        return this.deviceLogRepository.findDeviceLogsByDeviceId(deviceId);
    }

    public List<DeviceLog> findAllDeviceLogs() {
        return this.deviceLogRepository.findAllDeviceLogs();
    }
}
