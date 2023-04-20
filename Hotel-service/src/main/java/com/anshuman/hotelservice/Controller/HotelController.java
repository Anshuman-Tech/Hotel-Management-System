package com.anshuman.hotelservice.Controller;

import com.anshuman.hotelservice.DAO.HotelService;
import com.anshuman.hotelservice.DTO.HotelDTO;
import com.anshuman.hotelservice.Entity.Hotel;
import com.anshuman.hotelservice.Error.HotelNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.persistence.PreRemove;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;
    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }


    /*
    @PreAuthorize(), @Secured(), @RolesAllowed() all work in the same way and are used for role based route access.
     */
    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/addHotel")
    public ResponseEntity<Hotel> addHotel(@RequestBody HotelDTO hotelDTO) throws Exception {
        Hotel hotel = hotelService.addHotel(hotelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }


    @PreAuthorize("hasAuthority('SCOPE_internal') or hasAuthority('Admin')")
    @GetMapping("/getHotel/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("id") String hotelId) throws HotelNotFound {
        Hotel hotel = hotelService.getHotelById(hotelId);
        return ResponseEntity.status(HttpStatus.FOUND).body(hotel);
    }

//    @PreAuthorize("hasAuthority(SCOPE_internal) || hasAuthority('Admin')")
//    @Secured("hasRole('Admin),hasRole('SCOPE_internal)")
    @RolesAllowed("hasRole('Admin'),hasRole('SCOPE_internal')")
    @GetMapping("/getAllHotels")
    public List<Hotel> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
    @GetMapping("/getHotelByName")
    public ResponseEntity<Hotel> getHotelByName(@RequestParam("hotelName") String name) throws HotelNotFound {
        Hotel hotel = hotelService.getHotelByName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(hotel);
    }
}
