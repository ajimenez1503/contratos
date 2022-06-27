package com.contratos.controller;

import lombok.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private Error processFieldErrors(List<FieldError> fieldErrors) {
        Error error = new Error(HttpStatus.BAD_REQUEST, "Validation error");
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            error.addFieldError(fieldError);
        }
        return error;
    }

    @EqualsAndHashCode
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static class Error {
        private final HttpStatus status;
        private final String message;
        private List<FieldError> fieldErrors = new ArrayList<>();

        public Error(HttpStatus badRequest, String validationError) {
            this.status = badRequest;
            this.message = validationError;
        }

        public void addFieldError(FieldError error) {
            fieldErrors.add(error);
        }
    }
}
