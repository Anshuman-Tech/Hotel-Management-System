package com.anshuman.hotelservice.Controller;

import com.anshuman.hotelservice.DAO.HotelService;
import com.anshuman.hotelservice.DTO.HotelDTO;
import com.anshuman.hotelservice.Entity.Hotel;
import com.anshuman.hotelservice.Error.HotelNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;
    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }

    @PostMapping("/addHotel")
    public ResponseEntity<Hotel> addHotel(@RequestBody HotelDTO hotelDTO) throws Exception {
        Hotel hotel = hotelService.addHotel(hotelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }

    @GetMapping("/getHotel/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("id") String hotelId) throws HotelNotFound {
        Hotel hotel = hotelService.getHotelById(hotelId);
        return ResponseEntity.status(HttpStatus.FOUND).body(hotel);
    }

    @GetMapping("/getAllHotels")
    public List<Hotel> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/getHotelByName")
    public ResponseEntity<Hotel> getHotelByName(@RequestParam("hotelName") String name) throws HotelNotFound {
        Hotel hotel = hotelService.getHotelByName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(hotel);
    }
}
