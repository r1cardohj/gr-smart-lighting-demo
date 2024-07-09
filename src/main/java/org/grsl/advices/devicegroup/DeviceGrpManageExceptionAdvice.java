package org.grsl.advices.devicegroup;

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

}
