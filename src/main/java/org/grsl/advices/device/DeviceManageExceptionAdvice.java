package org.grsl.advices.device;

import org.grsl.repositories.DeviceRepository;
import org.grsl.schema.http.Code400Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DeviceManageExceptionAdvice {
    @ExceptionHandler(DeviceRepository.DeviceExistException.class)
    @ResponseBody
    public Code400Response exceptionDeviceExistHandler() {
        return new Code400Response("Device is exist. operation is valid.");
    }

    @ExceptionHandler(DeviceRepository.DeviceNotFoundException.class)
    @ResponseBody
    public Code400Response exceptionDeviceNotFoundHandler() {
        return new Code400Response("Device is not found.");
    }
}
