package com.anshuman.userservice.Controller;

import com.anshuman.userservice.DAO.UserService;
import com.anshuman.userservice.DTO.UserDTO;
import com.anshuman.userservice.Entity.User;
import com.anshuman.userservice.Error.UserAlreadyExists;
import com.anshuman.userservice.Error.UserNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> adduser(@RequestBody UserDTO userDTO) throws UserAlreadyExists, ParseException {

        User user = userService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/getUserBYId/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
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
