package org.grsl.advices.deviceruntime;

import org.grsl.repositories.DeviceRuntimeRespository;
import org.grsl.schema.http.BaseHttpResponse;
import org.grsl.schema.http.Code400Response;
import org.grsl.services.DeviceRuntimeService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DeviceRtExceptionAdvice {
    @ExceptionHandler(DeviceRuntimeRespository.DeviceRuntimeIsExistException.class)
    @ResponseBody
    public BaseHttpResponse isExsitHandler() { return new Code400Response("device runtime is existed");}

    @ExceptionHandler(DeviceRuntimeRespository.DeviceRuntimeNotFoundException.class)
    @ResponseBody
    public BaseHttpResponse notFoundHandler() { return new Code400Response("device runtime is not found");}

    @ExceptionHandler(DeviceRuntimeService.ControlInvalidException.class)
    @ResponseBody
    public BaseHttpResponse controlInvalidHandler() {
        return new Code400Response("your control is invalid");
    }
}
