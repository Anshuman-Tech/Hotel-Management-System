package com.anshuman.userservice.Service;

import com.anshuman.userservice.DAO.UserService;
import com.anshuman.userservice.DTO.UserDTO;
import com.anshuman.userservice.Entity.User;
import com.anshuman.userservice.Error.UserAlreadyExists;
import com.anshuman.userservice.Error.UserNotFoundException;
import com.anshuman.userservice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(UserDTO userDTO) throws UserAlreadyExists, ParseException {

        Optional<User> existingUser = userRepository.findById(userDTO.getUserId());
        if(existingUser.isPresent()){
            throw new UserAlreadyExists("This user already exists");
        }
        else {
            User user = User.builder()
                    .userId(userDTO.getUserId())
                    .userName(userDTO.getUserName())
                    .emailId(userDTO.getEmail())
                    .dob(userDTO.getDob())
                    .phoneNumber(userDTO.getPhoneNumber())
                    .build();

            return userRepository.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("User is not present! Check the userId");
        }
        else{
            return user.get();
        }
    }

    @Override
    public User updateUser(String userId, UserDTO userDTO) throws UserNotFoundException, ParseException {
        Optional<User> existingUser = userRepository.findById(userId);
        if(!existingUser.isPresent()){
            throw new UserNotFoundException("User is not present! Check the userId");
        }
        User user  = existingUser.get();
        user.setUserName(userDTO.getUserName());
        user.setEmailId(userDTO.getEmail());
        user.setDob(userDTO.getDob());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        return userRepository.save(user);
    }

    @Override
    public void updateUserPhoneNumber(String userId, String phoneNumber) throws UserNotFoundException {
//        Optional<User> existingUser = userRepository.findById(userId);
//        if(!existingUser.isPresent()){
//            throw new UserNotFoundException("User is not present! Check the user id");
//        }
//        User user = existingUser.get();
//        user.setPhoneNumber(phoneNumber);
//        return userRepository.save(user);
        Optional<User> existingUser = userRepository.findById(userId);
        if(!existingUser.isPresent()){
            throw new UserNotFoundException("User is not present! Check the user id");
        }
        else{
            userRepository.updateUserPhoneNumber(userId,phoneNumber);
        }

    }

    @Override
    public User getUserByUserName(String userName) throws UserNotFoundException{
        User user = userRepository.findByUserName(userName);
        if(user==null){
            throw new UserNotFoundException("User does not exist! Check the user name");
        }
        return user;
    }
}
