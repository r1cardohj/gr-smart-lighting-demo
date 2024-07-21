package org.grsl.controllers;


import org.grsl.models.DeviceRuntime;
import org.grsl.schema.deviceruntime.AdjustBrightnessRequest;
import org.grsl.schema.deviceruntime.DeviceIdRequest;
import org.grsl.schema.http.BaseHttpResponse;
import org.grsl.schema.http.Code200Response;
import org.grsl.schema.http.CommonDataResponse;
import org.grsl.services.DeviceRuntimeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device/runtime")
public class DeviceRuntimeController {

    private DeviceRuntimeService deviceRuntimeService;

    public  DeviceRuntimeController(DeviceRuntimeService deviceRuntimeService) {
        this.deviceRuntimeService = deviceRuntimeService;
    }

    @GetMapping("/{deviceId:\\d+}")
    public BaseHttpResponse getDeviceRuntime(@PathVariable Long deviceId) {
        DeviceRuntime deviceRuntime = this.deviceRuntimeService.getDeviceRuntime(deviceId);
        return new CommonDataResponse<>(deviceRuntime);
    }

    @GetMapping("/")
    public BaseHttpResponse getAllDeviceRuntime() {
        return new CommonDataResponse<>(this.deviceRuntimeService.getAllDeviceRuntime());
    }

    @PostMapping("/create")
    public BaseHttpResponse createDeviceRuntime(@RequestBody  @Validated DeviceIdRequest request) {
        this.deviceRuntimeService.createDeviceRuntime(request.getLongDeviceId());
        return new Code200Response();
    }

    @PostMapping("/delete")
    public BaseHttpResponse deleteDeviceRuntime(@RequestBody @Validated DeviceIdRequest request) {
        this.deviceRuntimeService.deleteDeviceRuntime(request.getLongDeviceId());
        return new Code200Response();
    }

    @PostMapping("/control/on")
    public BaseHttpResponse turnOnDevice(@RequestBody @Validated DeviceIdRequest request) {
        this.deviceRuntimeService.turnOnDevice(request.getLongDeviceId());
        return new Code200Response();
    }

    @PostMapping("/control/off")
    public BaseHttpResponse turnOffDevice(@RequestBody @Validated DeviceIdRequest request) {
        this.deviceRuntimeService.turnOffDevice(request.getLongDeviceId());
        return new Code200Response();
    }

    @PostMapping("/control/brightness")
    public BaseHttpResponse adjustBrightness(@RequestBody @Validated AdjustBrightnessRequest request) {
        this.deviceRuntimeService.adjustBrightness(request.getLongDeviceId(), request.getBrightness());
        return new Code200Response();
    }
}
