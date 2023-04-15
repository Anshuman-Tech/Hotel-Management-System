package com.anshuman.ratingservice.Error;

public class RatingAlreadyExists extends Exception{
    public RatingAlreadyExists() {
        super();
    }

    public RatingAlreadyExists(String message) {
        super(message);
    }

    public RatingAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public RatingAlreadyExists(Throwable cause) {
        super(cause);
    }

    protected RatingAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
