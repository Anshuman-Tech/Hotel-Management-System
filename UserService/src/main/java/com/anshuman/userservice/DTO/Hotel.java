package com.anshuman.userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    private String hotelId;
    private String hotelName;
    private String about;
    private String address;
}
