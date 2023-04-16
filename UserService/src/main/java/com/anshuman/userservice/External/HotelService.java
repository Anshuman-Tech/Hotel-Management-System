package com.anshuman.userservice.External;

import com.anshuman.userservice.DTO.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotel/getHotel/{id}")
    Hotel getHotel(@PathVariable("id") String hotelId);
}
