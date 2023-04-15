package com.anshuman.ratingservice.DAO;

import com.anshuman.ratingservice.DTO.RatingDto;
import com.anshuman.ratingservice.Entity.Rating;
import com.anshuman.ratingservice.Error.RatingAlreadyExists;
import com.anshuman.ratingservice.Error.RatingNotExist;

import java.util.List;

public interface RatingService {

    Rating addRating(RatingDto ratingDto) throws RatingAlreadyExists;

    Rating getRatingByRatingId(String ratingId) throws RatingNotExist;

    List<Rating> getAllRatingsByUserId(String userId);

    List<Rating> getAllRatingsByHotelId(String hotelId);

    void deleteRatingByRatingId(String ratingId) throws RatingNotExist;

    void deleteRatingsByHotelId(String hotelId);

    Rating updateRating(String ratingId,RatingDto ratingDto) throws RatingNotExist;

    Rating updateRatingFeedback(String ratingId, String feedback) throws Exception;

}
