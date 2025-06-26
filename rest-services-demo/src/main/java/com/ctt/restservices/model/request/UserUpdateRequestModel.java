package com.ctt.restservices.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserUpdateRequestModel {

    @NotNull(message = "First name cannot be empty.")
    @Size(min = 2, message = "First name must be more than 2 characters.")
    private String firstName;

    @NotNull(message = "Last name cannot be empty.")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }   

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
