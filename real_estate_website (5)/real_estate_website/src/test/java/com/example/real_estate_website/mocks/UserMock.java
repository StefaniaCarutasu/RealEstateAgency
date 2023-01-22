package com.example.real_estate_website.mocks;

import com.example.real_estate_website.dtos.RoleDto;
import com.example.real_estate_website.dtos.UserDto;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.models.Role;


public class UserMock {

    public static AppUser mockUser(){
        Role role = Role.builder().id(3L).name("AGENT").build();
        return AppUser.builder()
            .id(3L)
            .firstName("Agent1")
            .lastName("Agent1")
            .email("agent1.gmail.com")
            .role(role)
            .build();
    }

    public static AppUser mockBidderUser(){
        Role role = Role.builder().id(3L).name("BIDDER").build();
        return AppUser.builder()
            .id(5L)
            .firstName("Bidder1")
            .lastName("Bidder1")
            .email("bidder1.gmail.com")
            .role(role)
            .build();
    }

    public static UserDto mockUserDto(){
        RoleDto role = RoleDto.builder().id(3L).name("AGENT").build();
        return UserDto.builder()
            .firstName("Agent1")
            .lastName("Agent1")
            .email("agent1.gmail.com")
            .role(role.getName())
            .build();
    }

}
