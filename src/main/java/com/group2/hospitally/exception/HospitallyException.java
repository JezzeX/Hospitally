package com.group2.hospitally.exception;

import com.group2.hospitally.model.response.AppResponse;
import com.group2.hospitally.util.ResponseCode;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@Hidden
@RestControllerAdvice
public class HospitallyException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        String exMessage = ex.getBindingResult().getAllErrors().stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AppResponse.<String>builder()
                //.data(exMessage)
                .responseCode(ResponseCode.AD_REQUEST.getCode())
                .responseMessage(ResponseCode.AD_REQUEST.getDescription())
                .build());
    }

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request){

        return ResponseEntity.status(ex.getHttpStatus()).body(AppResponse.<String>builder()
                .responseCode(ex.getResponseCode().getCode())
                .responseMessage(ex.getResponseCode().getDescription())
                .build()
        );
    }
}
