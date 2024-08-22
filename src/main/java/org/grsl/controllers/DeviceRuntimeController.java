package org.grsl.controllers;


import org.grsl.models.DeviceRuntime;
import org.grsl.schema.deviceruntime.AdjustBrightnessRequest;
import org.grsl.schema.deviceruntime.DeviceIdRequest;
import org.grsl.schema.http.BaseHttpResponse;
import org.grsl.schema.http.Code200Response;
import org.grsl.schema.http.CommonDataResponse;
import org.grsl.services.DeviceRuntimeService;
import org.grsl.services.SSEManageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/device/runtime")
@CrossOrigin(origins = {"http://127.0.0.1:5173", "http://localhost:5173"}, maxAge = 3600)
public class DeviceRuntimeController {

    private final DeviceRuntimeService deviceRuntimeService;
    private final SSEManageService sseManageService;

    public  DeviceRuntimeController(DeviceRuntimeService deviceRuntimeService,
                                    SSEManageService sseManageService) {
        this.deviceRuntimeService = deviceRuntimeService;
        this.sseManageService = sseManageService;
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

    @GetMapping("/countOn")
    public BaseHttpResponse getStatusOnDeviceCount() {
        Integer count = this.deviceRuntimeService.getStatusOnDeviceCount();
        Map<String, Integer> map = new HashMap<>();
        map.put("count", count);
        return new CommonDataResponse<>(map);
    }

    @PostMapping("/control/on")
    public BaseHttpResponse turnOnDevice(@RequestBody @Validated DeviceIdRequest request) {
        this.deviceRuntimeService.turnOnDevice(request.getLongDeviceId());
        if (this.sseManageService.inSession(request.getDeviceId())) {
            DeviceRuntime deviceRuntime = this.deviceRuntimeService.getDeviceRuntime(request.getLongDeviceId());
            this.sseManageService.send(request.getDeviceId(), deviceRuntime);
        }
        return new Code200Response();
    }

    @PostMapping("/control/off")
    public BaseHttpResponse turnOffDevice(@RequestBody @Validated DeviceIdRequest request) {
        this.deviceRuntimeService.turnOffDevice(request.getLongDeviceId());
        if (this.sseManageService.inSession(request.getDeviceId())) {
            DeviceRuntime deviceRuntime = this.deviceRuntimeService.getDeviceRuntime(request.getLongDeviceId());
            this.sseManageService.send(request.getDeviceId(), deviceRuntime);
        }
        return new Code200Response();
    }

    @PostMapping("/control/brightness")
    public BaseHttpResponse adjustBrightness(@RequestBody @Validated AdjustBrightnessRequest request) {
        this.deviceRuntimeService.adjustBrightness(request.getLongDeviceId(), request.getBrightness());
        if (this.sseManageService.inSession(request.getDeviceId())) {
            DeviceRuntime deviceRuntime = this.deviceRuntimeService.getDeviceRuntime(request.getLongDeviceId());
            this.sseManageService.send(request.getDeviceId(), deviceRuntime);
        }
        return new Code200Response();
    }

    @GetMapping("/control/sse/register/{deviceId:\\d+}")
    public SseEmitter register(@PathVariable String deviceId) {
        return this.sseManageService.connect(deviceId);
    }

}
