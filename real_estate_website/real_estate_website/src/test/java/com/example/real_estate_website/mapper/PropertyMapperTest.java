package com.example.real_estate_website.mapper;

import com.example.real_estate_website.dtos.PropertyDto;
import com.example.real_estate_website.mocks.PropertyMock;
import com.example.real_estate_website.models.Property;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PropertyMapperTest {

    @InjectMocks
    PropertyMapper propertyMapper;

    @Test
    void mapToPropertyTest(){
        //GIVEN
        PropertyDto propertyDto = PropertyMock.mockPropertyDto();

        //WHEN
        Property property = propertyMapper.mapToProperty(propertyDto);

        //THEN
        assertEquals(property.getAddress(), propertyDto.getAddress());
        assertNotNull(property.getSurface());
    }

    @Test
    void mapToPropertyDtoTest(){
        //GIVEN
        Property property = PropertyMock.mockProperty();

        //WHEN
        PropertyDto propertyDto = propertyMapper.mapToPropertyDto(property);

        //THEN
        assertEquals(property.getAddress(), propertyDto.getAddress());
        assertNotNull(propertyDto.getSurface());
    }
}
