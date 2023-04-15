package com.anshuman.ratingservice.Repository;

import com.anshuman.ratingservice.Entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating,String> {

    List<Rating> findAllByUserId(String userId);

    List<Rating> findAllByHotelId(String hotelId);

    @Query(value = "{'hotel_id':?0}",delete = true)
    void deleteRatingsByHotelId(String hotelId);
}
