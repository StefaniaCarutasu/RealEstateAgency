package com.example.real_estate_website.exceptions;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String message){
        super(message);
    }
}
