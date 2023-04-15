package com.anshuman.ratingservice.Error;

import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
public class ErrorMessage {

    private String message;

    private HttpStatus status;
}
