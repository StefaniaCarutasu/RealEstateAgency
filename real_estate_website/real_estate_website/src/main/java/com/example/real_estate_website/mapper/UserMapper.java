package com.example.real_estate_website.mapper;

import com.example.real_estate_website.dtos.UserDto;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.models.Role;
import com.example.real_estate_website.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper {

    @Autowired
    private RoleService roleService;

    public AppUser mapToUser(UserDto userDto) {
        Role role = roleService.getRoleByName(userDto.getRole());
        return AppUser.builder()
            .age(userDto.getAge())
            .email(userDto.getEmail())
            .firstName(userDto.getFirstName())
            .lastName(userDto.getLastName())
            .phoneNumber(userDto.getPhoneNumber())
            .role(role)
            .build();
    }

    public UserDto mapToUserDto(AppUser appUser) {
        return UserDto.builder()
            .age(appUser.getAge())
            .email(appUser.getEmail())
            .firstName(appUser.getFirstName())
            .lastName(appUser.getLastName())
            .phoneNumber(appUser.getPhoneNumber())
            .role(appUser.getRole().getName())
            .build();
    }
}
