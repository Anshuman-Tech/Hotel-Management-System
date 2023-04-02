package com.anshuman.userservice.DAO;

import com.anshuman.userservice.DTO.UserDTO;
import com.anshuman.userservice.Entity.User;
import com.anshuman.userservice.Error.UserAlreadyExists;
import com.anshuman.userservice.Error.UserNotFoundException;

import java.text.ParseException;
import java.util.List;

public interface UserService {

    User addUser(UserDTO userDTO) throws UserAlreadyExists, ParseException;
    List<User> getAllUsers();

    User getUserById(String userId) throws UserNotFoundException;

    User updateUser(String userId, UserDTO userDTO) throws UserNotFoundException, ParseException;

    void updateUserPhoneNumber(String userId,String phoneNumber) throws UserNotFoundException;

    User getUserByUserName(String userName) throws UserNotFoundException;
}
