package com.anshuman.hotelservice.DAO;

import com.anshuman.hotelservice.DTO.HotelDTO;
import com.anshuman.hotelservice.Entity.Hotel;
import com.anshuman.hotelservice.Error.HotelNotFound;

import java.util.List;

public interface HotelService {

    Hotel addHotel(HotelDTO hotelDTO) throws Exception;

    Hotel getHotelById(String hotelId) throws HotelNotFound;

    List<Hotel> getAllHotels();

    Hotel getHotelByName(String name) throws HotelNotFound;
}
