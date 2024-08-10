package com.ecommerce.customer.handler;

import com.ecommerce.customer.exception.CustomerNotFoundException;
import com.ecommerce.customer.model.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<HttpResponse> handle(CustomerNotFoundException exception, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpResponse.builder()
                .path(request.getContextPath())
                .timeStamp(Instant.now().toString())
                .reason("An Internal Server error occurred")
                .message(exception.getMsg())
                .status(status)
                .statusCode(status.value())
                .build(), status);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponse> handle(MethodArgumentNotValidException exception, HttpStatus status, WebRequest request) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError)error).getObjectName();
            var fieldMessage = error.getDefaultMessage();
            errors.put(fieldName, fieldMessage);
        });

        return new ResponseEntity<>(HttpResponse.builder()
                .path(request.getContextPath())
                .timeStamp(Instant.now().toString())
                .reason("Bad request")
                .data(Map.of("errors", errors))
                .status(status)
                .statusCode(status.value())
                .build(), status);
    }
}
