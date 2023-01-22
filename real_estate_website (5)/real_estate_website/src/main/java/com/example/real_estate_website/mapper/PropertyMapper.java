package com.example.real_estate_website.mapper;

import com.example.real_estate_website.dtos.PropertyDto;
import com.example.real_estate_website.models.Property;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper {

    public Property mapToProperty(PropertyDto propertyDto) {
            return Property.builder()
                .address(propertyDto.getAddress())
                .numberOfBathrooms(propertyDto.getNumberOfBathrooms())
                .numberOfRooms(propertyDto.getNumberOfRooms())
                .surface(propertyDto.getSurface())
                .yardSurface(propertyDto.getYardSurface())
                .build();
    }

    public PropertyDto mapToPropertyDto(Property property) {
        return PropertyDto.builder()
            .address(property.getAddress())
            .numberOfBathrooms(property.getNumberOfBathrooms())
            .numberOfRooms(property.getNumberOfRooms())
            .surface(property.getSurface())
            .yardSurface(property.getYardSurface())
            .build();
    }
}
