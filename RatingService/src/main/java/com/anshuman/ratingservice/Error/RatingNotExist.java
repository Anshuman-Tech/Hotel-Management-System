package com.anshuman.ratingservice.Error;

public class RatingNotExist extends Exception{

    public RatingNotExist() {
        super();
    }

    public RatingNotExist(String message) {
        super(message);
    }

    public RatingNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public RatingNotExist(Throwable cause) {
        super(cause);
    }

    protected RatingNotExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
