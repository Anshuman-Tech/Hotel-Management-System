package com.anshuman.userservice.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private float rating;
    private String feedback;
    private Hotel hotel;
}
