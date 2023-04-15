package com.anshuman.ratingservice.Controller;

import com.anshuman.ratingservice.DAO.RatingService;
import com.anshuman.ratingservice.DTO.RatingDto;
import com.anshuman.ratingservice.Entity.Rating;
import com.anshuman.ratingservice.Error.RatingAlreadyExists;
import com.anshuman.ratingservice.Error.RatingNotExist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rating")
public class ReviewController {

    private final RatingService ratingService;

    @PostMapping("/addRating")
    public ResponseEntity<Rating> addRating(@RequestBody RatingDto ratingDto) throws RatingAlreadyExists {
        Rating rating = ratingService.addRating(ratingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating);
    }

    @GetMapping("/getRating/{ratingId}")
    public ResponseEntity<Rating> getRatingByRatingId(@PathVariable() String ratingId) throws RatingNotExist {
        Rating rating = ratingService.getRatingByRatingId(ratingId);
        return ResponseEntity.status(HttpStatus.FOUND).body(rating);
    }


    @GetMapping("/getRatingsByUserId/{id}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable("id") String userId){
        List<Rating> ratings = ratingService.getAllRatingsByUserId(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(ratings);
    }

    @GetMapping("/getRatingsByHotelId/{id}")
    public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable("id") String hotelId){
        List<Rating> ratings = ratingService.getAllRatingsByHotelId(hotelId);
        return ResponseEntity.status(HttpStatus.FOUND).body(ratings);
    }

    @DeleteMapping("/deleteRating/{id}")
    public ResponseEntity<String> deleteRatingByRatingId(@PathVariable("id") String ratingId) throws RatingNotExist {
        ratingService.deleteRatingByRatingId(ratingId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @DeleteMapping("/deleteRatingsByHotelId/{id}")
    public ResponseEntity<String> deleteRatingsByHotelId(@PathVariable("id") String hotelId){
        ratingService.deleteRatingsByHotelId(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Successfully");
    }


    @PutMapping("/updateRating/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable("id") String ratingId,@RequestBody RatingDto ratingDto) throws RatingNotExist{
        Rating rating = ratingService.updateRating(ratingId,ratingDto);
        return ResponseEntity.status(HttpStatus.OK).body(rating);
    }

    @PatchMapping("/updateRatingFeedback/{ratingId}")
    public ResponseEntity<Rating> updateRatingFeedback(@PathVariable String ratingId, @RequestParam String feed) throws Exception{
        Rating rating = ratingService.updateRatingFeedback(ratingId,feed);
        return ResponseEntity.status(HttpStatus.OK).body(rating);
    }
}
