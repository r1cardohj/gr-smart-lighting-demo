package org.grsl.advices.devicegroup;

import org.grsl.repositories.DeviceDeviceGroupRespository;
import org.grsl.repositories.DeviceGroupRespository;
import org.grsl.schema.http.Code400Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DeviceGrpManageExceptionAdvice {
    @ExceptionHandler(DeviceGroupRespository.DeviceGroupNotFoundException.class)
    @ResponseBody
    public Code400Response notFoundHandler() {
        return new Code400Response("device group is not found.");
    }

    @ExceptionHandler(DeviceGroupRespository.DeviceGroupNameRepeatException.class)
    @ResponseBody
    public Code400Response nameRepeatHandler() {
        return new Code400Response("device group name is exist.");
    }

    @ExceptionHandler(DeviceGroupRespository.DeviceGroupExistException.class)
    @ResponseBody
    public Code400Response isExistHandler() {
        return new Code400Response("device group is exist");
    }

    @ExceptionHandler(DeviceDeviceGroupRespository.DeviceInGrpAlreadyException.class)
    @ResponseBody
    public Code400Response deviceInGroupAlready() { return new Code400Response("device is in group already."); }

    @ExceptionHandler(DeviceDeviceGroupRespository.DeivceNotInGrpExcpetion.class)
    @ResponseBody
    public Code400Response deviceNotInGroup() { return new Code400Response("device is not in group!"); }
}
