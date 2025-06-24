package com.ctt.restservices.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctt.restservices.model.request.UserDetailsRequestModel;
import com.ctt.restservices.model.response.UserResponse;
import com.ctt.restservices.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

    // This is a placeholder for user data, in a real application this would be replaced with a database or other storage
    Map<String, UserResponse> users;
    Utils utils;

    public UserServiceImpl() {}

    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserResponse createUser(UserDetailsRequestModel userRequest) {
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(userRequest.getFirstName());
        userResponse.setLastName(userRequest.getLastName());
        userResponse.setEmail(userRequest.getEmail());

        // Simulating user creation with a random UUID as userId
        String userId = utils.generateUserId();
        userResponse.setUserId(userId);

        // Storing the user in the placeholder map
        if(users == null) {
            users = new HashMap<>();
            users.put(userId, userResponse);
        }
        
        return userResponse;
    }

    
}
