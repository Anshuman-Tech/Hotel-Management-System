package com.anshuman.hotelservice.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDTO {

    private String id;
    private String name;
    private String about;
    private String address;
}
