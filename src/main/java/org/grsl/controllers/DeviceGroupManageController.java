package org.grsl.controllers;


import org.grsl.models.DeviceGroup;
import org.grsl.schema.devicegroup.DeviceGroupCreateRequest;
import org.grsl.schema.devicegroup.DeviceGroupJoinAndLeaveRequest;
import org.grsl.schema.devicegroup.DeviceGroupUpdateRequest;
import org.grsl.schema.generics.IDOnlyRequest;
import org.grsl.schema.http.BaseHttpResponse;
import org.grsl.schema.http.Code200Response;
import org.grsl.schema.http.CommonDataResponse;
import org.grsl.services.DeviceGroupManageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("device/group")
public class DeviceGroupManageController {
    private final DeviceGroupManageService deviceGroupManageService;

    public DeviceGroupManageController(DeviceGroupManageService deviceGroupManageService) {
        this.deviceGroupManageService = deviceGroupManageService;
    }

    @GetMapping("/{id:\\d+}")
    public BaseHttpResponse getDeviceGroupById(@PathVariable long id) {
        DeviceGroup deviceGroup = this.deviceGroupManageService.getDeviceGroupById(id);
        return new CommonDataResponse<>(deviceGroup);
    }

    @GetMapping("/")
    public BaseHttpResponse getAllDeviceGroup(@RequestParam(required = false) Integer page,
                                              @RequestParam(required = false) Integer perPage) {
        List<DeviceGroup> deviceGroups = this.deviceGroupManageService.getDeviceGroupByPage(page, perPage);
        return new CommonDataResponse<>(deviceGroups);
    }

    @PostMapping("/create")
    public BaseHttpResponse createDeviceGroup(@RequestBody @Validated DeviceGroupCreateRequest request) {
        this.deviceGroupManageService.createDeiveGroup(request.toDeviceGroup());
        return new Code200Response();
    }

    @PostMapping("/update")
    public BaseHttpResponse updateDeviceGroup(@RequestBody @Validated DeviceGroupUpdateRequest request) {
        this.deviceGroupManageService.updateDeviceGroup(request.toDeviceGroup());
        return new Code200Response();
    }

    @PostMapping("/delete")
    public BaseHttpResponse deleteDeviceGroup(@RequestBody @Validated IDOnlyRequest request) {
        this.deviceGroupManageService.deleteDeviceGroup(request.getLongId());
        return new Code200Response();
    }

    @PostMapping("/join")
    public BaseHttpResponse joinDeviceGroup(@RequestBody @Validated DeviceGroupJoinAndLeaveRequest request) {
        //todo
    }

    @PostMapping("/leave")
    public BaseHttpResponse leaveDeviceGroup(@RequestBody @Validated DeviceGroupJoinAndLeaveRequest request) {
        //todo
    }
}
