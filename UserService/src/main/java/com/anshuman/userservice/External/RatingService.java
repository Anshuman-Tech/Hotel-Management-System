package com.anshuman.userservice.External;

import com.anshuman.userservice.DTO.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "RATING-SERVICE",url = "http://localhost:9002/rating")
public interface RatingService {

    @GetMapping("/getRatingsByUserId/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable("id") String userId);
}
