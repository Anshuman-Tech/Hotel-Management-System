package com.anshuman.hotelservice.Error;

import com.anshuman.hotelservice.DTO.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus
public class RestExceptionHandler {

    @ExceptionHandler(HotelAlreadyExists.class)
    public ResponseEntity<ErrorResponse> hotelAlreadyExists(HotelAlreadyExists e){
        ErrorResponse response = ErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
