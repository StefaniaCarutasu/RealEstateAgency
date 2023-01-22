package com.example.real_estate_website.services;

import com.example.real_estate_website.dtos.PropertyDto;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.models.Property;

import java.util.List;
import java.util.Optional;

public interface PropertiesService {

    boolean addProperty(PropertyDto propertyDto, AppUser user);

    Optional<Property> getPropertyById(Long propertyId);

    List<Property> getAllPropertiesForOwner(Long ownerId);
}
