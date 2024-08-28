package org.grsl.advices.devicegroup;

import org.grsl.repositories.DeviceDeviceGroupRepository;
import org.grsl.repositories.DeviceGroupRepository;
import org.grsl.schema.http.Code400Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DeviceGrpManageExceptionAdvice {
    @ExceptionHandler(DeviceGroupRepository.DeviceGroupNotFoundException.class)
    @ResponseBody
    public Code400Response notFoundHandler() {
        return new Code400Response("device group is not found.");
    }

    @ExceptionHandler(DeviceGroupRepository.DeviceGroupNameRepeatException.class)
    @ResponseBody
    public Code400Response nameRepeatHandler() {
        return new Code400Response("device group name is exist.");
    }

    @ExceptionHandler(DeviceGroupRepository.DeviceGroupExistException.class)
    @ResponseBody
    public Code400Response isExistHandler() {
        return new Code400Response("device group is exist");
    }

    @ExceptionHandler(DeviceDeviceGroupRepository.DeviceInGrpAlreadyException.class)
    @ResponseBody
    public Code400Response deviceInGroupAlready() { return new Code400Response("device is in group already."); }

    @ExceptionHandler(DeviceDeviceGroupRepository.DeivceNotInGrpExcpetion.class)
    @ResponseBody
    public Code400Response deviceNotInGroup() { return new Code400Response("device is not in group!"); }
}
