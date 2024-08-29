package org.grsl.components;

import org.grsl.services.DeviceRuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleTaskConfigs {

    @Autowired
    DeviceRuntimeService deviceRuntimeService;

    @Scheduled(fixedDelay = 30000)
    public void backupAllDeviceRuntime() {
        this.deviceRuntimeService.backupAll();
    }
}
