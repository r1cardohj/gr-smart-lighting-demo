package org.grsl.controllers;

import org.grsl.models.Device;
import org.grsl.schema.device.DeviceCreateRequest;
import org.grsl.schema.generics.IDOnlyRequest;
import org.grsl.schema.device.DeviceUpdateRequest;
import org.grsl.schema.generics.PaginationResponse;
import org.grsl.schema.http.CommonDataResponse;
import org.grsl.schema.http.BaseHttpResponse;
import org.grsl.schema.http.Code200Response;
import org.grsl.services.DeviceManageService;
import org.grsl.utils.Paginator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/device")
@CrossOrigin(origins = {"http://127.0.0.1:5173", "http://localhost:5173"}, maxAge = 3600)
public class DeviceManageController {
    private final DeviceManageService deviceManageService;

    public DeviceManageController(DeviceManageService deviceManageService) {
        this.deviceManageService = deviceManageService;
    }

    @GetMapping("/")
    public BaseHttpResponse getAllDevices(@RequestParam(required = false) Integer page,
                                          @RequestParam(required = false) Integer perPage) {
        Long totalCount = this.deviceManageService.getTotalCount();
        Paginator paginator=  new Paginator(page, perPage);
        List<Device> devices = this.deviceManageService.findDeviceByPage(paginator.page());
        return new PaginationResponse<>(devices,
                                        paginator.pagination(totalCount));
    }


    @GetMapping("/{id:\\d+}")
    public BaseHttpResponse getDeviceById(@PathVariable long id) {
            Device device = this.deviceManageService.findDeviceById(id);
            return new CommonDataResponse<>(device);
    }

    @PostMapping("/create")
    public BaseHttpResponse createDevice(@RequestBody @Validated DeviceCreateRequest request) {
        this.deviceManageService.createDevice(request.toDevice());
        return new Code200Response();
    }

    @PostMapping("/update")
    public BaseHttpResponse updateDevice(@RequestBody @Validated DeviceUpdateRequest request) {
        this.deviceManageService.updateDevice(request.toDevice());
        return new Code200Response();
    }

    @PostMapping("/delete")
    public BaseHttpResponse deleteDevice(@RequestBody @Validated IDOnlyRequest request) {
        this.deviceManageService.deleteDeviceById(request.getLongId());
        return new Code200Response();
    }

    @GetMapping("/countOnline")
    public BaseHttpResponse getOnlineDeviceCount() {
        Integer count = this.deviceManageService.getOnlineDeviceCount();
        Map<String, Integer> map = new HashMap<>();
        map.put("count", count);
        return new CommonDataResponse<>(map);
    }
}
