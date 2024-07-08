package org.grsl.controllers;

import org.grsl.models.Device;
import org.grsl.repositories.DeviceRepository;
import org.grsl.schema.device.DeviceCreateRequest;
import org.grsl.schema.device.DeviceIDOnlyRequest;
import org.grsl.schema.device.DeviceUpdateRequest;
import org.grsl.schema.http.CommonDataResponse;
import org.grsl.schema.http.BaseHttpResponse;
import org.grsl.schema.http.Code200Response;
import org.grsl.schema.http.Code400Response;
import org.grsl.services.DeviceManageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/device")
public class DeviceManageController {
    private final DeviceManageService deviceManageService;

    public DeviceManageController(DeviceManageService deviceManageService) {
        this.deviceManageService = deviceManageService;
    }

    @GetMapping("/")
    public BaseHttpResponse getAllDevices(@RequestParam(required = false) Integer page,
                                          @RequestParam(required = false) Integer perPage) {
        List<Device> devices = this.deviceManageService.findDeviceByPage(page, perPage);
        return new CommonDataResponse<>(devices);
    }


    @GetMapping("/{id}")
    public BaseHttpResponse getDeviceById(@PathVariable long id) {
        try {
            Device device = this.deviceManageService.findDeviceById(id);
            return new CommonDataResponse<>(device);
        } catch (DeviceRepository.DeviceNotFoundException e) {
            return new CommonDataResponse<>(null);
        }
    }

    @PostMapping("/create")
    public BaseHttpResponse createDevice(@RequestBody @Validated DeviceCreateRequest request) {
        try {
            this.deviceManageService.createDevice(request.toDevice());
        } catch (DeviceRepository.DeviceExistException e) {
            return new Code400Response(e.toString());
        }
        return new Code200Response();
    }

    @PostMapping("/update")
    public BaseHttpResponse updateDevice(@RequestBody @Validated DeviceUpdateRequest request) {
        try {
            this.deviceManageService.updateDevice(request.toDevice());
        } catch (DeviceRepository.DeviceNotFoundException e) {
            return new Code400Response(e.toString());
        }
        return new Code200Response();
    }

    @PostMapping("/delete")
    public BaseHttpResponse deleteDevice(@RequestBody @Validated DeviceIDOnlyRequest request) {
        try {
            this.deviceManageService.deleteDeviceById(request.getId());
        } catch (DeviceRepository.DeviceNotFoundException e) {
            return new Code400Response(e.toString());
        }
        return new Code200Response();
    }
}
