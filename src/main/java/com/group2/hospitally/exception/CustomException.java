package com.group2.hospitally.exception;

import com.group2.hospitally.util.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends Exception{
    private HttpStatus httpStatus;
    private ResponseCode responseCode;

    public CustomException(HttpStatus httpStatus,ResponseCode responseCode, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.responseCode = responseCode;
    }
}
