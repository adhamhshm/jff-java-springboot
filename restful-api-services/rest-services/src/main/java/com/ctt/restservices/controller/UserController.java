package com.ctt.restservices.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctt.restservices.exceptions.UserServiceException;
import com.ctt.restservices.model.request.UserDetailsRequestModel;
import com.ctt.restservices.model.request.UserUpdateRequestModel;
import com.ctt.restservices.model.response.UserResponse;
import com.ctt.restservices.service.UserService;

@RestController
@RequestMapping("/users") // This class is a placeholder for user-related endpoints -> http://localhost:8080/users
public class UserController {

    // This is a placeholder for user data, in a real application this would be replaced with a database or other storage
    // First was done here, but moved to the UserServiceImpl too
    Map<String, UserResponse> users;

    // The @Autowired annotation automatically injects a dependency from the Spring context. It tells Spring:
    // “Find a bean of type UserService and assign it to this variable.”
    // You want to delegate business logic (like creating users, validating input, etc.) to a separate service layer
    @Autowired
    UserService userService;

    @GetMapping
    public String getUsers(
        @RequestParam(value="page", defaultValue = "1") int page, 
        @RequestParam(value="limit", defaultValue = "10") int limit,
        @RequestParam(value="sort", required = true) String sort) {
        return "Users are fetched.";
    }  

    // @GetMapping(path="/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    // public UserResponseModel getUser(@PathVariable String userId) {

    //     UserResponseModel userResponse = new UserResponseModel();
    //     userResponse.setUserId(userId);
    //     userResponse.setFirstName("John");
    //     userResponse.setLastName("Doe");
    //     userResponse.setEmail("johndoe@gmail.com");

    //     return userResponse;
    // }   

    // ----------------------------------------- GET A USER -----------------------------------------
    @GetMapping(
        path="/{userId}", 
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {

        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName("John");
        userResponse.setLastName("Doe");
        userResponse.setEmail("johndoe@gmail.com");

        // Simulating user retrieval from the placeholder map
        // In a real application, this would be replaced with a database call
        if(users.containsKey(userId)) {
            return new ResponseEntity<UserResponse>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<UserResponse>(HttpStatus.NO_CONTENT);
        }
    }

    // ----------------------------------------- GET NULL USER TEST -----------------------------------------
    @GetMapping(
        path="/null/{userId}", 
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public void getUserWithNull(@PathVariable String userId) {

        // String firstName = null;
        // int firstNameLength = firstName.length();

        if(true) {
            throw new UserServiceException("A user service exception is thrown.");
        }

        //return firstNameLength;
    } 

    // ----------------------------------------- POST/CREATE USER BEFORE ADDED SERVICE LAYER -----------------------------------------
    // @PostMapping(
    //     path="/{userId}", 
    //     consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
    //     produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    // public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserDetailsRequestModel userRequest) {
        
    //     UserResponse userResponse = new UserResponse();
    //     userResponse.setFirstName(userRequest.getFirstName());
    //     userResponse.setLastName(userRequest.getLastName());
    //     userResponse.setEmail(userRequest.getEmail());

    //     // Simulating user creation with a random UUID as userId
    //     String userId = UUID.randomUUID().toString();
    //     userResponse.setUserId(userId);

    //     // Storing the user in the placeholder map
    //     if(users == null) {
    //         users = new HashMap<>();
    //         users.put(userId, userResponse);
    //     }
        
    //     return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    // }

    // ----------------------------------------- POST/CREATE USER AFTER ADDED SERVICE LAYER -----------------------------------------
    @PostMapping(
        path="/{userId}", 
        consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserDetailsRequestModel userRequest) {
        

        // UserResponse userResponse = new UserServiceImpl().createUser(userRequest);
        UserResponse userResponse = userService.createUser(userRequest);
        
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    // ----------------------------------------- UPDATE USER -----------------------------------------
    @PutMapping(
        consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public UserResponse updateUser(@PathVariable String userId, @Valid @RequestBody UserUpdateRequestModel userUpdate) {

        UserResponse userResponseStored = users.get(userId);
        userResponseStored.setFirstName(userUpdate.getFirstName());
        userResponseStored.setLastName(userUpdate.getLastName());

        users.put(userId, userResponseStored);

        return userResponseStored;
    }

    // ----------------------------------------- DELETE USER -----------------------------------------
    @DeleteMapping(path="/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {

        if (!users.containsKey(userId)) {
            return ResponseEntity.notFound().build();
        }

        users.remove(userId);

        return ResponseEntity.noContent().build();
    }
}
