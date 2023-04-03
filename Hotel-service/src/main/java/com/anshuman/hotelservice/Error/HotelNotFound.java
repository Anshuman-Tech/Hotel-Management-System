package com.anshuman.hotelservice.Error;

public class HotelNotFound extends Exception{

    public HotelNotFound() {
        super();
    }

    public HotelNotFound(String message) {
        super(message);
    }

    public HotelNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelNotFound(Throwable cause) {
        super(cause);
    }

    protected HotelNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
