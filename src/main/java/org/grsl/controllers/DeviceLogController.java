package org.grsl.controllers;

import org.grsl.models.DeviceLog;
import org.grsl.schema.http.BaseHttpResponse;
import org.grsl.schema.http.CommonDataResponse;
import org.grsl.services.DeviceLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5173", "http://localhost:5173"})
@RequestMapping("device/log")
public class DeviceLogController {
    private final DeviceLogService deviceLogService;

    public DeviceLogController(DeviceLogService deviceLogService) {
        this.deviceLogService = deviceLogService;
    }

    @GetMapping("/")
    public BaseHttpResponse getAllLog() {
        List<DeviceLog> deviceLogs =  this.deviceLogService.findAllDeviceLogs();
        return new CommonDataResponse<>(deviceLogs);
    }

    @GetMapping("/{deviceId:\\d+}")
    public BaseHttpResponse getLogByDeviceId(@PathVariable long deviceId) {
        List<DeviceLog> deviceLogs = this.deviceLogService.findDeviceLogsByDeviceId(deviceId);

        return new CommonDataResponse<>(deviceLogs);
    }
}
