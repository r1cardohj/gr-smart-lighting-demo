package org.grsl.controllers;


import org.grsl.models.Device;
import org.grsl.models.DeviceGroup;
import org.grsl.schema.devicegroup.DeviceGroupCreateRequest;
import org.grsl.schema.devicegroup.DeviceGroupJoinOrLeaveRequest;
import org.grsl.schema.devicegroup.DeviceGroupUpdateRequest;
import org.grsl.schema.generics.IDOnlyRequest;
import org.grsl.schema.generics.PaginationResponse;
import org.grsl.schema.http.BaseHttpResponse;
import org.grsl.schema.http.Code200Response;
import org.grsl.schema.http.CommonDataResponse;
import org.grsl.services.DeviceGroupManageService;
import org.grsl.utils.Paginator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("device/group")
@CrossOrigin(origins = {"http://127.0.0.1:5173", "http://localhost:5173"})
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
        Long totalCount = this.deviceGroupManageService.getDeviceGroupTotalCount();
        Paginator paginator = new Paginator(page, perPage);
        List<DeviceGroup> deviceGroups = this.deviceGroupManageService.getDeviceGroupByPage(paginator.page());

        return new PaginationResponse<>(deviceGroups, paginator.pagination(totalCount));
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
    public BaseHttpResponse joinDeviceGroup(@RequestBody @Validated DeviceGroupJoinOrLeaveRequest request) {
        this.deviceGroupManageService.joinDeviceGroup(request.getGroupIdLong(), request.getDeviceIdSet());
        return new Code200Response();
    }

    @PostMapping("/leave")
    public BaseHttpResponse leaveDeviceGroup(@RequestBody @Validated DeviceGroupJoinOrLeaveRequest request) {
        this.deviceGroupManageService.leaveDeviceGroup(request.getGroupIdLong(), request.getDeviceIdSet());
        return new Code200Response();
    }

    @PostMapping("/findByDevice")
    public BaseHttpResponse getDeviceGroupByDevice(@RequestBody @Validated IDOnlyRequest request) {
        Iterable<DeviceGroup> deviceGroups = this.deviceGroupManageService.getDeviceGroupByDeviceId(request.getLongId());
        return new CommonDataResponse<>(deviceGroups);
    }

    @PostMapping("/findByGroup")
    public BaseHttpResponse getDeviceByDeviceGroup(@RequestBody @Validated IDOnlyRequest request) {
        Iterable<Device> devices = this.deviceGroupManageService.getDeviceByDeviceGroupId(request.getLongId());

        return new CommonDataResponse<>(devices);
    }
}
