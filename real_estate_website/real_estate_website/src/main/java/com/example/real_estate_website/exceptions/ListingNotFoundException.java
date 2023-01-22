package com.example.real_estate_website.exceptions;

public class ListingNotFoundException extends RuntimeException{
    public ListingNotFoundException(String message){
        super(message);
    }
}
