package com.example.real_estate_website.services;

import com.example.real_estate_website.dtos.BidDto;
import com.example.real_estate_website.dtos.ListingDto;
import com.example.real_estate_website.models.Listing;

import java.util.List;

public interface ListingsService {

    List<ListingDto> getAllListings();

    Listing getListingById(Long listingId);

    boolean addListing(Long propertyId, Long agentId, ListingDto listingDto);

    boolean modifyListing(Listing listing);

    boolean deleteListing(Long listingId);

    boolean addReviewToListing(Long listingId, Long userId, String reviewContent);

    boolean addBidToListing(Long listingId, Long userId, BidDto bidDto);
}
