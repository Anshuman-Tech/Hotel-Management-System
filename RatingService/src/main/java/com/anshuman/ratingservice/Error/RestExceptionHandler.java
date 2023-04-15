package com.anshuman.ratingservice.Error;


import com.anshuman.ratingservice.Entity.Rating;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus
public class RestExceptionHandler {

    @ExceptionHandler(RatingAlreadyExists.class)
    public ResponseEntity<ErrorMessage> ratingAlreadyExists(RatingAlreadyExists message){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(message.getMessage());
        errorMessage.setStatus(HttpStatus.FOUND);
        return ResponseEntity.status(HttpStatus.FOUND).body(errorMessage);
    }
}
