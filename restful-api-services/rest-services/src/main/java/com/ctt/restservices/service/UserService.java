package com.ctt.restservices.service;

import com.ctt.restservices.model.request.UserDetailsRequestModel;
import com.ctt.restservices.model.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserDetailsRequestModel userRequest);
}
