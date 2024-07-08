package org.grsl.advices.json;

import org.grsl.schema.http.CommonErrorResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class JsonVaildationExcpetionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonErrorResponse<Object> jsonExceptionHandler(MethodArgumentNotValidException e,
                                                            HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();

        for (ObjectError objectError : bindingResult.getAllErrors()) {
            FieldError fieldError = (FieldError) objectError;
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new CommonErrorResponse<>(errors);
    }
}
