package com.example.real_estate_website.mapper;

import com.example.real_estate_website.dtos.ListingDto;
import com.example.real_estate_website.models.Listing;
import org.springframework.stereotype.Component;

@Component
public class ListingMapper {
    public Listing mapToListing(ListingDto listingDto) {
        return Listing.builder()
            .agent(listingDto.getAgent())
            .askingPrice(listingDto.getAskingPrice())
            .bids(listingDto.getBids())
            .currency(listingDto.getCurrency())
            .endDate(listingDto.getEndDate())
            .isAvailable(listingDto.getIsAvailable())
            .property(listingDto.getProperty())
            .rating(listingDto.getRating())
            .startDate(listingDto.getStartDate())
            .soldPrice(listingDto.getSoldPrice())
            .build();
    }

    public ListingDto mapToListingDto(Listing listing) {
        return ListingDto.builder()
            .agent(listing.getAgent())
            .askingPrice(listing.getAskingPrice())
            .bids(listing.getBids())
            .currency(listing.getCurrency())
            .endDate(listing.getEndDate())
            .isAvailable(listing.getIsAvailable())
            .property(listing.getProperty())
            .rating(listing.getRating())
            .startDate(listing.getStartDate())
            .soldPrice(listing.getSoldPrice())
            .build();
    }
}
