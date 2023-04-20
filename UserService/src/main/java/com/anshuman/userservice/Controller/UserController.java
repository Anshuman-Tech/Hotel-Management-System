package com.anshuman.userservice.Controller;

import com.anshuman.userservice.DAO.UserService;
import com.anshuman.userservice.DTO.Hotel;
import com.anshuman.userservice.DTO.Rating;
import com.anshuman.userservice.DTO.UserDTO;
import com.anshuman.userservice.Entity.User;
import com.anshuman.userservice.Error.UserAlreadyExists;
import com.anshuman.userservice.Error.UserNotFoundException;
import com.anshuman.userservice.External.HotelService;
import com.anshuman.userservice.External.RatingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    //@Slf4j or LoggerFactory is used to create the logger.
//    Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

//    @Autowired
//    private RatingService ratingService;

//    @Autowired
//    private HotelService hotelService;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/addUser")
    public ResponseEntity<User> adduser(@RequestBody UserDTO userDTO) throws UserAlreadyExists, ParseException {

        User user = userService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal') || hasAuthority('Normal')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/getUserBYId/{id}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod="ratingHotelFallback")
//    @RateLimiter(name = "ratingHotelLimiter",fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelRetry",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) throws UserNotFoundException {

        //        When using RestTemplate Http Client
        User user = userService.getUserById(userId);
//        127.0.0.1:9002/rating/getRatingsByUserId/User1
        // Using host and port
//        List<Rating> ratings = restTemplate.getForObject("http://localhost:9002/rating/getRatingsByUserId/User1",List.class);

        //Using Eureka Client name and dynamic userId
        Rating[] ratings = restTemplate.getForObject("http://Rating-Service/rating/getRatingsByUserId/"+user.getUserId(),Rating[].class);

        List<Rating> ratings1 = Arrays.asList(ratings);
        List<Rating> ratingList = ratings1.stream().map(rating -> {
            Hotel hotel = restTemplate.getForObject("http://Hotel-Service/hotel/getHotel/"+rating.getHotelId(), Hotel.class);
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);

        return ResponseEntity.status(HttpStatus.FOUND).body(user);

//        Using Feign client

//        User user = userService.getUserById(userId);
//        ResponseEntity<List<Rating>> ratings = ratingService.getRatingsByUserId(user.getUserId());
//        List<Rating> ratings1 = ratings.getBody();
//        List<Rating> ratings2 = ratings1.stream().map(rating -> {
//            Hotel hotel = hotelService.getHotelById(rating.getHotelId());
//            rating.setHotel(hotel);
//            return rating;
//        }).collect(Collectors.toList());
//
//        user.setRatings(ratings2);
//        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }


    //Fallback method - A fallback method is implemented when any of the dependent services is down or slow.
    //The return type of the fallback method is same as the return type of the called API method.
    //The parameter of the fallback method is same as the parameter of the called API method, along with an exception.
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception e){
        log.info("One of the dependent services is down: {}",e.getMessage());

        //If dummy data is returned even after all the services are up, then we can print the exception stack.
        e.printStackTrace();

        User user = User.builder()
                .userId("Dummy101")
                .userName("Dummy")
                .emailId("dummy@gmail.com")
                .dob("10/20/1000")
                .ratings(Arrays.asList(new Rating()))
                .phoneNumber("1111111111")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/getUserByUserName/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) throws UserNotFoundException{
        User user = userService.getUserByUserName(userName);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId,@RequestBody UserDTO userDTO) throws UserNotFoundException, ParseException {
        User user = userService.updateUser(userId,userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PatchMapping("/updateUserPhoneNumber/{id}")
    public ResponseEntity UpdateUserPhoneNumber(@PathVariable("id") String userId,@RequestParam("Phone") String phoneNumber) throws UserNotFoundException {
        userService.updateUserPhoneNumber(userId,phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body("Updated");
    }
}
