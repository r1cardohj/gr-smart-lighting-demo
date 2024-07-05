package org.grsl.controllers;

import org.grsl.models.Device;
import org.grsl.repositories.DeviceRepository;
import org.grsl.schema.device.DeviceCreateRequest;
import org.grsl.schema.device.DeviceIDOnlyRequest;
import org.grsl.schema.device.DeviceResponse;
import org.grsl.schema.http.BaseHttpResponse;
import org.grsl.schema.http.Code200Response;
import org.grsl.schema.http.Code400Response;
import org.grsl.services.DeviceManageService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/device")
public class DeviceManageController {
    private final DeviceManageService deviceManageService;

    public DeviceManageController(DeviceManageService deviceManageService) {
        this.deviceManageService = deviceManageService;
    }

    /*@GetMapping("/")
    public Iterable<Device> getAllDevices(@RequestParam Integer page,
                                          @RequestParam Integer perPage) {

    }*/


    @GetMapping("/{id}")
    public BaseHttpResponse getDeviceById(@PathVariable long id) {
        try {
            Device device = this.deviceManageService.findDeviceById(id);
            return new DeviceResponse(device);
        } catch (DeviceRepository.DeviceNotFoundException e) {
            return new DeviceResponse(null);
        }
    }

    @PostMapping("/create")
    public BaseHttpResponse createDevice(@RequestBody DeviceCreateRequest request) {
        try {
            this.deviceManageService.createDevice(request.toDevice());
        } catch (DeviceRepository.DeviceExistException e) {
            return new Code400Response(e.toString());
        }
        return new Code200Response();
    }

    @PostMapping("/update")
    public BaseHttpResponse updateDevice(@RequestBody Device device) {
        try {
            this.deviceManageService.updateDevice(device);
        } catch (DeviceRepository.DeviceNotFoundException e) {
            return new Code400Response(e.toString());
        }
        return new Code200Response();
    }

    @PostMapping("/delete")
    public BaseHttpResponse deleteDevice(@RequestBody DeviceIDOnlyRequest request) {
        try {
            this.deviceManageService.deleteDeviceById(request.getId());
        } catch (DeviceRepository.DeviceNotFoundException e) {
            return new Code400Response(e.toString());
        }
        return new Code200Response();
    }
}
