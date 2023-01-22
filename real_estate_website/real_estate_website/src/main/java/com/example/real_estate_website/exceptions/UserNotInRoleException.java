package com.example.real_estate_website.exceptions;

public class UserNotInRoleException extends RuntimeException{
    public UserNotInRoleException(String message){
        super(message);
    }
}
