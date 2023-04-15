package com.anshuman.ratingservice.Service;

import com.anshuman.ratingservice.DAO.RatingService;
import com.anshuman.ratingservice.DTO.RatingDto;
import com.anshuman.ratingservice.Entity.Rating;
import com.anshuman.ratingservice.Error.RatingAlreadyExists;
import com.anshuman.ratingservice.Error.RatingNotExist;
import com.anshuman.ratingservice.Repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating addRating(RatingDto ratingDto) throws RatingAlreadyExists {
        Optional<Rating> existingRating = ratingRepository.findById(ratingDto.getRatingId());
        if(existingRating.isPresent()){
            throw new RatingAlreadyExists("Rating already exists");
        }
        Rating rating = new Rating();
        rating.setRatingId(ratingDto.getRatingId());
        rating.setUserId(ratingDto.getUserId());
        rating.setHotelId(ratingDto.getHotelId());
        rating.setRating(ratingDto.getRating());
        rating.setFeedback(ratingDto.getFeedback());

        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRatingByRatingId(String ratingId) throws RatingNotExist {
        Optional<Rating> rating = ratingRepository.findById(ratingId);
        if(!rating.isPresent()){
            throw new RatingNotExist("Rating does not exist");
        }
        return rating.get();
    }

    @Override
    public List<Rating> getAllRatingsByUserId(String userId) {
        return ratingRepository.findAllByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingsByHotelId(String hotelId) {
        return ratingRepository.findAllByHotelId(hotelId);
    }

    @Override
    public void deleteRatingByRatingId(String ratingId) throws RatingNotExist{
        Optional<Rating> existingRating = ratingRepository.findById(ratingId);
        if(!existingRating.isPresent()){
            throw new RatingNotExist("Rating does not exist");
        }
        ratingRepository.deleteById(ratingId);
    }

    @Override
    public void deleteRatingsByHotelId(String hotelId){

        ratingRepository.deleteRatingsByHotelId(hotelId);
    }

    @Override
    public Rating updateRating(String ratingId, RatingDto ratingDto) throws RatingNotExist{
        Optional<Rating> existingRating = ratingRepository.findById(ratingId);
        if(!existingRating.isPresent()){
            throw new RatingNotExist("Rating does not exist");
        }
        Rating rating = existingRating.get();

        if(ratingDto.getUserId()!=null){
            rating.setUserId(ratingDto.getUserId());
        }
        if(ratingDto.getHotelId()!=null){
            rating.setHotelId(ratingDto.getHotelId());
        }
        if(ratingDto.getRating()!=0){
            rating.setRating(ratingDto.getRating());
        }
        if(ratingDto.getFeedback()!=null){
            rating.setFeedback(ratingDto.getFeedback());
        }

        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRatingFeedback(String ratingId, String feedback) throws Exception{
        Optional<Rating> existingRating = ratingRepository.findById(ratingId);
        if(!existingRating.isPresent()){
            throw new RatingNotExist("Rating does not exist");
        }
        Rating rating = existingRating.get();
        rating.setFeedback(feedback);
        return ratingRepository.save(rating);
    }
}
