package com.example.real_estate_website.mocks;

import com.example.real_estate_website.dtos.ListingDto;
import com.example.real_estate_website.models.Listing;

public class ListingMock {

    public static Listing mockListing() {
        return Listing.builder()
            .id(6L)
            .property(PropertyMock.mockProperty())
            .isAvailable(true)
            .currency("USD")
            .askingPrice(98000)
            .agent(UserMock.mockUser())
            .build();
    }

    public static ListingDto mockListingDto() {
        return ListingDto.builder()
            .property(PropertyMock.mockProperty())
            .agent(UserMock.mockUser())
            .askingPrice(98000)
            .isAvailable(true)
            .currency("USD")
            .build();
    }
}
