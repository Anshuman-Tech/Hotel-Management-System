package com.anshuman.hotelservice.Service;

import com.anshuman.hotelservice.DAO.HotelService;
import com.anshuman.hotelservice.DTO.HotelDTO;
import com.anshuman.hotelservice.Entity.Hotel;
import com.anshuman.hotelservice.Error.HotelAlreadyExists;
import com.anshuman.hotelservice.Error.HotelNotFound;
import com.anshuman.hotelservice.Repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotel addHotel(HotelDTO hotelDTO) throws Exception{
        //check if the hotel already exists
        Optional<Hotel> existingHotel = hotelRepository.findById(hotelDTO.getId());
        if(existingHotel.isPresent()){
            throw new HotelAlreadyExists("Hotel already exists");
        }
        else{
            Hotel hotel = Hotel.builder()
                    .hotelId(hotelDTO.getId())
                    .hotelName(hotelDTO.getName())
                    .about(hotelDTO.getAbout())
                    .address(hotelDTO.getAddress())
                    .build();
            return hotelRepository.save(hotel);
        }
    }

    @Override
    public Hotel getHotelById(String hotelId) throws HotelNotFound{
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(!hotel.isPresent()){
            throw new HotelNotFound("Hotel not found!");
        }
        return hotel.get();
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelByName(String name) throws HotelNotFound{
       Hotel hotel =  hotelRepository.findByHotelName(name);
       if(hotel==null){
           throw new HotelNotFound("Hotel not found");
       }
       return hotel;
    }
}
