package com.anshuman.hotelservice.Error;

public class HotelAlreadyExists extends Exception{

    public HotelAlreadyExists() {
        super();
    }

    public HotelAlreadyExists(String message) {
        super(message);
    }

    public HotelAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelAlreadyExists(Throwable cause) {
        super(cause);
    }

    protected HotelAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
