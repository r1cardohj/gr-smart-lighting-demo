package org.grsl.services;

import org.grsl.models.CommandType;
import org.grsl.models.commands.DeviceCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class DeviceControlService {

    private final ThreadPoolTaskExecutor executor;

    @Autowired
    public DeviceControlService(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    public void submitMutiDeviceCommands(DeviceCommand command) {
        executor.execute(() -> {
            //todo: deviceControl logic
        });
    }

    public void submitDeviceCommandSync(DeviceCommand command) {
        //todo
    }

    private Runnable buildTaskbyCommand(DeviceCommand command) {
        //todo
    }
}
