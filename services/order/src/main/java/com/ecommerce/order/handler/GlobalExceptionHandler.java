package com.ecommerce.order.handler;

import com.ecommerce.order.exception.BusinessException;
import com.ecommerce.order.model.HttpResponse;
import jakarta.persistence.EntityNotFoundException;
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

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<HttpResponse> handle(BusinessException exception, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpResponse.builder()
                .path(request.getContextPath())
                .timeStamp(Instant.now().toString())
                .reason("An Internal Server error occurred")
                .message(exception.getMsg())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HttpResponse> handle(EntityNotFoundException exception, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpResponse.builder()
                .path(request.getContextPath())
                .timeStamp(Instant.now().toString())
                .reason("An item was not found")
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.BAD_REQUEST);
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
