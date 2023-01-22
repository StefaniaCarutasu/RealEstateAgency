package com.example.real_estate_website.mapper;

import com.example.real_estate_website.dtos.ListingDto;
import com.example.real_estate_website.mocks.ListingMock;
import com.example.real_estate_website.models.Listing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ListingMapperTest {

    @InjectMocks
    ListingMapper listingMapper;

    @Test
    void mapToListingTest(){
        //GIVEN
        ListingDto listingDto = ListingMock.mockListingDto();

        //WHEN
        Listing listing = listingMapper.mapToListing(listingDto);

        //THEN
        assertEquals(listing.getAskingPrice(), listingDto.getAskingPrice());
        assertNotNull(listing.getAgent());
    }

    @Test
    void mapToListingDtoTest(){
        //GIVEN
        Listing listing = ListingMock.mockListing();

        //WHEN
        ListingDto listingDto = listingMapper.mapToListingDto(listing);

        //THEN
        assertEquals(listing.getAskingPrice(), listingDto.getAskingPrice());
        assertNotNull(listingDto.getAgent());
    }
}
