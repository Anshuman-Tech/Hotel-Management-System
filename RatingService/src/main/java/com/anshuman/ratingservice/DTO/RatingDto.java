package com.anshuman.ratingservice.DTO;


import lombok.Data;
import lombok.Getter;

@Getter
public class RatingDto {

    private String ratingId;
    private String userId;
    private String hotelId;
    private float rating;
    private String feedback;
}
