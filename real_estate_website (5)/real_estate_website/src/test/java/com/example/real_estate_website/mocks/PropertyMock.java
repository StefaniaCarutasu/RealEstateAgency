package com.example.real_estate_website.mocks;

import com.example.real_estate_website.dtos.PropertyDto;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.models.Property;
import com.example.real_estate_website.models.Role;

import java.util.HashSet;
import java.util.Set;

public class PropertyMock {

    public static Property mockProperty() {
        Role role = Role.builder().id(2L).name("OWNER").build();
        return Property.builder()
            .id(2L)
            .address("Bucuresti, Splaiul Unirii 163")
            .owner(AppUser.builder()
                .id(4L)
                .firstName("Owner4")
                .lastName("Owner4")
                .email("owner4@gmail.com")
                .role(role)
                .build())
            .numberOfRooms(4)
            .numberOfBathrooms(2)
            .surface(80D)
            .yardSurface(0D)
            .build();
    }

    public static PropertyDto mockPropertyDto() {
        return PropertyDto.builder()
            .address("Bucuresti, Splaiul Unirii 163")
            .numberOfRooms(4)
            .numberOfBathrooms(2)
            .surface(80D)
            .yardSurface(0D)
            .build();
    }
}
