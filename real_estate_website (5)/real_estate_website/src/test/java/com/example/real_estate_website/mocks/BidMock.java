package com.example.real_estate_website.mocks;

import com.example.real_estate_website.dtos.BidDto;
import com.example.real_estate_website.models.Bid;

public class BidMock {

    public static Bid mockBid() {
        return Bid.builder()
            .id(6L)
            .offer(98000)
            .listing(ListingMock.mockListing())
            .bidder(UserMock.mockBidderUser())
            .build();
    }

    public static BidDto mockBidDto() {
        return BidDto.builder()
            .offer(98000)
            .build();
    }
}
