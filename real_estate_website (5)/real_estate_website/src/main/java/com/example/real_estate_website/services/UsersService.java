package com.example.real_estate_website.services;

import com.example.real_estate_website.dtos.UserDto;
import com.example.real_estate_website.models.AppUser;

import java.util.List;

public interface UsersService {
    boolean addUser(UserDto user);

    AppUser getUserById(Long userId);

    boolean modifyUser(AppUser appUser);

    List<AppUser> getUsersByRole(String roleName);

    List<AppUser> getAllUsers();
}
