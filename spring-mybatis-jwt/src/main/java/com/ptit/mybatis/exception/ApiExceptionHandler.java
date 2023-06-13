package com.ptit.mybatis.exception;

import com.ptit.mybatis.utli.BaseResponse;
import com.ptit.mybatis.utli.ConstantStatus;
import com.ptit.mybatis.utli.ListBaseResponse;
import com.ptit.mybatis.utli.Meta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleAllException(Exception ex) {
        return new BaseResponse(new Meta(ConstantStatus.INTERNAL_SERVER_ERROR, "The system is maintenance"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ListBaseResponse<String> TodoException(MethodArgumentNotValidException ex) {
        log.info(ex.getClass().getName());
        //
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        return new ListBaseResponse(errors);
    }
}
