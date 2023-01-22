package com.example.real_estate_website.mapper;

import com.example.real_estate_website.dtos.BidDto;
import com.example.real_estate_website.models.Bid;
import org.springframework.stereotype.Component;

@Component
public class BidMapper {

    public Bid mapToBid(BidDto bidDto) {
        return Bid.builder().offer(bidDto.getOffer()).build();
    }

    public BidDto mapToBidDto(Bid bid) {
        return BidDto.builder().offer(bid.getOffer()).build();
    }
}
